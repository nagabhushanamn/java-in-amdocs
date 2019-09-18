

package com.github.chrishantha.sample.memoryref;

public class C extends Base {

    final E e;
    final F f;

    C(B b) {
        this.e = b.e;
        f = new F();
    }

    @Override
    public long getSize() {
        return super.getSize() + f.getSize();
    }

}
