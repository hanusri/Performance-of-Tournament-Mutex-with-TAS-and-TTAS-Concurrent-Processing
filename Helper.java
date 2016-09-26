/**
 * Created by Srikanth on 9/18/2016.
 */
public class Helper {
    public static final int Thread_INITIAL_DELAY = 1000;
    public static final int REQUEST_COUNT = 10000000;
    public static final String TOURNAMENT_CODE = "0";
    public static final String TAS_CODE = "1";

    public static int ceiledTwoExponent(int n) {
        int result = 1;
        while (result > n)
            result = result << 1;
        return result;
    }

    public static int log(int base, int value) {
        return (int) Math.ceil(Math.log10(value) / Math.log10(2));
    }

}
