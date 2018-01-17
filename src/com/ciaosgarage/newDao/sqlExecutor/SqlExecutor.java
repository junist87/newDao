package com.ciaosgarage.newDao.sqlExecutor;

import com.ciaosgarage.newDao.vo.Vo;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Map;

/**
 * The interface SqlExecutor
 * 데이터베이스에 접근하여 SQL 구문을 처리해주는 클래스
 */
public interface SqlExecutor {
    /**
     * UPDATE 구문을 처리해주는 메소드
     * (insert, update, delete)
     *
     * @param sql        처리할 slq 구문
     * @param parameters :tag 에 들어갈 파라미터 셋
     * @return 접근(처리)된 레코드의 갯수
     */
    int update(String sql, Map<String, Object> parameters);

    /**
     * 데이터베이스에서 데이터를 1개 가져오는 메소드
     * (select)
     *
     * @param voInfo     접근할 테이블의 정보를 가지고 있는 vo 객체의 class
     * @param sql        처리할 slq 구문
     * @param mapper :tag 에 들어갈 파라미터 셋
     * @return 조건에 맞는 레코드
     * @throws EmptyResultDataAccessException 검색하는 조건값이 없다면 발생하는 Exception
     */
    Vo queryForObject(Class voInfo, String sql, Map<String, Object> mapper) throws EmptyResultDataAccessException;

    /**
     * 데이터베이스에서 데이터를 여러개 가져오는 메소드
     * (select)
     *
     * @param voInfo     접근할 테이블의 정보를 가지고 있는 vo 객체의 class
     * @param sql        처리할 slq 구문
     * @param mapper :tag 에 들어갈 파라미터 셋
     * @return 조건에 맞는 레코드
     * @throws EmptyResultDataAccessException 검색하는 조건값이 없다면 발생하는 Exception
     */
    List<Vo> query(Class voInfo, String sql, Map<String, Object> mapper) throws EmptyResultDataAccessException;

    /**
     * 해당 테이블의 전체 레코드의 수를 가져오는 메소드
     *
     * @param sql 검색할 테이블의 이름
     * @return 해당 테이블의 전체 레코드의 수
     */
    int count(String sql);
}
