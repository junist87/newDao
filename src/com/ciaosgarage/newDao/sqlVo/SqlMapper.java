package com.ciaosgarage.newDao.sqlVo;

import java.util.Map;

public class SqlMapper {
    String sql;
    Map<String, Object> mapper;

    public SqlMapper(String sql, Map<String, Object> mapper) {
        this.sql = sql;
        this.mapper = mapper;
    }

    public String getSql() {
        return sql;
    }

    public Map<String, Object> getMapper() {
        return mapper;
    }
}
