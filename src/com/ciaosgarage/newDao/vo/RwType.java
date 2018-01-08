package com.ciaosgarage.newDao.vo;

public enum RwType {
    EDITABLE, INSERTONLY, READONLY;

    static public RwType getDefault() {
        return RwType.EDITABLE;
    }
}
