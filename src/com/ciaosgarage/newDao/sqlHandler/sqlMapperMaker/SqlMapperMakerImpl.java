package com.ciaosgarage.newDao.sqlHandler.sqlMapperMaker;


import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;
import com.ciaosgarage.newDao.vo.Column;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SqlMapperMakerImpl implements SqlMapperMaker {

    @Override
    public Map<String, Object> makeMapper(Map<String, Column> voMap, List<AttachStmt> statements) {
        Map<String, Object> mapper = makeMapper(voMap);
        mapper.putAll(makeMapper(statements));
        return mapper;
    }

    @Override
    public Map<String, Object> makeMapper(Map<String, Column> voMap) {
        Map<String, Object> mapper = new HashMap<>();
        for (Map.Entry<String, Column> entry : voMap.entrySet()) {
            Column column = entry.getValue();

            // 컬럼에 대입할 데이터가 있을때만 추가한다
            if (column.isExistValue()) mapper.put(column.getMapperName(), column.getMapperValue());
        }
        return mapper;
    }

    @Override
    public Map<String, Object> makeMapper(List<AttachStmt> stmts) {
        Map<String, Object> mapper = new HashMap<>();
        for (AttachStmt stmt : stmts) {
            for (Column column : stmt.getMapper()) {

                // 컬럼에 대입할 데이터가 있을때만 추가한다
                if (column.isExistValue()) mapper.put(column.getMapperName(), column.getMapperValue());
            }
        }
        return mapper;
    }
}
