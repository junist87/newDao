package com.ciaosgarage.newDao.sqlHandler;

import com.ciaosgarage.newDao.sqlVo.SqlMapper;
import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;
import com.ciaosgarage.newDao.sqlVo.columnStmt.ColumnStmt;
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
     * Get sql mapper sql mapper.
     *
     * @param voMap      the vo map
     * @param columnStmt the column stmt
     * @param statements the statements
     * @return the sql mapper
     */
    SqlMapper getSelectSqlMapper(Map<String, Column> voMap, ColumnStmt columnStmt, List<AttachStmt> statements);


    SqlMapper getUpdateSqlMapper(Map<String, Column> voMap, SqlType type);
}
