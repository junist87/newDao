package com.ciaosgarage.newDao.vo;

public abstract class Vo {
    // 필드 이름을 절대 바꾸면 안된다.
    @DbColumn(columnType = ColumnType.PRIMARYKEY, rwType = RwType.INSERTONLY)
    private String pk;

    public String getPk() {
        return pk;
    }
}
