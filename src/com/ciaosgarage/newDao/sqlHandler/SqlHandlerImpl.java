package com.ciaosgarage.newDao.sqlHandler;

import com.ciaosgarage.newDao.sqlHandler.sqlMaker.SqlMaker;
import com.ciaosgarage.newDao.sqlHandler.sqlMapperMaker.SqlMapperMaker;
import com.ciaosgarage.newDao.sqlVo.SqlMapper;
import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;
import com.ciaosgarage.newDao.vo.Column;
import com.sun.tools.corba.se.idl.StringGen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlHandlerImpl implements SqlHandler {
    private SqlMaker sqlMaker;
    private SqlMapperMaker sqlMapperMaker;

    public void setSqlMaker(SqlMaker sqlMaker) {
        this.sqlMaker = sqlMaker;
    }

    public void setSqlMapperMaker(SqlMapperMaker sqlMapperMaker) {
        this.sqlMapperMaker = sqlMapperMaker;
    }

    @Override
    public SqlMapper getSqlMapper(Map<String, Column> voMap, SqlType type, List<AttachStmt> stmts) {
        // 기본값 가져오기
        String sql = makeSql(voMap, type, stmts);

        Map<String, Object> mapper;
        mapper = sqlMapperMaker.makeMapper(stmts);
        mapper.putAll(sqlMapperMaker.makeMapper(voMap));
        return new SqlMapper(sql, mapper);

    }

    @Override
    public SqlMapper getSqlMapper(Map<String, Column> voMap, SqlType type) {
        return getSqlMapper(voMap, type, new ArrayList<>());
    }

    private String makeSql(Map<String, Column> voMap, SqlType type, List<AttachStmt> stmts) {
        String sql;
        switch (type) {
            case SELECT:
                sql = sqlMaker.select(voMap, stmts);
                break;
            case COUNT:
                sql = sqlMaker.count(voMap);
                break;
            case DELETE:
                sql = sqlMaker.delete(voMap);
                break;
            case INSERT:
                sql = sqlMaker.insert(voMap);
                break;
            case UPDATE:
                sql = sqlMaker.update(voMap);
                break;
            case DELETEALL:
                sql = sqlMaker.deleteAll(voMap);
                break;
            default:
                sql = "";
        }
        return sql;
    }

}
