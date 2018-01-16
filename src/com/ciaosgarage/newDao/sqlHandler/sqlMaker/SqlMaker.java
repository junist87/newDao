package com.ciaosgarage.newDao.sqlHandler.sqlMaker;


import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;
import com.ciaosgarage.newDao.vo.Column;

import java.util.List;
import java.util.Map;

/**
 * The interface SqlMaker.
 * 입력된 데이터를 기준으로 SQL 문장을 작성하는 클래스
 * Update, Insert, Delete
 */
public interface SqlMaker {
    /**
     * Select 문을 만드는 메소드
     *
     * @param voMap SQL 작성에 필요한 vo 객체의 class 파일
     * @return SQL 문
     */
    String select(Map<String, Column> voMap, List<AttachStmt> attachStmts);

    /**
     * Insert 문을 만드는 메소드
     *
     * @param voMap SQL 작성에 필요한 vo 객체의 class 파일
     * @return SQL 문
     */
    String insert(Map<String, Column> voMap);

    /**
     * Delete 문을 만드는 메소드
     * Primary Key 를 기준으로 삭제 SQL 문을 만든다.
     *
     * @param voMap SQL 작성에 필요한 vo 객체의 class 파일
     * @return SQL 문
     */
    String delete(Map<String, Column> voMap);

    /**
     * Update 문을 만드는 메소드
     * Primary Key 를 기준으로 삭제 SQL 문을 만든다.
     *
     * @param voMap SQL 작성에 필요한 vo 객체의 class 파일
     * @return SQL 문
     */
    String update(Map<String, Column> voMap);

    /**
     * Delete All 문을 만드는 메소드
     * Primary Key 를 기준으로 삭제 SQL 문을 만든다.
     *
     * @param voMap SQL 작성에 필요한 vo 객체의 class 파일
     * @return SQL 문
     */
    String deleteAll(Map<String, Column> voMap);

    /**
     * SELECT COUNT(*) FROM ... 형태의 Count 문을 만드는 메소드
     *
     * @param voMap SQL 작성에 필요한 vo 객체의 class 파일
     * @return SQL 문
     */
    String count(Map<String, Column> voMap);
}