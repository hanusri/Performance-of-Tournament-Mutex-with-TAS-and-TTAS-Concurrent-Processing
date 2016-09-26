/**
 * Created by Srikanth on 9/18/2016.
 */
public class ApplicationThread implements Runnable {

    private int threadId;
    public int[] petersonThreadId;
    private Application application;

    public ApplicationThread(int threadId, Application application) {
        this.application = application;
        petersonThreadId = new int[Helper.log(2, ApplicationRunner.getTotalThreads())];
        this.threadId = threadId;
    }

    public void setApplicationRunner(Application application) {
        this.application = application;
    }

    @Override
    public void run() {
        try {
            //Thread.sleep(Helper.Thread_INITIAL_DELAY);

            for (int i = 0; i < ApplicationRunner.getMaxRequestCount(); i++) {
                application.csLock(this);
                application.executeCriticalSection();
                application.csUnlock(this);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getThreadId() {
        return threadId;
    }

}
