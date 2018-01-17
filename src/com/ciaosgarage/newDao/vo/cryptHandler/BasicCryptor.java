package com.ciaosgarage.newDao.vo.cryptHandler;

public class BasicCryptor extends SHA256Cryptor {
    private String key;

    public BasicCryptor(String key) {
        if (key.length() < 16) {
            throw new RuntimeException("보안키는 16자의 String 값이 필요합니다");
        }
        this.key = key;
        super.makeKey(key);
    }
}
