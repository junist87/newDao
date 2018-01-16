package com.ciaosgarage.newDao.sqlHandler.sqlMapperMaker;


import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;
import com.ciaosgarage.newDao.vo.Column;

import java.util.List;
import java.util.Map;

/**
 * The interface SqlMapperMaker
 * SQL 실행시에 필요한 맵퍼를 만들어 주는 클래스
 */
public interface SqlMapperMaker {
    /**
     * SQL 실행시에 필요한 맵퍼를 만들어 주는 메소드
     *
     * @param voMap      맵퍼 작성에 필요한 데이터를 가지고 있는 vo 객체
     * @param statements 정보 검색시에 작성된 statement
     * @return
     */
    Map<String, Object> makeMapper(Map<String, Column> voMap, List<AttachStmt> statements);

    Map<String, Object> makeMapper(List<AttachStmt> statements);


    /**
     * SQL 실행시에 필요한 맵퍼를 만들어 주는 메소드
     *
     * @param voMap 맵퍼 작성에 필요한 데이터를 가지고 있는 vo 객체
     * @return
     */
    Map<String, Object> makeMapper(Map<String, Column> voMap);

}
