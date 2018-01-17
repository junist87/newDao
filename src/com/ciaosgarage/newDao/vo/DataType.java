package com.ciaosgarage.newDao.vo;

// 자바 도메인에서 쓰이는 데이터 타입
public enum DataType {
    INTEGER, STRING, DOUBLE, DATE, LONG, TIMESTAMP;

    static DataType getDataType(String dataType) {
        switch (dataType.toLowerCase()) {
            case "integer":
                return INTEGER;
            case "string":
                return STRING;
            case "double":
                return DOUBLE;
            case "date":
                return DATE;
            case "long":
                return LONG;
            case "timestamp":
                return TIMESTAMP;
            default:
                throw new RuntimeException("Wrong Datatype : " + dataType);
        }
    }
}
