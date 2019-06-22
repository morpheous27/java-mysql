package org.nitin.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class TemplateFactory {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getTemplate() {
        return jdbcTemplate;
    }


}
