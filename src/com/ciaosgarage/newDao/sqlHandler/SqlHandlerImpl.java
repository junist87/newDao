package com.ciaosgarage.newDao.sqlHandler;

import com.ciaosgarage.newDao.sqlHandler.sqlMaker.SqlMaker;
import com.ciaosgarage.newDao.sqlHandler.sqlMapperMaker.SqlMapperMaker;
import com.ciaosgarage.newDao.sqlVo.SqlMapper;
import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;
import com.ciaosgarage.newDao.vo.Column;

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
        SqlMapper sqlMapper = getSqlMapper(voMap, type);

        // 추가 스테이트먼트 합치기
        String sql = sqlMapper.getSql() + getAttachStmtSql(stmts);
        Map<String, Object> mapper = sqlMapperMaker.makeMapper(stmts);
        mapper.putAll(sqlMapper.getMapper());
        return new SqlMapper(sql, mapper);

    }

    @Override
    public SqlMapper getSqlMapper(Map<String, Column> voMap, SqlType type) {
        String sql;
        Map<String, Object> mapper = sqlMapperMaker.makeMapper(voMap);
        switch (type) {
            case SELECT:
                sql = sqlMaker.select(voMap, new ArrayList<>());
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
        return new SqlMapper(sql, mapper);
    }

    private String getAttachStmtSql(List<AttachStmt> stmts) {
        StringBuilder sql = new StringBuilder();
        for (AttachStmt stmt : stmts) {
            sql.append(stmt.getStatement());
            sql.append(" ");
        }
        return sql.toString().trim();
    }
}