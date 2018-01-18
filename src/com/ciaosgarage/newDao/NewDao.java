package com.ciaosgarage.newDao;

import com.ciaosgarage.newDao.context.Context;
import com.ciaosgarage.newDao.daoService.DaoService;

import javax.sql.DataSource;

public class NewDao {
    private DataSource dataSource;
    private Context context = Context.instance;

    public NewDao(DataSource dataSource) {
        this.dataSource = dataSource;
        context.setDataSource(dataSource);
    }

    public DaoService getDaoService() {
        return context.daoService;
    }
}
