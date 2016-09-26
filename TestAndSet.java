/**
 * Created by Srikanth on 9/18/2016.
 */

import java.util.concurrent.atomic.AtomicBoolean;

public class TestAndSet implements ILock {

    private AtomicBoolean lock;

    public TestAndSet() {
        System.out.println("Running Test-And-Set");
        lock = new AtomicBoolean(false);
    }


    @Override
    public void csLock(ApplicationThread applicationThread) {
        while (lock.getAndSet(true)) ;
        return;
    }

    @Override
    public void csUnlock(ApplicationThread applicationThread) {
        lock.set(false);
    }
}
