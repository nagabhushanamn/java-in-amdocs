
package com.github.chrishantha.sample.memoryref;

import java.util.Random;

public abstract class Base {

    private final byte[] bytes;
    private static final Random random = new Random();

    Base() {
        bytes = new byte[(10 * (1 + random.nextInt(10))) * 1024 * 1024];
    }

    public long getSize() {
        return JavaAgent.getObjectSize(this) + JavaAgent.getObjectSize(bytes);
    }
}
