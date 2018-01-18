package com.ciaosgarage.newDao.daoService;

import com.ciaosgarage.newDao.daoService.dao.Dao;
import com.ciaosgarage.newDao.daoService.seqTableHandler.SeqTableHandler;
import com.ciaosgarage.newDao.sqlVo.requsetHandler.RequestHandler;
import com.ciaosgarage.newDao.vo.Vo;
import com.ciaosgarage.newDao.voHandler.VoHandler;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;


public class DaoServiceImpl implements DaoService {

    VoHandler voHandler;
    SeqTableHandler seqTableHandler;
    Dao dao;

    public void setVoHandler(VoHandler voHandler) {
        this.voHandler = voHandler;
    }

    public void setSeqTableHandler(SeqTableHandler seqTableHandler) {
        this.seqTableHandler = seqTableHandler;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Override
    public Vo pullVo(RequestHandler request) throws EmptyResultDataAccessException {
        return dao.get(request.getVoInfo(), request.getStatements());
    }

    @Override
    public List pullVoList(RequestHandler request) throws EmptyResultDataAccessException {
        return dao.getList(request.getVoInfo(), request.getStatements());
    }

    @Override
    public int pushVo(Vo vo) {
        if (vo.getPk() == null) {
            String getPk = seqTableHandler.getPk(vo.getClass());
            vo = voHandler.setPk(vo, getPk);
            return dao.add(vo);
        } else {
            return dao.update(vo);
        }
    }
}
