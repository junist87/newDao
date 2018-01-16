package com.ciaosgarage.test.voHandler;

import com.ciaosgarage.test.TestVo;
import com.ciaosgarage.newDao.exceptions.CantAccessFieldException;
import com.ciaosgarage.newDao.vo.*;
import com.ciaosgarage.newDao.voHandler.VoHandler;
import com.ciaosgarage.newDao.voHandler.VoHandlerImpl;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/*
 *
 * 테스트 메소드
 *
 * void setPk(Vo vo, Object value) throws CantAccessFieldException;
 * Object getValue(Vo vo, String columnName) throws CantAccessFieldException;
 * void setValue(Vo vo, String columnName, Object value) throws CantAccessFieldException;
 * RwType getRwType(Class voInfo, String columnName) throws CantAccessFieldException;
 * List<Column> transformToList(Vo vo) throws CantAccessFieldException;
 * List<Column> transformToList(Class voInfo) throws CantAccessFieldException;
 * Map<String, Column> transformToMap(Class voInfo);
 * boolean isCrypt(Class voInfo, String columnName);
 * Vo transformToVo(List<Column> columnValues) throws CantConstructVoException;
 * Vo transformToVo(Map<String, Column> columnValues) throws CantConstructVoException;
 *
 */

public class VoHandlerTest {
    VoHandler voHandler;

    @Before
    public void setUp() {
        voHandler = new VoHandlerImpl();
        assertNotNull(voHandler);
    }

    @Test
    public void setPk() {

        TestVo vo = new TestVo();
        String pk = "ABCDE";

        // 객체는 기본적으로 pk를 가지지 않는다
        assertNull(vo.getPk());

        // pk 지정하기
        voHandler.setPk(vo, pk);
        assertThat(vo.getPk(), is(pk));

        // 두번쨰 시도
        pk = "DAEDG";
        voHandler.setPk(vo, pk);
        assertThat(vo.getPk(), is(pk));

    }

    @Test
    public void getValue() {
        TestVo vo = new TestVo();
        String name = "Jun";
        Integer age = 32;
        String nickname = "Ciao";

        vo.name = name;
        vo.age = age;
        vo.nickname = nickname;

        // 컬럼 내용들 확인
        assertThat(voHandler.getValue(vo, "name"), is(name));
        assertThat(voHandler.getValue(vo, "age"), is(age));
        assertThat(voHandler.getValue(vo, "nickname"), is(nickname));
    }

    @Test(expected = CantAccessFieldException.class)
    public void getValueException() {
        TestVo vo = new TestVo();

        // 존재하지 않는 컬럼이름 적용
        voHandler.getValue(vo, "ABCD");
    }


    @Test
    public void setValue() {
        TestVo vo = new TestVo();
        String name = "Jun";
        Integer age = 32;
        String nickname = "Ciao";

        // 값 지정하기
        voHandler.setValue(vo, "name", name);
        voHandler.setValue(vo, "age", age);
        voHandler.setValue(vo, "nickname", nickname);

        // 컬럼 내용들 확인
        assertThat(voHandler.getValue(vo, "name"), is(name));
        assertThat(voHandler.getValue(vo, "age"), is(age));
        assertThat(voHandler.getValue(vo, "nickname"), is(nickname));
    }

    @Test(expected = CantAccessFieldException.class)
    public void setValueException() {
        TestVo vo = new TestVo();
        // 존재하지 않는 필드 입력
        voHandler.setValue(vo, "ADEd", "AG");
    }


    @Test
    public void transformToList() {

        TestVo tempVo = new TestVo();

        List<Column> list = voHandler.transformToList(tempVo);
        assertThat(list.size(), is(6));
        this.isExistField(list, "pk");
        this.isExistField(list, "name");
        this.isExistField(list, "nickname");
        this.isExistField(list, "age");
        this.isExistField(list, "createDate");
        this.isExistField(list, "lat");
    }

    @Test
    public void transformToMapWithClass() throws Exception{
        Map<String, Column> voMap = voHandler.transformToMap(TestVo.class);

        assertThat(voMap.size(), is(6));
        assertThat(voMap.get("pk").getVoInfo().getSimpleName(), is("TestVo"));

    }

