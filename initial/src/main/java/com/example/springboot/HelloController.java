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
        		"SELECT my_int FROM test", 
        		(rs, rowNum) -> new String(new TestDB(rs.getLong("my_int")).toString())
                ).forEach(testDBRow -> builder.append("<br>").append(testDBRow));
		return builder.toString();
	}

}
