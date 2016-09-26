/**
 * Created by Srikanth on 9/18/2016.
 */
public class ApplicationRunner {
    private static int maxRequestCount;
    private static MutualExclusionService mutualExclusionService;
    private static int totalThreads;

    public static int getMaxRequestCount() {
        return maxRequestCount;
    }

    public static int getTotalThreads() {
        return totalThreads;
    }

    public static MutualExclusionService getMutualExclusionService() {
        return mutualExclusionService;
    }

    public static void main(String[] args) {
        if (args.length != 2)
            System.out.print("Invalid Arguments passed. Please pass two arguments. " +
                    "First argument either 0(Tournament) or 1(TAS) or 2(TTAS) and second argument as number of threads to run");

        try {
            maxRequestCount = Helper.REQUEST_COUNT;
            mutualExclusionService = args[0].equals(Helper.TOURNAMENT_CODE) ? MutualExclusionService.TOURNAMENT
                    : args[0].equals(Helper.TAS_CODE) ? MutualExclusionService.TAS : MutualExclusionService.TTAS;

            totalThreads = Integer.parseInt(args[1]);

            Application application = new Application();
            application.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

enum MutualExclusionService {
    TOURNAMENT, TAS,TTAS
}