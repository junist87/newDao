package com.ciaosgarage.newDao;

import com.ciaosgarage.newDao.context.Context;
import com.ciaosgarage.newDao.voHandler.VoHandler;
import com.ciaosgarage.newDao.voHandler.VoHandlerImpl;

import javax.sql.DataSource;

public class NewDao {
    private DataSource dataSource;
    private Context context;

    public NewDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Context getContext() {
        return context;
    }
}
