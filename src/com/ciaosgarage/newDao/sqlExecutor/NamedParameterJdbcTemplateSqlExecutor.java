package com.ciaosgarage.newDao.sqlExecutor;

import com.ciaosgarage.newDao.sqlExecutor.resultSetTranslator.ResultSetTranslator;
import com.ciaosgarage.newDao.vo.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NamedParameterJdbcTemplateSqlExecutor implements SqlExecutor {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private ResultSetTranslator resultSetTranslator;


    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void setResultSetTranslator(ResultSetTranslator translator) {
        this.resultSetTranslator = translator;
    }

    @Override
    public int update(String sql, Map<String, Object> parameters) {
        return jdbcTemplate.update(sql, parameters);
    }

    @Override
    public Vo queryForObject(Class voInfo, String sql, Map<String, Object> parameters) throws EmptyResultDataAccessException {
        Vo getObject = jdbcTemplate.queryForObject(sql, parameters, (resultSet, i) -> resultSetTranslator.translate(resultSet, voInfo));
        return getObject;
    }

    @Override
    public List query(Class voInfo, String sql, Map<String, Object> parameters) throws EmptyResultDataAccessException {
        List getList = jdbcTemplate.query(sql, parameters,
                (resultSet, i) -> resultSetTranslator.translate(resultSet, voInfo));
        if (getList.size() == 0) throw new EmptyResultDataAccessException(0);
        return getList;
    }

    @Override
    public int count(String sql) {
        return jdbcTemplate.queryForObject(sql, new HashMap<>(),
                (resultSet, i) -> resultSet.getInt(1));
    }
}
