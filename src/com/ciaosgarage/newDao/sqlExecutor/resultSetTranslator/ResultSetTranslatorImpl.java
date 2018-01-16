package com.ciaosgarage.newDao.sqlExecutor.resultSetTranslator;

import com.ciaosgarage.newDao.vo.Column;
import com.ciaosgarage.newDao.vo.Vo;
import com.ciaosgarage.newDao.voHandler.VoHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ResultSetTranslatorImpl implements ResultSetTranslator {

    VoHandler voHandler;


    public void setVoHandler(VoHandler voHandler) {
        this.voHandler = voHandler;
    }

    @Override
    public Vo translate(ResultSet resultSet, Class voInfo) throws SQLException {
        //추출된 resultSet 에 들어있는 컬럼의 갯수구하기 -> for 문 돌리기 위해서
        int columns = resultSet.getMetaData().getColumnCount();

        Map<String, Column> voMap = voHandler.transformToMap(voInfo);

        // 레코드셋의 인덱스는 1~ 컬럼카운트 까지!
        for (int col = 1; col <= columns; col++) {
            // 추출할 값의 정보를 가져온다.
            String columnName = resultSet.getMetaData().getColumnName(col);

            // 입력할 값을 가져온다
            String value = resultSet.getString(col);

            // 가져온 값을 맵에 기록한다.
            Column targetColumn = voMap.get(columnName);
            targetColumn.setResultSetValue(value);
        }

        // 맵 데이터를 기반으로 vo 객체를 생성한다.
        Vo newVo = voHandler.transformToVo(voMap);
        return newVo;
    }

}