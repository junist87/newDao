package com.ciaosgarage.newDao.sqlHandler;

import com.ciaosgarage.newDao.sqlHandler.sqlMaker.SqlMaker;
import com.ciaosgarage.newDao.sqlHandler.sqlMapperMaker.SqlMapperMaker;
import com.ciaosgarage.newDao.sqlVo.SqlMapper;
import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;
import com.ciaosgarage.newDao.sqlVo.columnStmt.ColumnStmt;
import com.ciaosgarage.newDao.vo.Column;

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
    public SqlMapper getSelectSqlMapper(Map<String, Column> voMap, ColumnStmt columnStmt, List<AttachStmt> attachStmts) {
        // 기본값 가져오기
        String sql = sqlMaker.select(voMap, columnStmt, attachStmts);

        // 맵퍼를 모두 합친다
        Map<String, Object> mapper;
        mapper = sqlMapperMaker.makeMapper(attachStmts);
        mapper.putAll(sqlMapperMaker.makeMapper(columnStmt));
        mapper.putAll(sqlMapperMaker.makeMapper(voMap));
        return new SqlMapper(sql, mapper);
    }

    @Override
    public SqlMapper getUpdateSqlMapper(Map<String, Column> voMap, SqlType type) {
        return new SqlMapper(getUpdateSql(voMap, type), sqlMapperMaker.makeMapper(voMap));
    }

    private String getUpdateSql(Map<String, Column> voMap, SqlType type) {
        switch (type) {
            case COUNT:
                return sqlMaker.count(voMap);
            case DELETE:
                return sqlMaker.delete(voMap);
            case INSERT:
                return sqlMaker.insert(voMap);
            case UPDATE:
                return sqlMaker.update(voMap);
            case DELETEALL:
                return sqlMaker.deleteAll(voMap);
            default:
                throw new RuntimeException("SQL 문을 만들수 없습니다.");
        }
    }


}
