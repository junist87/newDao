package com.ciaosgarage.newDao.sqlVo.attachStmt;

import com.ciaosgarage.newDao.context.Context;
import com.ciaosgarage.newDao.exceptions.CantFindColumnException;
import com.ciaosgarage.newDao.vo.Column;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ASWhere implements AttachStmt {

    private Map<String, Column> voMap;
    private List<Column> mapper;
    private int mapperIndex = 0;    // 맵퍼의 고유 인덱스값
    private int critIndex = 0;      // 입력된 검색할 조건의 갯수
    private final String preFix = "ASWhere";
    private StringBuffer sql;

    public ASWhere(Class voInfo) {
        this.voMap = Context.instance.voHandler.transformToMap(voInfo);
        this.mapper = new ArrayList<>();
        this.sql = new StringBuffer("WHERE ");
    }

    @Override
    public String getStatement() {
        return sql.toString();
    }

    @Override
    public List<Column> getMapper() {
        return mapper;
    }

    public void addAndValue(String columnName, Object value) {
        Column column = this.getValue(columnName, value);
        critIndex += 1;
        this.makeSql(Comp.AND, Oper.EQUAL, column);
    }

    public void addOrValue(String columnName, Object value) {
        Column column = this.getValue(columnName, value);
        critIndex += 1;
        this.makeSql(Comp.OR, Oper.EQUAL, column);
    }

    public void addRangeValue(String columnName, Object start, Object end) {
        Column column1 = getValue(columnName, start);
        Column column2 = getValue(columnName, end);
        critIndex += 1;
        makePrefixRangeValueSql(PreFix.BETWEEN, Comp.AND, Oper.AND, column1, column2);
    }


    private void makeSql(Comp comp, Oper oper, Column value) {
        // 첫번째로 오는 값은 Comparator 가 붙지 않는다
        if (critIndex != 1) {
            sql.append(comp.getString());
            sql.append(" ");
        }
        sql.append(value.getColumnName());
        sql.append(" ");
        sql.append(oper.getString());
        sql.append(" :");
        sql.append(value.getMapperName());
        sql.append(" ");
    }

    private void makePrefixRangeValueSql(PreFix preFix, Comp comp, Oper oper, Column value1, Column value2) {
        // 첫번째 벨류와 두번쨰 밸류는 같아야 한다.
        if (!value1.getColumnName().equals(value2.getColumnName()))
            throw new RuntimeException("입력된 두 값이 틀립니다. " + value1.getColumnName() + ", " + value2.getColumnName());

        // 첫번째로 오는 값은 Comparator 가 붙지 않는다
        if (critIndex != 1) {
            sql.append(comp.getString());
            sql.append(" ");
        }
        sql.append(preFix.getString());
        sql.append(" ");
        sql.append(value1.getColumnName());
        sql.append(" :");
        sql.append(value1.getMapperName());
        sql.append(" ");
        sql.append(oper.getString());
        sql.append(" :");
        sql.append(value2.getMapperName());
        sql.append(" ");
    }

    private Column getValue(String columnName, Object value) {
        // 컬럼 정보를 찾는다. 중복을 허용하기 위해서 복제한다
        Column column = voMap.get(columnName).clone();
        if (column == null) throw new CantFindColumnException("컬럼이름이 잘못되었습니다. ColumnName = " + columnName);

        // 컬럼에 값을 입력한다.
        column.setValue(value);

        // 맵퍼이름을 수정한다
        column.setMapperName(getUniqueMapperName());

        // 맵퍼 리스트에 저장한다
        mapper.add(column);

        // 값을 리턴한다
        return column;
    }

    private String getUniqueMapperName() {
        String mapper = this.preFix + String.valueOf(mapperIndex);
        mapperIndex += 1;
        return mapper;
    }

    /* 내부 정의 값들 */

    private enum PreFix {
        NONE(""), BETWEEN("BETWEEN");
        private String string;

        PreFix(String string) {
            this.string = string;
        }

        public String getString() {
            return string;
        }
    }

    private enum Comp {
        AND("AND"), OR("OR");

        private String string;

        Comp(String string) {
            this.string = string;
        }

        public String getString() {
            return string;
        }
    }

    private enum Oper {
        EQUAL("="), LIKE("LIKE"), AND("AND");
        private String string;

        Oper(String string) {
            this.string = string;
        }

        public String getString() {
            return string;
        }
    }

}
