package com.ciaosgarage.newDao.voHandler;

import com.ciaosgarage.newDao.exceptions.CantAccessFieldException;
import com.ciaosgarage.newDao.exceptions.CantConstructVoException;
import com.ciaosgarage.newDao.exceptions.CantFindVoInfoException;
import com.ciaosgarage.newDao.vo.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoHandlerImpl implements VoHandler {


    @Override
    public Vo setPk(Vo vo, Object value) throws CantAccessFieldException {
        try {
            Field field = this.getField("pk", vo.getClass());
            field.set(vo, value);
            return vo;
        } catch (Exception e) {
            // 예외전환
            throw new CantAccessFieldException(e);
        }
    }

    @Override
    public Object getValue(Vo vo, String columnName) throws CantAccessFieldException {
        try {
            Field field = this.getField(columnName, vo.getClass());
            return field.get(vo);
        } catch (Exception e) {
            // 예외전환
            throw new CantAccessFieldException(e);
        }
    }

    @Override
    public void setValue(Vo vo, String columnName, Object value) throws CantAccessFieldException {
        try {
            Field field = this.getField(columnName, vo.getClass());
            field.set(vo, value);
        } catch (Exception e) {
            // 예외전환
            throw new CantAccessFieldException(e);
        }
    }


    @Override
    public List<Column> transformToList(Vo vo) throws CantAccessFieldException {
        List<Column> list = new ArrayList<>();
        Class voInfo = vo.getClass();

        // 모든 상위 클래스까지 검색
        try {
            while (voInfo != null) {
                for (Field field : voInfo.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (isColumn(field)) {
                        Column column = new Column(field.getName(), voInfo);
                        column.setValue(field.get(vo));
                        list.add(column);
                    }
                }
                voInfo = voInfo.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            // 예외전환
            throw new CantAccessFieldException(e);
        }

        return list;
    }

    @Override
    public List<Column> transformToList(Class voInfo) {
        List<Column> list = new ArrayList<>();

        // 모든 상위 클래스까지 검색
        while (voInfo != null) {
            for (Field field : voInfo.getDeclaredFields()) {
                field.setAccessible(true);
                if (isColumn(field)) list.add(new Column(field.getName(), voInfo));
            }
            voInfo = voInfo.getSuperclass();
        }
        return list;
    }

    @Override
    public Map<String, Column> transformToMap(Class voInfo) {
        Map<String, Column> list = new HashMap<>();
        Class loopClass = voInfo;

        // 모든 상위 클래스까지 검색
        while (loopClass != null) {
            for (Field field : loopClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (isColumn(field)) list.put(field.getName(), new Column(field.getName(), voInfo));

            }
            loopClass = loopClass.getSuperclass();
        }
        return list;
    }

    @Override
    public Map<String, Column> transformToMap(Vo vo) throws CantAccessFieldException {
        Map<String, Column> list = new HashMap<>();
        Class loopClass = vo.getClass();
        Class insertClass = vo.getClass();

        try {
            // 모든 상위 클래스까지 검색
            while (loopClass != null) {
                for (Field field : loopClass.getDeclaredFields()) {
                    field.setAccessible(true);

                    if (isColumn(field)) {
                        Column column = new Column(field.getName(), insertClass);
                        column.setValue(field.get(vo)); // 객체에서 값을 추출하여 컬럼에 입력한다
                        list.put(field.getName(), column);
                    }

                }
                loopClass = loopClass.getSuperclass();
            }
            return list;
        } catch (IllegalAccessException e) {
            throw new CantAccessFieldException(e);
        }
    }

    @Override
    public Vo transformToVo(List<Column> columns) throws CantConstructVoException {
        Class voInfo = this.getVoInfo(columns);
        Vo newVo = getNewVo(voInfo);
        for (Column columnValue : columns) {
            this.setValue(newVo, columnValue.getColumnName(), columnValue.getValue());
        }
        return newVo;
    }

    private boolean isColumn(Field field) {
        return field.isAnnotationPresent(DbColumn.class);
    }

    private Vo getNewVo(Class voInfo) throws CantConstructVoException {
        try {
            // 새로운 객체 만들기 - 기본 생성자로 객체 생성
            Constructor constructor = voInfo.getDeclaredConstructor();
            return (Vo) constructor.newInstance();

        } catch (Exception e) {
            // 예외전환
            throw new CantConstructVoException(e);
        }

    }

    @Override
    public Vo transformToVo(Map<String, Column> columns) {
        Class voInfo = this.getVoInfo(columns);
        Vo newVo = getNewVo(voInfo);
        for (Map.Entry<String, Column> entry : columns.entrySet()) {
            Column column = entry.getValue();
            this.setValue(newVo, column.getColumnName(), column.getValue());
        }
        return newVo;
    }


    @Override
    public Class getVoInfo(Map<String, Column> voMap) throws CantFindVoInfoException {
        for (Map.Entry<String, Column> entry : voMap.entrySet()) {
            Column column = entry.getValue();
            return column.getVoInfo();
        }

        throw new CantFindVoInfoException();
    }

    @Override
    public Class getVoInfo(List<Column> columns) throws CantFindVoInfoException {
        if (columns.size() == 0) throw new CantFindVoInfoException("columns 리스트의 사이즈가 0입니다.");
        return columns.get(0).getVoInfo();
    }

    private Field getField(String fieldName, Class target) throws CantAccessFieldException {
        while (target != null) {
            for (Field field : target.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals(fieldName)) return field;
            }

            target = target.getSuperclass();
        }
        throw new CantAccessFieldException("ERROR - 해당 필드가 없습니다. Field name : " + fieldName);
    }
}
