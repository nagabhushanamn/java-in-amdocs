

package com.github.chrishantha.sample.memoryref;

import com.github.chrishantha.sample.base.SampleApplication;

import java.io.IOException;

public class MemoryRefApplication implements SampleApplication {

    @Override
    public void start() {
        A a = new A();
        long size = a.getSize();
        System.out.format("The retained heap size of object A is %d bytes (~%d MiB).%n",
                size, (size / (1024 * 1024)));
        long objectSize = JavaAgent.getObjectSize(a);
        if (objectSize > 0) {
            System.out.format("The shallow heap size of object A is %d bytes.%n", objectSize);
        } else {
            System.out.println("WARNING: Java Agent is not initialized properly.");
        }
        Thread backgroundThread = new Thread() {

            private long getSize() {
                G g = new G();
                return g.getSize();
            }

            @Override
            public void run() {
                long size = getSize();
                System.out.format("The size of object allocated within the background thread was %d bytes (~%d MiB).%n",
                        size, (size / (1024 * 1024)));
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundThread.setName("Background Thread");
        backgroundThread.setDaemon(true);
        backgroundThread.start();
        try {
            System.out.println("Press Enter to exit.");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "MemoryRefApplication";
    }
}
