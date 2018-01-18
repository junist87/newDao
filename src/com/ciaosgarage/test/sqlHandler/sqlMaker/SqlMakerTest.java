package sqlHandler.sqlMaker;


import com.ciaosgarage.newDao.sqlHandler.sqlMaker.SqlMaker;
import com.ciaosgarage.newDao.sqlHandler.sqlMaker.SqlMakerImpl;
import com.ciaosgarage.newDao.sqlVo.attachStmt.ASWhere;

import com.ciaosgarage.newDao.context.Context;
import com.ciaosgarage.newDao.sqlVo.columnStmt.CSSelectAll;
import com.ciaosgarage.newDao.vo.Column;
import org.junit.Before;
import org.junit.Test;
import testVO.TestVO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqlMakerTest {
    private Map<String, Column> emptyVoMap;
    private Map<String, Column> voMap;

    private String name = "Junee";
    private Integer age = 32;
    private String nickname = "Ciao";
    private Timestamp date = Timestamp.valueOf("2018-1-1 00:00:00");
    private Double lat = 1.423;

    private SqlMaker sqlMaker;

    @Before
    public void setUp() {
        // 테스트용 vo 객체 생성
        TestVO vo = new TestVO();
        vo.name = name;
        vo.age = age;
        vo.nickname = nickname;
        vo.createDate = date;
        vo.lat = lat;

        Context.instance.voHandler.setPk(vo, "ABD");

        // 테스트 준비물
        this.voMap = Context.instance.voHandler.transformToMap(vo);
        this.emptyVoMap = Context.instance.voHandler.transformToMap(TestVO.class);

        // 객체준비
        sqlMaker = new SqlMakerImpl();
    }

    @Test
    public void select() {
        String sql;
        sql = sqlMaker.select(voMap, new CSSelectAll(), new ArrayList<>());
        System.out.println(sql);

        ASWhere asWhere = new ASWhere(TestVO.class);
        asWhere.addRangeValue("lat", 132.2, 242.2);
        asWhere.addAndValue("name", "ABCD");
        asWhere.addOrValue("age", 11);
        sql = sqlMaker.select(voMap, new CSSelectAll(), Arrays.asList(asWhere));
        assertThat("SELECT * FROM tblTestVO WHERE BETWEEN lat :ASWhere0 AND :ASWhere1 AND name = :ASWhere2 OR age = :ASWhere3", is(sql));
        System.out.println(sql);
    }

    @Test
    public void insert() {
        String sql;
        sql = sqlMaker.insert(voMap);
        assertThat("INSERT INTO tblTestVO (name, nickname, pk, age, lat) VALUES (:name, :nickname, :pk, :age, :lat)", is(sql));
        System.out.println(sql);
    }

    @Test
    public void update() {
        String sql;
        sql = sqlMaker.update(voMap);

        assertThat("UPDATE tblTestVO SET name = :name, nickname = :nickname, lat = :lat WHERE pk = :pk", is(sql));
        System.out.println(sql);
    }

    @Test
    public void delete() {
        String sql;
        sql = sqlMaker.delete(voMap);
        assertThat("DELETE FROM tblTestVO WHERE pk = :pk", is(sql));
        System.out.println(sql);

        sql = sqlMaker.deleteAll(voMap);
        assertThat("DELETE FROM tblTestVO", is(sql));
        System.out.println(sql);
    }
}
