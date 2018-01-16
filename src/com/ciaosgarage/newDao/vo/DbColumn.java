package com.ciaosgarage.newDao.vo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Column config.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface

DbColumn {
    /**
     * 입력관련 속성을 지정한다.
     * InsertOnly : 입력만 가능하고 수정은 불가능하다
     * ReadOnly : 읽기전용
     * Editable : 자유롭게 입출력 가능하다.
     *
     * @return the rw type
     */
    RwType rwType() default RwType.EDITABLE;

    /**
     * 암호화 할 컬럼인지 선택한다.
     *
     * @return the crypt option
     */
    CryptOption cryptOption() default CryptOption.OFF;

    /**
     * 컬럼의 타입을 지정한다
     * PrimaryKey, ForeignKey, None 타입이 있다
     *
     * @return the com.ciaosgarage.com.test.column type
     */
    ColumnType columnType() default ColumnType.NONE;
}
