package com.ciaosgarage.newDao.dao;

import com.ciaosgarage.newDao.sqlExecutor.SqlExecutor;
import com.ciaosgarage.newDao.sqlHandler.SqlHandler;
import com.ciaosgarage.newDao.sqlHandler.SqlType;
import com.ciaosgarage.newDao.sqlVo.SqlMapper;
import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;
import com.ciaosgarage.newDao.vo.Column;
import com.ciaosgarage.newDao.vo.Vo;
import com.ciaosgarage.newDao.voHandler.VoHandler;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Map;


public class DaoImpl implements Dao {
    SqlHandler sqlHandler;
    SqlExecutor sqlExecutor;
    VoHandler voHandler;

    public void setSqlHandler(SqlHandler sqlHandler) {
        this.sqlHandler = sqlHandler;
    }

    public void setSqlExecutor(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    public void setVoHandler(VoHandler voHandler) {
        this.voHandler = voHandler;
    }

    @Override
    public int add(Vo vo) {
        Map<String, Column> voMap = voHandler.transformToMap(vo);
        SqlMapper sqlMapper = sqlHandler.getSqlMapper(voMap, SqlType.INSERT);
        return sqlExecutor.update(sqlMapper.getSql(), sqlMapper.getMapper());
    }

    @Override
    public Object get(Class voInfo, List<AttachStmt> statements) throws EmptyResultDataAccessException {
        Map<String, Column> voMap = voHandler.transformToMap(voInfo);
        SqlMapper sqlMapper = sqlHandler.getSqlMapper(voMap, SqlType.SELECT, statements);
        return sqlExecutor.queryForObject(voInfo, sqlMapper.getSql(), sqlMapper.getMapper());
    }

    @Override
    public List getList(Class voInfo, List<AttachStmt> statements) throws EmptyResultDataAccessException {
        Map<String, Column> voMap = voHandler.transformToMap(voInfo);
        SqlMapper sqlMapper = sqlHandler.getSqlMapper(voMap, SqlType.SELECT, statements);
        return sqlExecutor.query(voInfo, sqlMapper.getSql(), sqlMapper.getMapper());
    }

    @Override
    public int update(Vo vo) {
        Map<String, Column> voMap = voHandler.transformToMap(vo);
        SqlMapper sqlMapper = sqlHandler.getSqlMapper(voMap, SqlType.UPDATE);
        return sqlExecutor.update(sqlMapper.getSql(), sqlMapper.getMapper());
    }

    @Override
    public int delete(Vo vo) {
        Map<String, Column> voMap = voHandler.transformToMap(vo);
        SqlMapper sqlMapper = sqlHandler.getSqlMapper(voMap, SqlType.DELETE);
        return sqlExecutor.update(sqlMapper.getSql(), sqlMapper.getMapper());
    }

    @Override
    public int count(Class voInfo) {
        Map<String, Column> voMap = voHandler.transformToMap(voInfo);
        SqlMapper sqlMapper = sqlHandler.getSqlMapper(voMap, SqlType.COUNT);
        return sqlExecutor.count(sqlMapper.getSql());
    }

    @Override
    public int deleteAll(Class voInfo) {
        Map<String, Column> voMap = voHandler.transformToMap(voInfo);
        SqlMapper sqlMapper = sqlHandler.getSqlMapper(voMap, SqlType.DELETEALL);
        return sqlExecutor.update(sqlMapper.getSql(), sqlMapper.getMapper());
    }
}
