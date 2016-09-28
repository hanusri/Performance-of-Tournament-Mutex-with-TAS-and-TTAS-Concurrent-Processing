/**
 * Created by Srikanth on 9/18/2016.
 */
public class ApplicationThread implements Runnable {

    private int threadId;
    public int[] petersonThreadId;
    private ThreadController threadController;

    public ApplicationThread(int threadId, ThreadController threadController) {
        this.threadController = threadController;
        petersonThreadId = new int[Helper.log(2, ApplicationRunner.getTotalThreads()) + 1];
        this.threadId = threadId;
    }

    public void setApplicationRunner(ThreadController threadController) {
        this.threadController = threadController;
    }

    @Override
    public void run() {
        try {
            //Thread.sleep(Helper.Thread_INITIAL_DELAY);

            for (int i = 0; i < ApplicationRunner.getMaxRequestCount(); i++) {
                threadController.csLock(this);
                threadController.executeCriticalSection();
                threadController.csUnlock(this);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getThreadId() {
        return threadId;
    }

}
