package com.ciaosgarage.newDao.sqlVo.requsetHandler;

import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;
import com.ciaosgarage.newDao.sqlVo.columnStmt.CSSelectAll;
import com.ciaosgarage.newDao.sqlVo.columnStmt.ColumnStmt;

import java.util.ArrayList;
import java.util.List;

public abstract class RequestHandlerFW implements RequestHandler {
    protected Class voInfo;
    protected List<AttachStmt> list;
    protected ColumnStmt columnStmt;

    public RequestHandlerFW(Class voInfo) {
        this.voInfo = voInfo;
        columnStmt = new CSSelectAll();
        reset();
    }

    @Override
    final public List<AttachStmt> getAttachStmt() {
        List returnList = list;
        reset();
        return returnList;
    }

    @Override
    final public ColumnStmt getColumnStmt() {
        return columnStmt;
    }

    @Override
    public void setColumnStmt(ColumnStmt columnStmt) {
        this.columnStmt = columnStmt;
    }

    @Override
    final public Class getVoInfo() {
        return voInfo;
    }

    @Override
    final public void setVoInfo(Class voInfo) {
        this.voInfo = voInfo;
    }

    @Override
    final public void reset() {
        list = new ArrayList<>();
    }


}
