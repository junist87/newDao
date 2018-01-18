package sqlHandler.mapperMaker;

import com.ciaosgarage.newDao.context.Context;
import com.ciaosgarage.newDao.vo.cryptHandler.Cryptor;
import com.ciaosgarage.newDao.sqlHandler.sqlMapperMaker.SqlMapperMaker;
import com.ciaosgarage.newDao.sqlHandler.sqlMapperMaker.SqlMapperMakerImpl;
import com.ciaosgarage.newDao.sqlVo.attachStmt.ASWhere;
import com.ciaosgarage.newDao.vo.Column;
import com.ciaosgarage.newDao.voHandler.VoHandler;
import org.junit.Before;
import org.junit.Test;
import testVO.TestVO;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqlMapperMakerTest {
    private Map<String, Column> voMap;

    private int columnSize = 6;

    private String pk = "ABCD";
    private String name = "Junee";
    private Integer age = 32;
    private String nickname = "Ciao";
    private Timestamp createDate = Timestamp.valueOf("2018-1-1 00:00:00");
    private Double lat = 1.423;

    private String cnName = "name";
    private String cnAge = "age";
    private String cnNickname = "nickname";
    private String cnCreateDate = "createDate";
    private String cnLat = "lat";
    private String cnPk = "pk";

    private SqlMapperMaker mapperMaker;

    @Before
    public void setUp() {
        // 테스트용 vo 객체 생성
        TestVO vo = new TestVO();
        vo.name = name;
        vo.age = age;
        vo.nickname = nickname;
        vo.createDate = createDate;
        vo.lat = lat;

        // pk 입력
        VoHandler voHandler = Context.instance.voHandler;
        voHandler.setPk(vo, pk);

        // 테스트 준비물
        this.voMap = Context.instance.voHandler.transformToMap(vo);

        // 객체준비
        mapperMaker = new SqlMapperMakerImpl();
    }


    @Test
    public void getVoMapper() {
        Map<String, Object> mapper = mapperMaker.makeMapper(voMap);
        Cryptor cryptor = Context.instance.cryptor;

        // 모든 컬럼의 갯수는 6개
        assertThat(mapper.size(), is(columnSize));

        // 컬럼 내부값 확인
        assertThat(mapper.get(cnPk), is(pk));
        assertThat(mapper.get(cnAge), is(age));
        assertThat(mapper.get(cnCreateDate), is(createDate));
        assertThat(mapper.get(cnLat), is(lat));
        assertThat(mapper.get(cnName), is(name));
        assertThat(mapper.get(cnNickname), is(cryptor.encryption(nickname))); //닉네임은 암호화 되어서 출력된다.
    }

    @Test
    public void getAttachStmtMapper() {
        Double critLat1 = 132.2;
        Double critLat2 = 321.232;
        String critName = "ADAB";
        Integer critAge = 42;
        String critNickname = "CiaoLee";
        String encryptedNickname = Context.instance.cryptor.encryption(critNickname);

        ASWhere asWhere = new ASWhere(TestVO.class);
        asWhere.addRangeValue("lat",critLat1, critLat2);
        asWhere.addAndValue("name", critName);
        asWhere.addOrValue("age", critAge);
        asWhere.addOrValue("nickname", critNickname);

        Map<String, Object> mapper = mapperMaker.makeMapper(Arrays.asList(asWhere));
        assertThat(mapper.size(), is(5));
        assertThat(mapper.get("ASWhere0"), is(critLat1));
        assertThat(mapper.get("ASWhere1"), is(critLat2));
        assertThat(mapper.get("ASWhere2"), is(critName));
        assertThat(mapper.get("ASWhere3"),is(critAge));
        assertThat(mapper.get("ASWhere4"), is(encryptedNickname));

    }
}
