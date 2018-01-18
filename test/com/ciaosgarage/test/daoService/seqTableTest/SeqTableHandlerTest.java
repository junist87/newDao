package com.ciaosgarage.test.daoService.seqTableTest;

import com.ciaosgarage.newDao.context.Context;
import com.ciaosgarage.newDao.daoService.seqTableHandler.SeqTableHandler;
import com.ciaosgarage.newDao.daoService.seqTableHandler.SeqTableHandlerImpl;
import com.ciaosgarage.newDao.defaultVo.SeqTable;
import com.ciaosgarage.newDao.sqlVo.requsetHandler.MySqlRequestHandler;
import com.ciaosgarage.test.testVo.AccountVO;
import com.ciaosgarage.test.testVo.PhotoVO;
import com.ciaosgarage.test.testVo.TestVO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SeqTableHandlerTest {
    SeqTableHandler tableHandler;

    @Before
    public void setUp() {
        SeqTableHandlerImpl handler = new SeqTableHandlerImpl();
        Context.instance.setDataSource(dataSource());
        handler.setDao(Context.instance.dao);
        handler.setRequestHandler(new MySqlRequestHandler(null));
        handler.setVoHandler(Context.instance.voHandler);
        Context.instance.dao.deleteAll(SeqTable.class);
        tableHandler = handler;
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
    public void getIndex() {
        int testSize = 100; // 테스트 횟수 여기서 조정

        List<Class> testClass = new ArrayList<>();  // 테스트 필드 여기서 추가
        testClass.add(TestVO.class);
        testClass.add(PhotoVO.class);
        testClass.add(AccountVO.class);

        // 발급된 인덱스를 저장하기 위한 리스트
        List<Integer> indexSize = new ArrayList<>();
        for (int i = 0; i < testClass.size(); i++) {
            indexSize.add(0);
        }

        for (int i = 0; i < testSize; i++) {
            System.out.println("+-----------------------------+");
            int index = (int) (Math.random() * testClass.size());

            // 갯수확인
            indexSize.set(index, indexSize.get(index) + 1);

            // pk 값 발급
            String getPk = tableHandler.getPk(testClass.get(index));

            // 화면에 프린트
            System.out.println("table = " + testClass.get(index).getSimpleName() + ", getPk = " + getPk + ", index = " + indexSize.get(index));

            // pk 값 확인
            assertThat(Integer.valueOf(getPk, 16), is(indexSize.get(index)));
        }


    }


}