    private void printVo(List<Column> list) {

        for (Column column : list) {
            System.out.println(column.getColumnName());
        }
    }

    private void isExistField(List<Column> list, String fieldName) {
        for (Column column : list) {
            if (column.getColumnName().equals(fieldName)) return;
        }
        fail();
    }

    @Test
    public void transformToListVo() {

        String name = "Jun";
        String nickname = "Ciao";
        Integer age = 32;
        Date createDate = Date.valueOf("1987-9-24");
        Double lat = 123.2321;

        TestVo tempVo = new TestVo();
        tempVo.name = name;
        tempVo.nickname = nickname;
        tempVo.age = age;
        tempVo.createDate = createDate;
        tempVo.lat = lat;


        // 컬럼 확인
        List<Column> list = voHandler.transformToList(tempVo);
        assertThat(list.size(), is(6));
        this.isExistField(list, "pk");
        this.isExistField(list, "name");
        this.isExistField(list, "nickname");
        this.isExistField(list, "age");
        this.isExistField(list, "createDate");
        this.isExistField(list, "lat");


        // 값 확인
        this.isCorrectFieldValue(list, "pk", null);
        this.isCorrectFieldValue(list, "name", name);
        this.isCorrectFieldValue(list, "nickname", nickname);
        this.isCorrectFieldValue(list, "age", age);
        this.isCorrectFieldValue(list, "createDate", createDate);
        this.isCorrectFieldValue(list, "lat", lat);


    }

    private void isCorrectFieldValue(List<Column> list, String fieldName, Object value) {
        for (Column column : list) {
            if (column.getColumnName().equals(fieldName)) {
                // 두 값이 널이라면 참이므로 리턴
                if ((column.getValue() == null) && (value == null)) return;
                // 두값이 틀리면 테스트 실패
                if (!column.getValue().equals(value)) fail();
                return;
            }
        }
        fail();
    }


    @Test
    public void transformToMap() {
        // 컬럼 확인
        Map<String, Column> list = voHandler.transformToMap(TestVo.class);
        assertThat(list.size(), is(6));
        assertNotNull(list.get("pk"));
        assertNotNull(list.get("name"));
        assertNotNull(list.get("nickname"));
        assertNotNull(list.get("age"));
        assertNotNull(list.get("createDate"));
        assertNotNull(list.get("lat"));
    }

    @Test
    public void transformToVoList() {
        // 객체 생성
        TestVo testVo = new TestVo();

        String name = "Lee";
        String nickname = "CiaoLee";
        Integer age = 32;
        Date date = Date.valueOf("2018-9-24");

        testVo.name = name;
        testVo.nickname = nickname;
        testVo.age = age;
        testVo.createDate = date;

        // 리스트로 변환
        List<Column> list = voHandler.transformToList(testVo);

        // 도메인으로 변환
        TestVo cvtVo = (TestVo) voHandler.transformToVo(list);

        // 값확인
        assertThat(cvtVo.name, is(name));
        assertThat(cvtVo.nickname, is(nickname));
        assertThat(cvtVo.age, is(age));
        assertThat(cvtVo.createDate, is(date));
    }


    @Test
    public void transformToVoMap() {
        // 객체 생성
        TestVo testVo = new TestVo();

        String name = "Lee";
        String nickname = "CiaoLee";
        Integer age = 32;
        Date createDate = Date.valueOf("1987-09-24");


        // 맵 변환
        Map<String, Column> map = voHandler.transformToMap(TestVo.class);

        // 데이터 입력
        map.get("name").setValue(name);
        map.get("nickname").setValue(nickname);
        map.get("age").setValue(age);
        map.get("createDate").setValue(createDate);

        // 도메인으로 변환
        TestVo cvtVo = (TestVo) voHandler.transformToVo(map);

        // 값확인
        assertThat(cvtVo.name, is(name));
        assertThat(cvtVo.nickname, is(nickname));
        assertThat(cvtVo.age, is(age));
        assertThat(cvtVo.createDate, is(createDate));
    }

    /*
     * Vo transformToVo(Map<String, Column> columnValues) throws CantConstructVoException;
     */


}
