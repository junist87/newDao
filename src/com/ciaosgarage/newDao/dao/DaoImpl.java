package com.ciaosgarage.newDao.dao;

import com.ciaosgarage.newDao.sqlExecutor.SqlExecutor;
import com.ciaosgarage.newDao.sqlHandler.SqlHandler;
import com.ciaosgarage.newDao.sqlHandler.SqlType;
import com.ciaosgarage.newDao.sqlVo.SqlMapper;
import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;
import com.ciaosgarage.newDao.vo.Vo;
import com.ciaosgarage.newDao.voHandler.VoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;


public class DaoImpl implements Dao {
    @Override
    public int add(Vo vo) {
        return 0;
    }

    @Override
    public Object get(Class voInfo, List<AttachStmt> statements) throws EmptyResultDataAccessException {
        return null;
    }

    @Override
    public List getList(Class voInfo, List<AttachStmt> statements) throws EmptyResultDataAccessException {
        return null;
    }

    @Override
    public int update(Vo vo) {
        return 0;
    }

    @Override
    public int delete(Vo vo) {
        return 0;
    }

    @Override
    public int count(Class voInfo) {
        return 0;
    }

    @Override
    public int deleteAll(Class voInfo) {
        return 0;
    }
}
