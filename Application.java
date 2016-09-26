/**
 * Created by Srikanth on 9/18/2016.
 */
public class Application {
    private ILock iLock;
    private int sharedBox;
    private static int phase = 0;
    private static long startTime, endTime, elapsedTime;

    public Application() {
        iLock = ApplicationRunner.getMutualExclusionService() == MutualExclusionService.TTAS ?
                new TestTestAndSet() : ApplicationRunner.getMutualExclusionService() == MutualExclusionService.TAS ?
                new TestAndSet() : new Tournament();
    }

    public void csLock(ApplicationThread applicationThread) {
        iLock.csLock(applicationThread);
    }

    public void executeCriticalSection() {
        sharedBox++;
    }

    public void csUnlock(ApplicationThread applicationThread) {
        iLock.csUnlock(applicationThread);
    }

    public void start() {
        try {
            Thread[] threads = new Thread[ApplicationRunner.getTotalThreads()];
            timer();
            // start application threads
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(new ApplicationThread(i + 1, this));
                threads[i].start();
            }

            for (Thread thread : threads)
                thread.join();
            timer();
            System.out.print(sharedBox);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Timer to calculate the running time
     */
    public static void timer() {
        if (phase == 0) {
            startTime = System.currentTimeMillis();
            phase = 1;
        } else {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            System.out.println("Time: " + elapsedTime + " msec.");
            memory();
            phase = 0;
        }
    }

    /**
     * This method determines the memory usage
     */
    public static void memory() {
        long memAvailable = Runtime.getRuntime().totalMemory();
        long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
        System.out.println("Memory: " + memUsed / 1000000 + " MB / " + memAvailable / 1000000 + " MB.");
    }
}
