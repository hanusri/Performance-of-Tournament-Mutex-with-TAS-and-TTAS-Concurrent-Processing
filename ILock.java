/**
 * Created by Srikanth on 9/18/2016.
 */
public interface ILock {

    void csLock(ApplicationThread applicationThread);

    void csUnlock(ApplicationThread applicationThread);
}
