package com.ciaosgarage.newDao.sqlHandler.sqlMaker;

import com.ciaosgarage.newDao.sqlVo.attachStmt.AttachStmt;
import com.ciaosgarage.newDao.vo.Column;
import com.ciaosgarage.newDao.vo.RwType;

import java.util.List;
import java.util.Map;

public class SqlMakerImpl implements SqlMaker {

    // 테이블 접두어 기본값
    private String TablePrefix = "tbl";
    // PrimaryKey 컬럼이름 기본값
    private String PrimaryKeyColumnName = "pk";

    // 기본설정값 수정
    public void setTablePrefix(String prefix) {
        TablePrefix = prefix;
    }

    // 기본설정값 수정
    public void setPrimaryKeyColumnName(String primaryKeyColumnName) {
        PrimaryKeyColumnName = primaryKeyColumnName;
    }

    @Override
    public String select(Map<String, Column> voMap, List<AttachStmt> attachStmts) {
        return "SELECT * FROM " + TablePrefix + getTableName(voMap) + " " + getAttachStmtSql(attachStmts);
    }

    @Override
    public String update(Map<String, Column> voMap) {
        return "UPDATE " + TablePrefix + getTableName(voMap) + " SET " + getUpdateValues(voMap) +
                " " + getWherePrimaryKey(voMap);
    }

    @Override
    public String insert(Map<String, Column> voMap) {
        return "INSERT INTO " + TablePrefix + getTableName(voMap) + " " + getInsertValue(voMap);
    }

    @Override
    public String delete(Map<String, Column> voMap) {
        return "DELETE FROM " + TablePrefix + getTableName(voMap) + " " + getWherePrimaryKey(voMap);
    }

    @Override
    public String deleteAll(Map<String, Column> voMap) {
        return "DELETE FROM " + TablePrefix + getTableName(voMap);
    }

    @Override
    public String count(Map<String, Column> voMap) {
        return "SELECT COUNT(*) FROM " + TablePrefix + getTableName(voMap);
    }

    private String getAttachStmtSql(List<AttachStmt> attachStmts) {
        StringBuilder sql = new StringBuilder();
        for (AttachStmt stmt : attachStmts) {
            sql.append(stmt.getStatement());
            sql.append(" ");
        }
        return sql.toString().trim();
    }

    private String getWherePrimaryKey(Map<String, Column> voMap) {
        Column pkColumn = voMap.get(PrimaryKeyColumnName);
        return "WHERE " + pkColumn.getColumnName() + " = :" + pkColumn.getMapperName();
    }

    private String getTableName(Map<String, Column> voMap) {
        return voMap.get(PrimaryKeyColumnName).getVoInfo().getSimpleName();
    }


    private String getUpdateValues(Map<String, Column> voMap) {
        StringBuilder sql = new StringBuilder();
        for (Map.Entry<String, Column> entry : voMap.entrySet()) {
            Column column = entry.getValue();
            // 수정금지 이거나, 입력할 값이 없거나,primary Key 값이라면 다음으로 넘어간다
            if ((!column.getRwType().equals(RwType.EDITABLE))
                    || (column.getColumnName().equals(PrimaryKeyColumnName))
                    || (!column.isExistValue())) continue;

            // Sql 생성
            sql.append(column.getColumnName());
            sql.append(" = :");
            sql.append(column.getMapperName());
            sql.append(", ");
        }
        return sql.substring(0, sql.length() - 2);
    }


    private String getInsertValue(Map<String, Column> voMap) {
        StringBuilder columnNames = new StringBuilder();
        StringBuilder mapperNames = new StringBuilder();

        for (Map.Entry<String, Column> entry : voMap.entrySet()) {
            Column column = entry.getValue();

            // 읽기전용이거나, 입력할 값이 없으면 다음으로 넘긴다
            if (column.getRwType().equals(RwType.READONLY)
                    || (!column.isExistValue())) continue;

            // SQL 을 작성한다
            columnNames.append(column.getColumnName());
            columnNames.append(", ");

            mapperNames.append(":");
            mapperNames.append(column.getMapperName());
            mapperNames.append(", ");
        }

        return "(" + columnNames.substring(0, columnNames.length() - 2)
                + ") VALUES (" + mapperNames.substring(0, mapperNames.length() - 2) + ")";
    }
}