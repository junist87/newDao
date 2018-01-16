package com.ciaosgarage.newDao.sqlExecutor.cryptHandler;

public interface Cryptor {
    String encryption(String str);

    String decryption(String str);
}
