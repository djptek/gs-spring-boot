package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
// add jdbc Template
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import co.elastic.apm.api.Span;

@RestController
// implement command line runner
public class HelloController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	public String index() {
		StringBuilder builder = new StringBuilder();
		builder.append("DB values:");
		jdbcTemplate.query(
        		"SELECT my_int FROM test", 
        		(rs, rowNum) -> new TestDBRow(rs.getInt("my_int"))
               	).forEach(testDBRow -> {
			Transaction transaction = ElasticApm.startTransaction();
			try {
    				transaction.setType(Transaction.TYPE_REQUEST);
    				transaction.setName("HelloController#Java8forEach:"+testDBRow.toString());
				builder.append("<br>").append(testDBRow.toString());
				jdbcTemplate.query(
        				"SELECT COUNT(my_int) FROM test WHERE my_int = ?", 
					new Object[] { testDBRow.my_int() },
					(subRs, subRowNum) -> subRs.getInt("count")
				).forEach(count -> builder.append("-").append(count.toString()));
			} catch (Exception e) {
    				transaction.captureException(e);
    				throw e;
			} finally {
    				transaction.setResult("OK");
    				transaction.end();
			}
		});
		return builder.toString();
	}
}
