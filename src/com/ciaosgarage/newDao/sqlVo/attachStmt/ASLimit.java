package com.ciaosgarage.newDao.sqlVo.attachStmt;

import com.ciaosgarage.newDao.vo.Column;

import java.util.List;

public class ASLimit implements AttachStmt {

    private Integer unit;
    private Integer startIndex;

    public ASLimit(int startIndex, int unit) {
        this.unit = unit;
        this.startIndex = startIndex;
    }

    public ASLimit(int unit) {
        this.unit = unit;
    }

    public void setUnit(int unit) {
        this.startIndex = null;
        this.unit = unit;
    }

    public void setValue(int startIndex, int unit) {
        this.unit = unit;
        this.startIndex = startIndex;
    }

    @Override
    public String getStatement() {
        if (startIndex != null) return "LIMIT " + startIndex.toString() + ", " + unit.toString();
        else return "LIMIT " + unit.toString();
    }

    @Override
    public List<Column> getMapper() {
        return null;
    }
}
