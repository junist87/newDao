package com.ciaosgarage.newDao.sqlExecutor.resultSetTranslator;

import com.ciaosgarage.newDao.vo.Column;
import com.ciaosgarage.newDao.vo.Vo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * The interface ResultSetTranslator.
 * ResultSet 의 데이터를 지정한 vo 객체로 변환시켜주는 클래스
 * JdbcTemplate의 콜백함수이다
 */
public interface ResultSetTranslator {
    /**
     * ResultSet 의 데이터를 지정한 vo 객체로 변환시켜주는 메소드
     *
     * @param resultSet 변환할 데이터셋
     * @param voInfo    변환할 객체의 정보를 가지고 있는 class
     * @return 변환된 객체
     * @throws SQLException 변환에 실패시에는 SQLException 이 발생한다.
     */
    Vo translate(ResultSet resultSet, Class voInfo) throws SQLException;


}