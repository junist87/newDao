package com.ciaosgarage.newDao.voHandler;

import com.ciaosgarage.newDao.exceptions.NoExistPrimaryKeyFieldException;
import com.ciaosgarage.newDao.vo.Column;
import com.ciaosgarage.newDao.vo.RwType;
import com.ciaosgarage.newDao.vo.Table;

import java.util.List;
import java.util.Map;

public interface VoHandler {

    /**
     * vo 객체 내부에 primaryKey 가 존재 한다면, 그 값을 추출한다.
     *
     * @param vo 검색할 vo 객체
     * @return primary key 값
     * @throws NoExistPrimaryKeyFieldException primaryKey 값이 없다면 예외를 발생한다.
     */
    Object getPrimaryKey(Table vo) throws NoExistPrimaryKeyFieldException;

    /**
     * vo 객체 내부에 primaryKey 가 존재 한다면, 그 컬럼값을 추출한다.
     *
     * @param vo 검색할 vo 객체
     * @return primary key 값을 가진 컬럼 이름
     * @throws NoExistPrimaryKeyFieldException primaryKey 값이 없다면 예외를 발생한다.
     */
    String getPrimaryKeyColumnName(Table vo) throws NoExistPrimaryKeyFieldException;

    /**
     * class 에 primaryKey 가 존재 한다면, 그 컬럼값을 추출한다.
     *
     * @param voInfo 검색할 vo 객체
     * @return primary key 값을 가진 컬럼 이름
     * @throws NoExistPrimaryKeyFieldException primaryKey 값이 없다면 예외를 발생한다.
     */
    String getPrimaryKeyColumnName(Class voInfo) throws NoExistPrimaryKeyFieldException;

    /**
     * vo 객체 내부에 primaryKey 가 존재 한다면, 그 필드에 값을 입력한다.
     *
     * @param vo          입력할 vo 객체
     * @param value 입력할 값
     * @throws NoExistPrimaryKeyFieldException primaryKey 값이 없다면 예외를 발생한다.
     */
    void setPrimaryKey(Table vo, Object value) throws NoExistPrimaryKeyFieldException;

    /**
     * 특정한 필드의 값을 가져온다
     *
     * @param vo         the vo
     * @param columnName the column name
     * @return the value
     */
    Object getValue(Table vo, String columnName);

    /**
     * 특정한 필드에 값을 입력한다.
     *
     * @param vo         the vo
     * @param columnName the column name
     * @param value      the value
     * @return 입력된 vo객체
     */
    Object setValue(Table vo, String columnName, Object value);

    /**
     * 특정 필드의 RwType 을 리턴한다
     *
     * @param voInfo     필드가 속해있는 클래스
     * @param columnName 필드 이름
     * @return RwType rw type
     */
    RwType getRwType(Class voInfo, String columnName);

    /**
     * 객체의 필드값을 리스트로 변환한다.
     *
     * @param vo 변환시킬 객체
     * @return 변환된 리스트
     */
    List<Column> transformToList(Table vo);

    /**
     * 객체의 필드값을 리스트로 변환한다.
     *
     * @param voInfo 변환시킬 객체
     * @return 변환된 리스트
     */
    List<Column> transformToList(Class voInfo);

    /**
     * Value가 비어있는 Map 을 생성한다.
     *
     * @param voInfo 변환할 vo 객체의 클래스
     * @return 비어있는 맵
     */
    Map<String, Column> transformToMap(Class voInfo);

    /**
     * 암호화 컬럼인지 확인하는 메소드
     *
     * @param voInfo     확인할 vo 객체의 클래스 파일
     * @param columnName 확인할 컬럼이름
     * @return true : 암호화대상, false: 암호화 대상이 아니다.
     */
    boolean isCrypt(Class voInfo, String columnName);

    /**
     * 리스트로 만들어진 정보를 지정한 vo 객체로 만들어준다.
     *
     * @param voInfo       the vo info
     * @param columnValues the column values
     * @return vo 객체
     */
    Table transform(Class voInfo, List<Column> columnValues);

    /**
     * 맵으로 만들어진 정보를 지정한 vo 객체로 만들어준다.
     *
     * @param voInfo       the vo info
     * @param columnValues the column values
     * @return vo 객체
     */
    Table transform(Class voInfo, Map<String, Column> columnValues);

}
