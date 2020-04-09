package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
// add jdbc Template
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Scope;
import co.elastic.apm.api.Span;
import co.elastic.apm.api.Transaction;

import javax.servlet.http.HttpServletRequest;

@RestController
// implement command line runner
public class HelloController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append("DB values");
		Transaction transaction = ElasticApm.startTransactionWithRemoteParent(
			header -> request.getHeader(header));
		//try (final Scope scope = transaction.activate()) {
		try {
			ElasticApm.currentSpan().setName("try catch block");
			jdbcTemplate.query(
        			"SELECT my_int FROM test",
        			(rs, rowNum) -> new String(new TestDBRow(rs.getInt("my_int")).toString())
              		  ).forEach(testDBRow -> {
				builder.append(",").append(testDBRow);
			});
		} catch (Exception e) {
    			transaction.captureException(e);
    			throw e;
		} finally {
    			transaction.setResult("OK");
    			transaction.end();
		}
		return builder.toString();
	}
}
