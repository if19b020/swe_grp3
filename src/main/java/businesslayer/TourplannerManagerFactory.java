package businesslayer;

public class TourplannerManagerFactory {
  private static TourplannerManager manager;

    public static TourplannerManager getManager() {
        if (manager == null) {
            manager = new TourPlannerManagerFunc();
        }
        return manager;
    }
}
