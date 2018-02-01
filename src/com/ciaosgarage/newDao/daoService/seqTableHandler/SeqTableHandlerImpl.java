package com.ciaosgarage.newDao.daoService.seqTableHandler;


import com.ciaosgarage.newDao.daoService.dao.Dao;
import com.ciaosgarage.newDao.defaultVo.SeqTable;
import com.ciaosgarage.newDao.sqlVo.requsetHandler.RequestHandler;
import com.ciaosgarage.newDao.voHandler.VoHandler;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.UUID;

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
        String pk;

        do {
            pk = UUID.randomUUID().toString();
        } while (isDuplicated(pk));

        // 정보저장
        savePk(pk, voInfo.getSimpleName());

        // pk값 리턴
        return pk;
    }

    private void savePk(String pk, String targetName) {
        // uuid 발급후 데이터 저장
        SeqTable seqTable = new SeqTable();
        voHandler.setPk(seqTable, pk);
        seqTable.targetName = targetName;

        dao.add(seqTable);
    }

    private boolean isDuplicated(String uuid) {
        requestHandler.reset();
        requestHandler.search("pk", uuid);

        try {
            dao.get(requestHandler.getVoInfo(), requestHandler.getColumnStmt(), requestHandler.getAttachStmt());
            // 값이 존재하면 true
            return true;
        } catch (EmptyResultDataAccessException e) {
            // 값이 존재하지 않으면 false
            return false;
        }

    }

//    @Override
//    public String getPk(Class voInfo) {
//        int newPk = getLast() + 1;
//        String targetName = voInfo.getSimpleName();
//        int newTargetPk = lastTargetPk(targetName) + 1;
//
//
//        SeqTable seqTable = new SeqTable();
//        voHandler.setPk(seqTable, Long.toString(newPk, 16));
//        seqTable.primaryKey = newPk;
//        seqTable.targetPk = newTargetPk;
//        seqTable.targetName = targetName;
//
//        dao.add(seqTable);
//        return Integer.toString(newTargetPk, 16);
//    }
//
//    private int getLast() {
//        requestHandler.reset();
//        requestHandler.orderBy("primaryKey", false);
//        requestHandler.numberOf(1);
//        try {
//            SeqTable getLast = (SeqTable) dao.get(SeqTable.class, requestHandler.getColumnStmt(), requestHandler.getAttachStmt());
//            return getLast.primaryKey;
//        } catch (EmptyResultDataAccessException e) {
//            return 0;
//        }
//    }
//
//    private int lastTargetPk(String targetName) {
//        requestHandler.reset();
//        requestHandler.search("targetName", targetName);
//        requestHandler.orderBy("targetPk", false);
//        requestHandler.numberOf(1);
//
//        try {
//            SeqTable getLast = (SeqTable) dao.get(SeqTable.class, requestHandler.getColumnStmt(), requestHandler.getAttachStmt());
//            return getLast.targetPk;
//        } catch (EmptyResultDataAccessException e) {
//            return 0;
//        }
//
//    }
}
