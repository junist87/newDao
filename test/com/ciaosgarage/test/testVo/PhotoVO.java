package com.ciaosgarage.test.testVo;

import com.ciaosgarage.newDao.vo.DbColumn;
import com.ciaosgarage.newDao.vo.Vo;

public class PhotoVO extends Vo {
    @DbColumn
    public String accounPk;
    @DbColumn
    public String path;
}
