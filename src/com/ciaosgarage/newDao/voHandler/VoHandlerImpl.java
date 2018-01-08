package com.ciaosgarage.newDao.voHandler;

import com.ciaosgarage.newDao.exceptions.NoExistPrimaryKeyFieldException;
import com.ciaosgarage.newDao.vo.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class VoHandlerImpl implements VoHandler {

    @Override
    public Object getPrimaryKey(Table vo) throws NoExistPrimaryKeyFieldException {
        try {
            String fieldName = getPrimaryKeyFieldName(vo.getClass());
            Field primaryKeyField = vo.getClass().getDeclaredField(fieldName);
            primaryKeyField.setAccessible(true);
            return primaryKeyField.get(vo);
        } catch (Exception e) {
            throw new NoExistPrimaryKeyFieldException("!- Can't access primary key field");
        }
    }

    @Override
    public String getPrimaryKeyColumnName(Table vo) throws NoExistPrimaryKeyFieldException {
        return null;
    }

    @Override
    public String getPrimaryKeyColumnName(Class voInfo) throws NoExistPrimaryKeyFieldException {
        return null;
    }

    @Override
    public void setPrimaryKey(Table vo, Object value) throws NoExistPrimaryKeyFieldException {

    }

    @Override
    public Object getValue(Table vo, String columnName) {
        return null;
    }

    @Override
    public Object setValue(Table vo, String columnName, Object value) {
        return null;
    }

    @Override
    public RwType getRwType(Class voInfo, String columnName) {
        return null;
    }

    @Override
    public List<Column> transformToList(Table vo) {
        return null;
    }

    @Override
    public List<Column> transformToList(Class voInfo) {
        return null;
    }

    @Override
    public Map<String, Column> transformToMap(Class voInfo) {
        return null;
    }

    @Override
    public boolean isCrypt(Class voInfo, String columnName) {
        return false;
    }

    @Override
    public Table transform(Class voInfo, List<Column> columnValues) {
        return null;
    }

    @Override
    public Table transform(Class voInfo, Map<String, Column> columnValues) {
        return null;
    }

    private String getPrimaryKeyFieldName(Class targetClass) {
        while (targetClass != null) {
            for (Field field : targetClass.getDeclaredFields()) {
                if (isPrimaryKey(field)) return field.getName();
            }
            targetClass = targetClass.getSuperclass();
        }
        throw new NoExistPrimaryKeyFieldException();
    }

    private boolean isPrimaryKey(Field field) {
        field.setAccessible(true);
        // 컬럼셋팅 어노테이션이 있다면 키 값을 검색
        if (field.isAnnotationPresent(ColumnConfig.class)) {
            ColumnConfig settings = field.getAnnotation(ColumnConfig.class);
            if (settings.columnType().equals(ColumnType.PRIMARYKEY)) return true;
        }
        return false;
    }

}
