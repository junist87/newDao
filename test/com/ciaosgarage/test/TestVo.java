package com.ciaosgarage.test;

import com.ciaosgarage.newDao.vo.CryptOption;
import com.ciaosgarage.newDao.vo.DbColumn;
import com.ciaosgarage.newDao.vo.RwType;
import com.ciaosgarage.newDao.vo.Vo;

import java.sql.Date;
import java.sql.Timestamp;

public class TestVo extends Vo {
    @DbColumn(rwType = RwType.EDITABLE)
    public String name;
    @DbColumn(rwType = RwType.INSERTONLY)
    public Integer age;
    @DbColumn(cryptOption = CryptOption.ON)
    public String nickname;
    @DbColumn(rwType = RwType.READONLY)
    public Timestamp createDate;
    @DbColumn()
    public Double lat;
}
