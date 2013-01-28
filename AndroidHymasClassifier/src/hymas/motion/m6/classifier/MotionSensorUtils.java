package hymas.motion.m6.classifier;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;

/**
 *
 * @author Chirila Alexandru
 */
public abstract class MotionSensorUtils {
    public static void beginListening(Context ctx, LocationListener ll) {
        LocationManager lm = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        if (lm.getProvider(LocationManager.GPS_PROVIDER) != null) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
        } else if (lm.getProvider(LocationManager.NETWORK_PROVIDER) != null) {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ll);
        } else {
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, ll);
        }
    }
}
