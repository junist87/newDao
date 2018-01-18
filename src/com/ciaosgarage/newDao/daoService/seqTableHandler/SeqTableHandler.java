package com.ciaosgarage.newDao.daoService.seqTableHandler;

/**
 * Primary Key를 관리하는 클래스
 */
public interface SeqTableHandler {
    /**
     * 시퀀스테이블에 접근하여 primary Key 를 발급하는 메소드
     *
     * @param voInfo 발급받을 테이블을 담당하는 vo 객체의 클래스
     * @return 새로 발급된 primary Key
     */
    String getPk(Class voInfo);
}
