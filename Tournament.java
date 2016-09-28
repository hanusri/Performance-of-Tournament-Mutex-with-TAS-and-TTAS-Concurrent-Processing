/**
 * Created by Srikanth on 9/18/2016.
 */
public class Tournament implements ILock {
    private int level;
    private int internalThreadLocks;
    private Peterson[] petersonLock;

    public Tournament() {
        System.out.println("Running Tournament");
        level = Helper.log(2, ApplicationRunner.getTotalThreads());
        internalThreadLocks = (int) Math.pow(2, level) - 1;
        petersonLock = new Peterson[internalThreadLocks+1];
        for (int i = 0; i < petersonLock.length; i++)
            petersonLock[i] = new Peterson();
    }


    @Override
    public void csLock(ApplicationThread applicationThread) {
        int tournamentThreadId = applicationThread.getThreadId() + internalThreadLocks;
        for (int i = 1; i <= level; i++) {
            applicationThread.petersonThreadId[i] = tournamentThreadId % 2;
            tournamentThreadId /= 2;
            petersonLock[tournamentThreadId].lock(applicationThread.petersonThreadId[i]);
        }
    }

    @Override
    public void csUnlock(ApplicationThread applicationThread) {
        int tournamentThreadId = 1;
        for (int i = level; i >= 1; i--) {
            petersonLock[tournamentThreadId].unlock(applicationThread.petersonThreadId[i]);
            tournamentThreadId = (2 * tournamentThreadId) + applicationThread.petersonThreadId[i];
        }
    }
}

class Peterson {
    // thread-local index, 0 or 1
    private volatile boolean[] flag = new boolean[2];
    private volatile int victim;


    public void lock(int threadId) {
        int j = 1 - threadId;
        flag[threadId] = true;
        victim = threadId;
        while (flag[j] && victim == threadId) {
        }
    }

    public void unlock(int threadId) {
        flag[threadId] = false;
    }
}
