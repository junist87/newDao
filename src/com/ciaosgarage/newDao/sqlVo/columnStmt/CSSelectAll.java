package com.ciaosgarage.newDao.sqlVo.columnStmt;

public class CSSelectAll implements ColumnStmt {
    @Override
    public String getStatement() {
        return "*";
    }
}
