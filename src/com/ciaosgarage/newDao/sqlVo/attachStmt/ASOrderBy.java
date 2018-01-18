package com.ciaosgarage.newDao.sqlVo.attachStmt;

import com.ciaosgarage.newDao.vo.Column;

import java.util.List;

public class ASOrderBy implements AttachStmt {
    private Boolean ascending;
    private String columnName;
    private StringBuilder sql;

    public ASOrderBy(String columnName, boolean ascending) {
        this.ascending = ascending;
        this.columnName = columnName;
    }

    public void setValues(String columnName, boolean ascending) {
        this.ascending = ascending;
        this.columnName = columnName;
    }

    @Override
    public String getStatement() throws RuntimeException {
        if ((columnName == null) || (ascending == null))
            throw new RuntimeException("정렬조건이 입력되지 않았습니다. columnName = " + columnName + ", ascending = " + ascending);
        return "ORDER BY " + columnName + " " + getOperator();
    }

    @Override
    public List<Column> getMapper() {
        return null;
    }

    private String getOperator() {
        if (ascending) return "ASC";
        else return "DESC";
    }
}
