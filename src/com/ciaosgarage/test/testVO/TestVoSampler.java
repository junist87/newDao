package testVO;

public class TestVoSampler {
    private VoSampler voSampler;

    public TestVoSampler() {
        voSampler = new VoSampler();
    }

    public TestVO getTestVo() {
        TestVO vo = new TestVO();

        vo.age = voSampler.getRandom(60);
        vo.name = voSampler.getStringDeniedDuplicated(10);
        vo.nickname = voSampler.getStringAllowedDuplicated(10);
        vo.lat = voSampler.getRandom(10000) * 0.001;

        return vo;
    }
}
