package column;


import com.ciaosgarage.newDao.context.Context;

import com.ciaosgarage.newDao.vo.Column;
import org.junit.Before;
import org.junit.Test;
import testVO.TestVO;

import java.sql.Timestamp;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ColumnTest {

    private Map<String, Column> voMap;
    private Map<String, Column> emptyVoMap;

    private String name = "Junee";
    private Integer age = 32;
    private String nickname = "Ciao";
    private Timestamp height = Timestamp.valueOf("2018-1-1 00:00:00");
    private Double lat = 1.423;


    @Before
    public void setUp() {
        // 테스트용 vo 객체 생성
       TestVO vo = new TestVO();
        vo.name = name;
        vo.age = age;
        vo.nickname = nickname;
        vo.createDate = height;
        vo.lat = lat;

        // 테스트 준비물
        this.voMap = Context.instance.voHandler.transformToMap(vo);
        this.emptyVoMap = Context.instance.voHandler.transformToMap(TestVO.class);
    }

    @Test
    public void mapperName() {
        String targetColumnName = "name";
        String changeColumnName = "ABCD";
        Column column = voMap.get(targetColumnName);
        assertThat(column.getMapperName(), is(targetColumnName));

        column.setMapperName(changeColumnName);
        assertThat(column.getMapperName(), is(changeColumnName));
    }


    @Test
    public void getMapperValue() {
        String encryptedColumnName = "nickname";
        String noCryptedColumnName = "name";

        String encrytedValue = Context.instance.cryptor.encryption(nickname);
        String noCryptedValue = name;

        Column encryptedColumn = voMap.get(encryptedColumnName);
        Column noCryptedColumn = voMap.get(noCryptedColumnName);

        assertThat(encryptedColumn.getMapperValue(), is(encrytedValue));
        assertThat(noCryptedColumn.getMapperValue(), is(noCryptedValue));
    }

    @Test
    public void setResultSetValue() {
        String encryptedColumnName = "nickname";
        String noCryptedColumnName = "name";
        String integerValue = "age";

        Column encryptedColumn = voMap.get(encryptedColumnName);
        Column noCryptedColumn = voMap.get(noCryptedColumnName);
        Column integerColumn = voMap.get(integerValue);

        // 컬럼값에 값 입력

        // 암호화 대상컬럼인 경우 암호값을 넣으면 원래 값이 나와야 한다
        String temporaryStringValue = "DAdaedzdg";
        String encrytedValue = Context.instance.cryptor.encryption(temporaryStringValue);
        encryptedColumn.setResultSetValue(encrytedValue);
        assertThat(encryptedColumn.getValue(), is(temporaryStringValue));

        // 암호화 비대상인 컬럼인 경우 입력한 값이 나와야 한다.
        String noCryptedValue = "badeBD2f";
        noCryptedColumn.setResultSetValue(noCryptedValue);
        assertThat(noCryptedColumn.getValue(), is(noCryptedValue));

        // 암호화 비대상인 컬럼에 데이터형식 틀린 스트링 값을 넣어도 형변환 되서 나와야 한다.
        Integer temporaryIntValue = 2123;
        integerColumn.setResultSetValue(temporaryIntValue.toString());
        assertThat(integerColumn.getValue(), is(temporaryIntValue));
    }



}
