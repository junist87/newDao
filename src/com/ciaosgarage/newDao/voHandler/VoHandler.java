package com.ciaosgarage.newDao.voHandler;

import com.ciaosgarage.newDao.exceptions.CantAccessFieldException;
import com.ciaosgarage.newDao.exceptions.CantConstructVoException;
import com.ciaosgarage.newDao.exceptions.CantFindVoInfoException;
import com.ciaosgarage.newDao.vo.Column;
import com.ciaosgarage.newDao.vo.RwType;
import com.ciaosgarage.newDao.vo.Vo;

import java.util.List;
import java.util.Map;

/**
 * The interface Vo handler.
 */
public interface VoHandler {


    /**
     * vo 객체 내부에 primaryKey 가 존재 한다면, 그 필드에 값을 입력한다.
     *
     * @param vo    입력할 vo 객체
     * @param value 입력할 값
     * @throws CantAccessFieldException the cant access field exception
     */
    Vo setPk(Vo vo, Object value) throws CantAccessFieldException;

    /**
     * 특정한 필드의 값을 가져온다
     *
     * @param vo         the vo
     * @param columnName the com.ciaosgarage.com.test.column name
     * @return the value
     * @throws CantAccessFieldException the cant access field exception
     */
    Object getValue(Vo vo, String columnName) throws CantAccessFieldException;

    /**
     * 특정한 필드에 값을 입력한다.
     *
     * @param vo         the vo
     * @param columnName the com.ciaosgarage.com.test.column name
     * @param value      the value
     * @throws CantAccessFieldException the cant access field exception
     */
    void setValue(Vo vo, String columnName, Object value) throws CantAccessFieldException;

    /**
     * 객체의 필드값을 리스트로 변환한다.
     *
     * @param vo 변환시킬 객체
     * @return 변환된 리스트
     * @throws CantAccessFieldException the cant access field exception
     */
    List<Column> transformToList(Vo vo) throws CantAccessFieldException;

    /**
     * 객체의 필드값을 리스트로 변환한다.
     *
     * @param voInfo 변환시킬 객체
     * @return 변환된 리스트
     * @throws CantAccessFieldException the cant access field exception
     */
    List<Column> transformToList(Class voInfo) throws CantAccessFieldException;

    /**
     * Column 객체에 Value 가 비어있는 Map 을 생성한다.
     *
     * @param voInfo 변환할 vo 객체의 클래스
     * @return 비어있는 맵
     */
    Map<String, Column> transformToMap(Class voInfo);

    /**
     * Column 객체에 Value 값이 들어있는 Map 을 생성한다.
     *
     * @param vo 변환할 vo
     * @return 비어있는 맵
     */

    Map<String, Column> transformToMap(Vo vo) throws CantAccessFieldException ;

    /**
     * 리스트로 만들어진 정보를 지정한 vo 객체로 만들어준다.
     *
     * @param columns the columns
     * @return vo 객체
     * @throws CantConstructVoException the cant construct vo exception
     */
    Vo transformToVo(List<Column> columns) throws CantConstructVoException;

    /**
     * 맵으로 만들어진 정보를 지정한 vo 객체로 만들어준다.
     *
     * @param columns the columns
     * @return vo 객체
     * @throws CantConstructVoException the cant construct vo exception
     */
    Vo transformToVo(Map<String, Column> columns) throws CantConstructVoException;


    /**
     * 맵 데이터에서 voInfo 를 추출하는 기능을 담당한다.
     *
     * @param voMap Vo 객체의 맵 데이터
     * @return Vo 객체를 이루는 클래스 데이터
     * @throws CantFindVoInfoException Vo 객체의 클래스 데이터가 없을 때 발생하는 예외
     */
    Class getVoInfo(Map<String, Column> voMap) throws CantFindVoInfoException;


    /**
     * 리스트 데이터에서 voInfo 를 추출하는 기능을 담당한다.
     *
     * @param columns Vo 객체의 맵 데이터
     * @return Vo 객체를 이루는 클래스 데이터
     * @throws CantFindVoInfoException Vo 객체의 클래스 데이터가 없을 때 발생하는 예외
     */
    Class getVoInfo(List<Column> columns) throws CantFindVoInfoException;
}
