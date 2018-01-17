package com.ciaosgarage.test;

public class TestVoSampler {
    private VoSampler voSampler;

    public TestVoSampler() {
        voSampler = new VoSampler();
    }

    public TestVo getTestVo() {
        TestVo vo = new TestVo();

        vo.age = voSampler.getRandom(60);
        vo.name = voSampler.getStringDeniedDuplicated(10);
        vo.nickname = voSampler.getStringAllowedDuplicated(10);
        vo.lat = voSampler.getRandom(10000) * 0.001;

        return vo;
    }
}
