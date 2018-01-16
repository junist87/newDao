package com.ciaosgarage.newDao.sqlVo.attachStmt;

import com.ciaosgarage.newDao.vo.Column;

import java.util.List;

public interface AttachStmt {
    /**
     * 만들어진 Sql 문을 가져온다
     *
     * @return Sql 문
     */
    String getStatement();

    /**
     * Sql에 입력되는 필드값
     *
     * @return 필드값 리스트
     */
    List<Column> getMapper();

}
