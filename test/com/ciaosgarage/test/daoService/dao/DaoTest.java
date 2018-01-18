package com.ciaosgarage.test.daoService.dao;

import com.ciaosgarage.newDao.context.Context;
import com.ciaosgarage.newDao.daoService.dao.Dao;
import com.ciaosgarage.newDao.sqlVo.attachStmt.ASWhere;
import com.ciaosgarage.newDao.voHandler.VoHandler;
import com.ciaosgarage.test.testVo.TestVO;
import com.ciaosgarage.test.testVo.TestVoSampler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class DaoTest {
    DataSource dataSource;
    Dao dao;
    TestVoSampler sampler;

    @Before
    public void setUp() {
        sampler = new TestVoSampler();
        Context.instance.setDataSource(dataSource());
        dao = Context.instance.dao;
        dao.deleteAll(TestVO.class);
    }

    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://junist.synology.me:3307/newDao" + "?characterEncoding=UTF-8");
        dataSource.setUsername("ciaolee87");
        dataSource.setPassword("june2002");
        return dataSource;
    }

    @Test
    public void insertAndGetAndUpdate() {
        VoHandler voHandler = Context.instance.voHandler;
        int testSize = 100;
        List<TestVO> list = insertValue(testSize);


        // 입력한 데이터중 임의의 데이터를 뽑아 입력한값과 출력한 값이 동일한지 확인한다.
        // 0.5 배수 확인
        for (int i = 0; i < testSize / 2; i++) {
            int index = (int) (Math.random() * testSize);
            TestVO vo = list.get(index);

            // 데이터를 pk 기준으로 추출한다.
            ASWhere asWhere = new ASWhere(TestVO.class);
            asWhere.addAndValue("pk", String.valueOf(index));

            TestVO getVo = (TestVO) dao.get(TestVO.class, Arrays.asList(asWhere));

            isSameVo(vo, getVo, true);

        }


        // 입력한 데이터 중 임의의 데이터를 뽑아 수정한후 입력, 그리고 다시 추출하여 그 값을 검사한다

        for (int i = 0; i < testSize / 2; i++) {
            // 임의의 데이터를 추출한다
            int index = (int) (Math.random() * testSize);
            TestVO vo = list.get(index);

            // 데이터를 수정한다
            TestVO editVo = sampler.getTestVo();
            vo.lat = editVo.lat;
            vo.nickname = editVo.nickname;
            vo.age = editVo.age;
            vo.name = editVo.name;

            // 데이터베이스 수정사항을 저장한다.
            dao.update(vo);

            // 데이터를 pk 기준으로 추출한다.
            ASWhere asWhere = new ASWhere(TestVO.class);
            asWhere.addAndValue("pk", String.valueOf(index));
            TestVO getVo = (TestVO) dao.get(TestVO.class, Arrays.asList(asWhere));

            // 두 데이터를 비교한다
            isSameVo(vo, getVo, false);

        }

    }


    private void isSameVo(TestVO vo1, TestVO vo2, Boolean ageTest) {
        // createDate는 비교하지 않는다. 데이터베이스 입력시 자동지정
        // pk 값도 비교하지 않는다.

        System.out.println("+-----------------------+");
        System.out.println("pk : " + vo1.getPk() + ", " + vo2.getPk());
        System.out.println("age : " + vo1.age + ", " + vo2.age);
        System.out.println("name : " + vo1.name + ", " + vo2.name);
        System.out.println("nickname : " + vo1.nickname + ", " + vo2.nickname);
        System.out.println("lat : " + vo1.lat + ", " + vo2.lat);

        if (ageTest) assertThat(vo1.age, is(vo2.age));
        assertThat(vo1.name, is(vo2.name));
        assertThat(vo1.nickname, is(vo2.nickname));
        assertThat(vo1.lat, is(vo2.lat));
    }

    private List<TestVO> insertValue(int size) {
        List<TestVO> list = new ArrayList<>();
        VoHandler voHandler = Context.instance.voHandler;
        int testSize = 100;

        // 임의의 100개의 데이터를 입력한다.
        for (int i = 0; i < testSize; i++) {
            TestVO vo = sampler.getTestVo();
            voHandler.setPk(vo, String.valueOf(i)); // pk는 인덱스 값과 동일하게 지저한다. 규칙!
            dao.add(vo);
            list.add(vo);
            assertThat(dao.count(TestVO.class), is(i + 1));
        }

        return list;
    }


    @Test
    public void delete() {
        int testSize = 100;
        List<TestVO> list = insertValue(testSize);

        // 임의로 추출하여 삭제한다
        // 입력한 데이터중 임의의 데이터를 뽑아 입력한값과 출력한 값이 동일한지 확인한다.
        // 0.5 배수 확인
        for (int i = 0; i < (testSize / 2); i++) {
            int index = (int) (Math.random() * testSize);
            TestVO vo = list.get(index);

            // 삭제한다.
            int delete = dao.delete(vo);
            System.out.println("삭제될 레코드 pk :" + vo.getPk());
            System.out.println("삭제된 레코드 수 : " + delete );
            // 중복된 숫자가 나오면 i값을 뒤로 한번 돌리고 다음 pk 값으로 넘어간다
            if (delete == 0) {
                i -= 1;
                continue;
            }

            assertThat(dao.count(TestVO.class), is(list.size() - (i + 1))); // 테스트 횟수만큼 데이터 크기가 줄어든다.

            // 값을 찾아보고 예외가 발생하지 않으면 실패
            try {
                // 데이터를 pk 기준으로 추출한다.
                ASWhere asWhere = new ASWhere(TestVO.class);
                asWhere.addAndValue("pk", String.valueOf(index));
                TestVO getVo = (TestVO) dao.get(TestVO.class, Arrays.asList(asWhere));
                fail(); // 데이터가 찾아지면 삭제되지 않은것 이므로 실험실패
            } catch (EmptyResultDataAccessException e) {
            }

        }
    }
}
