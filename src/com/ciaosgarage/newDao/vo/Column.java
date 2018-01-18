package com.ciaosgarage.newDao.vo;

import com.ciaosgarage.newDao.context.Context;
import com.ciaosgarage.newDao.exceptions.CannotDefineDataTypeException;
import com.ciaosgarage.newDao.exceptions.CantCloneColumnInstance;
import com.ciaosgarage.newDao.exceptions.CantFindColumnException;
import com.ciaosgarage.newDao.exceptions.NoValueExcption;
import com.ciaosgarage.newDao.vo.cryptHandler.Cryptor;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;

public class Column implements Cloneable {
    private String columnName;
    private Object value;
    private Boolean crypt;
    private DataType dataType;
    private RwType rwType;
    private Class voInfo;
    private Field columnField;
    private String mapperName;
    private Cryptor cryptor;

    public Column(String columnName, Class voInfo) {
        this.columnName = columnName;
        this.mapperName = columnName;
        this.voInfo = voInfo;
        this.extractField(columnName);
        this.cryptor = Context.instance.cryptor;

    }

    // Tested! 맵퍼 네임이 다를경우 지정하는 메소드
    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    // Tested! 맵퍼 네임 출력
    public String getMapperName() {
        return mapperName;
    }

    // Tested! 맵퍼에 쓰일 값 추출
    public Object getMapperValue() {
        // 값이 바뀔수도 있으므로 계산된 값을 저장하지 않는다.
        if ((this.isCrypt()) && (this.value != null)) {
            String stringValue = String.valueOf(value);
            return this.cryptor.encryption(stringValue);
        } else if (this.value != null) {
            return this.value;
        } else {
            throw new NoValueExcption(columnName + "의 데이터 값이 없습니다. NullPoint Exception");
        }
    }

    // Tested! ResultSet 에서 추출되는 값 입력
    // 스트링값으로 받은 데이터를 암호화 대상은 암호를 디코딩한다
    // 스트링값으로 받은 데이터를 자동으로 형변환 시켜준다.
    public void setResultSetValue(String value) {
        if (isCrypt()) value = this.cryptor.decryption(value);

        // 입력값이 String 이면 변환할지 체크하기
        switch (this.getDataType()) {
            case TIMESTAMP:
                this.value = Timestamp.valueOf(value);
                break;
            case DATE:
                this.value = Date.valueOf(value);
                break;
            case LONG:
                this.value = Long.valueOf(value);
                break;
            case DOUBLE:
                this.value = Double.valueOf(value);
                break;
            case INTEGER:
                this.value = Integer.valueOf(value);
                break;
            case STRING:
                this.value = value;
                break;
            default:
                throw new CannotDefineDataTypeException("데이터형이 정의되지 않습니다 : " + dataType);
        }
    }

    // 해당 컬럼 이름 출력
    public String getColumnName() {
        return columnName;
    }

    // 해당 컬럼의 데이터 출력
    public Object getValue() {
        return value;
    }

    // 해당 컬럼에 데이터 입력
    public void setValue(Object value) {
        this.value = value;
    }

    // 해당 컬럼에 값이 존재 유무 출력
    public boolean isExistValue() {
        return this.value != null;
    }

    // 해당 컬럼임 속해 있는 클래스 출력
    public Class getVoInfo() {
        return voInfo;
    }

    // 해당 컬럼의 데이터형을 출력
    public DataType getDataType() {
        // 구한 값이 존재하면 그 값을 리턴한다
        if (dataType != null) return dataType;

        // 값을 추출한다
        this.dataType = DataType.getDataType(this.columnField.getType().getSimpleName());
        return dataType;

    }

    // 해당 컬럼의 입출력 조건을 출력
    public RwType getRwType() {
        // 구한 값이 존재하면 그 값을 리턴한다
        if (this.rwType != null) return rwType;

        // 값을 추출한다
        DbColumn settings = this.columnField.getAnnotation(DbColumn.class);
        this.rwType = settings.rwType();
        return this.rwType;

    }

    // 컬럼을 복사하여 출력
    public Column clone() throws CantCloneColumnInstance {
        try {
            return (Column) super.clone();
        } catch (CloneNotSupportedException e) {
            // 예외전환
            throw new CantCloneColumnInstance(e);
        }
    }

    // 해당 컬럼이 암호화 대상인지 확인
    private boolean isCrypt() {
        // 추출한 값이 있으면 그 값을 리턴한다
        if (this.crypt != null) return this.crypt;

        // 값을 추출한다
        DbColumn settings = this.columnField.getAnnotation(DbColumn.class);
        this.crypt = (settings.cryptOption() == CryptOption.ON);
        return this.crypt;
    }

    // 컬럼이름으로 객체의 필드정보를 추출한다.
    private void extractField(String columnName) {
        Class loopClass = voInfo;   //  값에 의한 복사
        // 수퍼클래스까지 검색해보기
        while (loopClass != null) {
            try {
                this.columnField = loopClass.getDeclaredField(columnName);
                columnField.setAccessible(true);
                return;
            } catch (NoSuchFieldException ignored) {
            }
            loopClass = loopClass.getSuperclass();
        }

        throw new CantFindColumnException("해당 필드를 찾지 못하였습니다. " + columnName);
    }


}
