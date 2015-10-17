package Utils;

/**
 * @author martingonzalez
 */
public class Distance {
    
    private static final int EARTH_RADIUS = 6371;

    public static double distance(Coord coordA, Coord coordB) {

        double dLat  = Math.toRadians(coordB.getLatitude() - coordA.getLatitude());
        double dLong = Math.toRadians(coordB.getLongitude() - coordA.getLongitude());

        double startLat = Math.toRadians(coordA.getLatitude());
        double endLat   = Math.toRadians(coordB.getLatitude());

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
