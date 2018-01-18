package com.ciaosgarage.newDao.sqlVo.requsetHandler;

import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;

import java.util.ArrayList;
import java.util.List;

public abstract class RequestHandlerFW implements RequestHandler {
    protected Class voInfo;
    protected List<AttachStmt> list;

    public RequestHandlerFW(Class voInfo) {
        this.voInfo = voInfo;
        reset();
    }

    @Override
    final public List<AttachStmt> getStatements() {
        List returnList = list;
        reset();
        return returnList;
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
