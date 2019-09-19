
package com.github.chrishantha.sample.memoryref;

public class A extends Base {

    final B b;
    final C c;

    A() {
        b = new B();
        c = new C(b);
    }

    @Override
    public long getSize() {
        return super.getSize() + b.getSize() + c.getSize();
    }
}