package com.ciaosgarage.newDao.context;

import com.ciaosgarage.newDao.dao.Dao;
import com.ciaosgarage.newDao.dao.DaoImpl;
import com.ciaosgarage.newDao.sqlExecutor.NamedParameterJdbcTemplateSqlExecutor;
import com.ciaosgarage.newDao.sqlExecutor.SqlExecutor;
import com.ciaosgarage.newDao.vo.cryptHandler.BasicCryptor;
import com.ciaosgarage.newDao.vo.cryptHandler.Cryptor;
import com.ciaosgarage.newDao.sqlExecutor.resultSetTranslator.ResultSetTranslatorImpl;
import com.ciaosgarage.newDao.sqlHandler.SqlHandler;
import com.ciaosgarage.newDao.sqlHandler.SqlHandlerImpl;
import com.ciaosgarage.newDao.sqlHandler.sqlMaker.SqlMakerImpl;
import com.ciaosgarage.newDao.sqlHandler.sqlMapperMaker.SqlMapperMakerImpl;
import com.ciaosgarage.newDao.voHandler.VoHandler;
import com.ciaosgarage.newDao.voHandler.VoHandlerImpl;

import javax.sql.DataSource;

public class Context {
    // 암호화 기본키
    private static String CRPYTION_KEY = "De3CZBD2bdDfaefadv";

    // 테이블 기본 prefix
    private static String TABLE_PREFIX = "tbl";

    // 공개되는 객체
    public VoHandler voHandler;
    public DataSource dataSource;
    public SqlExecutor sqlExecutor;
    public SqlHandler sqlHandler;
    public Cryptor cryptor;
    public Dao dao;


    // 싱글턴 리스트
    public final static Context modules = new Context();

    private Context() {
        this.createBeans();
    }

    // DataSource 에 의존하는 객체
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        sqlExecutor = createSqlExecutor();
        dao = createDao();
    }

    // 새로운 암호화키 설정
    public void setCrpytionKey(String key) {
        CRPYTION_KEY = key;
        createCryptor(CRPYTION_KEY);
    }

    // 새로운 테이블 prefix 설정
    public void setTablePrefix(String prefix) {
        TABLE_PREFIX = prefix;

    }

    /* ----------------------*/
    /* 객체 초기화 메소드 호출   */
    /* ----------------------*/
    // 객체 생성
    //* 이곳에 객체 생성 메소드 적기
    private void createBeans() {
        this.voHandler = createVoHandler();
        this.cryptor = createCryptor(CRPYTION_KEY);
        this.sqlHandler = createSqlHandler();
    }


    /* ----------------------*/
    /*  초기화 메소드 입력      */
    /* ----------------------*/
    private VoHandler createVoHandler() {
        return new VoHandlerImpl();
    }

    private Cryptor createCryptor(String key) {
        return new BasicCryptor(key);
    }

    private SqlExecutor createSqlExecutor() {
        // SqlExecutor 를 동작시키기 위한 클래스 객체화
        ResultSetTranslatorImpl resultSetTranslator = new ResultSetTranslatorImpl();
        resultSetTranslator.setVoHandler(this.voHandler);

        NamedParameterJdbcTemplateSqlExecutor executor = new NamedParameterJdbcTemplateSqlExecutor();
        executor.setDataSource(dataSource);
        executor.setResultSetTranslator(resultSetTranslator);
        return executor;
    }

    private SqlHandler createSqlHandler() {
        // SqlHandler 를 동작시키기 위한 클래스 객체화
        SqlMakerImpl sqlMaker = new SqlMakerImpl();
        SqlMapperMakerImpl sqlMapperMaker = new SqlMapperMakerImpl();

        SqlHandlerImpl sqlHandler = new SqlHandlerImpl();
        sqlHandler.setSqlMaker(sqlMaker);
        sqlHandler.setSqlMapperMaker(sqlMapperMaker);

        return sqlHandler;
    }


    private Dao createDao() {
        // Dao 를 동작시키기 위한 클래스 객체화
        DaoImpl dao = new DaoImpl();
        dao.setVoHandler(voHandler);
        dao.setSqlExecutor(sqlExecutor);
        dao.setSqlHandler(sqlHandler);
        return dao;
    }


}
