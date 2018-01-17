package com.ciaosgarage.newDao.vo.cryptHandler;

public interface Cryptor {
    String encryption(String str);

    String decryption(String str);
}
