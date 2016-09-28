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
                    "First argument determines number of threads and second argument determines number of requests");

        try {
            maxRequestCount = Integer.parseInt(args[1]);
            totalThreads = Integer.parseInt(args[0]);

            mutualExclusionService = MutualExclusionService.TOURNAMENT;
            ThreadController threadController = new ThreadController();
            threadController.start();

            mutualExclusionService = MutualExclusionService.TAS;
            threadController = new ThreadController();
            threadController.start();

            mutualExclusionService = MutualExclusionService.TTAS;
            threadController = new ThreadController();
            threadController.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

enum MutualExclusionService {
    TOURNAMENT, TAS,TTAS
}