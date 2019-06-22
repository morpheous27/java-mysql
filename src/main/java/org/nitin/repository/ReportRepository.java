package org.nitin.repository;

import org.nitin.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportRepository {

    @Autowired
    private TemplateFactory templateFactory;

    @Value("${weekly.report}")
    private String sql;

    public List<Report> getReport() {
        return templateFactory.getTemplate().query(sql, new BeanPropertyRowMapper(Report.class));
    }
}
