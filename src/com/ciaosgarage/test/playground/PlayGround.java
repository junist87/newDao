package com.ciaosgarage.test.playground;

import org.junit.Test;

public class PlayGround {
    @Test
    public void test() {
        Long a = Long.MAX_VALUE;

        System.out.println(a);
        System.out.println(Long.toString(a, 16));
    }
}
