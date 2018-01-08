package com.ciaosgarage.newDao.vo;

public class Column {
    private String columnName;
    private Object value;

    public Column(String columnName, Object value) {
        this.columnName = columnName;
        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
