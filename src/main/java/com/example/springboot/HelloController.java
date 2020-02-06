package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
// add jdbc Template
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

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
        		"SELECT my_int FROM text", 
        		(rs, rowNum) -> new TestDBRow(rs.getInt("my_int"))
               	).forEach(testDBRow -> {
			builder.append("<br>").append(testDBRow.toString());
			jdbcTemplate.query(
        			"SELECT COUNT(my_int) FROM test WHERE my_int = ?", 
				new Object[] { testDBRow.my_int() },
				(subRs, subRowNum) -> subRs.getInt("count")
			).forEach(count -> builder.append("-").append(count.toString()));
		});
		return builder.toString();
	}
}
