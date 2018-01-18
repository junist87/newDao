package com.ciaosgarage.newDao.sqlVo.columnStmt;

import com.ciaosgarage.newDao.vo.Column;

import java.util.List;

public class CSCountAll implements ColumnStmt{
    @Override
    public String getStatement() {
        return "COUNT(*)";
    }

    @Override
    public List<Column> getMapper() {
        return null;
    }
}
