package com.ciaosgarage.newDao.sqlHandler;

import com.ciaosgarage.newDao.sqlVo.SqlMapper;
import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;
import com.ciaosgarage.newDao.vo.Column;

import java.util.List;
import java.util.Map;

/**
 * The interface SqlHandler
 * Sql 작성을 총괄 책임하는 클래스
 * SQL 작성과 실행에 필요한 Mapper 를 만들어 준다
 * 자주 쓰이는 기본패턴의 SQL 문을 저장하는 기능이 있다
 */
public interface SqlHandler {
    /**
     * SqlProduct = Sql + Mapper
     *
     * @param voMap      SQL 에 입력할 데이터를 담고 있는 vo 객체
     * @param type       SQL 타입선택 (select, update, insert, delete)
     * @param statements 추가 검색같은 SQL 부가기능을 사용할때 입력하는 파라미터
     * @return the sql product
     */
    SqlMapper getSqlMapper(Map<String, Column> voMap, SqlType type, List<AttachStmt> statements);

    /**
     * SqlProduct = Sql + Mapper
     *
     * @param voMap SQL 작성에 필요한 데이터를 담고 있는 class 파일
     * @param type  SQL 타입선택 (select, update, insert, delete)
     * @return the sql product
     */
    SqlMapper getSqlMapper(Map<String, Column> voMap, SqlType type);

}
