package com.ciaosgarage.newDao.dbMaker;

public interface DbMaker {
    boolean isExistTalbe(Class voInfo);
    boolean makeTable(Class voInfo);
}
