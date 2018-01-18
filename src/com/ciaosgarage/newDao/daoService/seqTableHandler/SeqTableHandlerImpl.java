package com.ciaosgarage.newDao.daoService.seqTableHandler;


import com.ciaosgarage.newDao.daoService.dao.Dao;
import com.ciaosgarage.newDao.defaultVo.SeqTable;
import com.ciaosgarage.newDao.sqlVo.requsetHandler.RequestHandler;
import com.ciaosgarage.newDao.voHandler.VoHandler;
import org.springframework.dao.EmptyResultDataAccessException;

public class SeqTableHandlerImpl implements SeqTableHandler {
    private Dao dao;
    private VoHandler voHandler;
    private RequestHandler requestHandler;

    public void setVoHandler(VoHandler voHandler) {
        this.voHandler = voHandler;
    }

    public void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.requestHandler.setVoInfo(SeqTable.class);
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Override
    public String getPk(Class voInfo) {
        int newPk = getLast() + 1;
        String targetName = voInfo.getSimpleName();
        int newTargetPk = lastTargetPk(targetName) + 1;


        SeqTable seqTable = new SeqTable();
        voHandler.setPk(seqTable, Long.toString(newPk, 16));
        seqTable.primaryKey = newPk;
        seqTable.targetPk = newTargetPk;
        seqTable.targetName = targetName;

        dao.add(seqTable);
        return Integer.toString(newTargetPk, 16);
    }

    private int getLast() {
        requestHandler.reset();
        requestHandler.orderBy("primaryKey", false);
        requestHandler.numberOf(1);
        try {
            SeqTable getLast = (SeqTable) dao.get(SeqTable.class, requestHandler.getColumnStmt(), requestHandler.getAttachStmt());
            return getLast.primaryKey;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    private int lastTargetPk(String targetName) {
        requestHandler.reset();
        requestHandler.search("targetName", targetName);
        requestHandler.orderBy("targetPk", false);
        requestHandler.numberOf(1);

        try {
            SeqTable getLast = (SeqTable) dao.get(SeqTable.class, requestHandler.getColumnStmt(), requestHandler.getAttachStmt());
            return getLast.targetPk;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }

    }
}
