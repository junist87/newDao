package com.ciaosgarage.newDao.sqlVo.requsetHandler;


import com.ciaosgarage.newDao.sqlVo.attachStmt.ASLimit;
import com.ciaosgarage.newDao.sqlVo.attachStmt.ASOrderBy;
import com.ciaosgarage.newDao.sqlVo.attachStmt.ASWhere;
import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;

public class MySqlRequestHandler extends RequestHandlerFW {
    ASWhere asWhere;
    ASLimit asLimit;

    public MySqlRequestHandler(Class voInfo) {
        super(voInfo);
    }

    @Override
    public void search(String columnName, Object value) {
        // 중복을 허락하지 않고, 리스트를 비우면 새로 등록되게 하는 장치
        if (isExist(asWhere)) {
            asWhere = new ASWhere(voInfo);
            asWhere.addAndValue(columnName, value);
            list.add(asWhere);
        } else asWhere.addAndValue(columnName, value);
    }

    @Override
    public void sectionSearch(String column, Object start, Object end) {
        // 중복을 허락하지 않고, 리스트를 비우면 새로 등록되게 하는 장치
        if (isExist(asWhere)) {
            asWhere = new ASWhere(voInfo);
            asWhere.addRangeValue(column, start, end);
            list.add(asWhere);
        } else asWhere.addRangeValue(column, start, end);
    }

    @Override
    public void orderBy(String columnName, boolean ascending) {
        // 중복을 허락한다
        list.add(new ASOrderBy(columnName, ascending));

    }

    @Override
    public void numberOf(int size) {
        if (isExist(asLimit)) {
            asLimit = new ASLimit(size);
            list.add(asLimit);
        } else asLimit.setUnit(size);
    }

    public void limit(int unit, int startIndex) {
        if (isExist(asLimit)) {
            asLimit = new ASLimit(startIndex, unit);
            list.add(asLimit);
        } else asLimit.setValue(startIndex, unit);
    }

    private boolean isExist(AttachStmt stmt) {

        return ((stmt == null) || (!list.contains(stmt)));
        /*
            stmt 가 null 이면 무조건 생성
            리스트에 객체가 없으면 무조건 생성
         */
    }
}
