
package com.github.chrishantha.sample.memoryref;

public class B extends Base {

    final D d;
    final E e;

    B() {
        d = new D();
        e = new E();
    }

    @Override
    public long getSize() {
        return super.getSize() + d.getSize() + e.getSize();
    }
}
