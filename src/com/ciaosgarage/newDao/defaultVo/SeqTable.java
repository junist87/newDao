package com.ciaosgarage.newDao.defaultVo;

import com.ciaosgarage.newDao.vo.DbColumn;
import com.ciaosgarage.newDao.vo.RwType;
import com.ciaosgarage.newDao.vo.Vo;

import java.sql.Timestamp;

public class SeqTable extends Vo {
    @DbColumn(rwType = RwType.READONLY)
    public Timestamp createDate;
    @DbColumn
    public String targetName;
}
