package com.ciaosgarage.newDao.sqlVo.columnStmt;

import com.ciaosgarage.newDao.vo.Column;

import java.util.List;

public class CSSelectAll implements ColumnStmt {
    @Override
    public String getStatement() {
        return "*";
    }

    @Override
    public List<Column> getMapper() {
        return null;
    }
}
