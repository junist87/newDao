package com.ciaosgarage.newDao.sqlVo.requsetHandler;

import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;

import java.util.List;


/**
 * The interface AttachStatement handler.
 * AttachStatement 의 관리를 담당한다.
 * AttachStatement 사용을 쉽게 해주는 클래스
 */
public interface RequestHandler {

    /**
     * 저장된 AttachStatement 를 리턴한다.
     *
     * @return attach statement
     */
    List<AttachStmt> getStatements();

    /**
     * 이 요청의 결과값을 담을 vo 객체의 클래스 정보를 리턴한다
     *
     * @return the vo info
     */
    Class getVoInfo();


    /**
     * Sets vo info.
     *
     * @param voInfo the vo info
     */
    void setVoInfo(Class voInfo);

    /**
     * 저장된 AttachStatement 를 모두 초기화 한다.
     */
    void reset();

    /* ***********
        기본 표준 기능들

     ************* */

    /**
     * Search.
     *
     * @param columnName the column name
     * @param value      the value
     */
    void search(String columnName, Object value);

    /**
     * Section search.
     *
     * @param columnName the column name
     * @param start      the start
     * @param end        the end
     */
    void sectionSearch(String columnName, Object start, Object end);

    /**
     * Order by.
     *
     * @param columnName the column name
     * @param ascending  the ascending
     */
    void orderBy(String columnName, boolean ascending);

    /**
     * Number of.
     *
     * @param size the size
     */
    void numberOf(int size);
}
