package com.ciaosgarage.newDao.sqlVo.columnStmt;

import com.ciaosgarage.newDao.vo.Column;

import java.util.List;

public interface ColumnStmt {
    String getStatement();

    List<Column> getMapper();
}
