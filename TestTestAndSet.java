import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Srikanth on 9/18/2016.
 */
public class TestTestAndSet implements ILock {

    private AtomicBoolean lock;

    public TestTestAndSet() {
        System.out.println("Running Test-Test-And-Set");
        lock = new AtomicBoolean(false);
    }

    @Override
    public void csLock(ApplicationThread applicationThread) {

        while (true) {
            while(lock.get()) ;
            if (!lock.getAndSet(true))
                return;
        }
    }

    @Override
    public void csUnlock(ApplicationThread applicationThread) {
        lock.set(false);
    }
}
