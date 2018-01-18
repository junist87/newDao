package com.ciaosgarage.test.testVo;

import com.ciaosgarage.newDao.vo.DbColumn;
import com.ciaosgarage.newDao.vo.Vo;

public class AccountVO extends Vo{
    @DbColumn
    public String name;
    @DbColumn
    public Integer age;
    @DbColumn
    public String address;
}
