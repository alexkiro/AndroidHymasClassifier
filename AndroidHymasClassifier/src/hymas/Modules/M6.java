package hymas.Modules;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import hymas.motion.m6.classifier.ChangeMotionListener;
import hymas.motion.m6.classifier.Label;
import hymas.motion.m6.classifier.MotionClassifier;
import hymas.motion.m6.classifier.MotionSensorUtils;

/**
 * Punctul de intrare a modulului M6
 * <br/>
 * Permisiunile necesare:
 * <ul>
 * <li>android.permission.ACCESS_FINE_LOCATION</li>
 * <li>android.permission.ACCESS_COARSE_LOCATION</li>
 * <li>android.permission.INTERNET</li>
 * </ul>
 * @author Chirila Alexandru
 */
public class M6 {
    /**
     * Metoda principala a modulului M6. Aceasta incepe procesul de colectare de 
     * date, si regulat aplica clasificatorul pentru a deduce situatia de miscare.
     * @param ctx metoda are nevoie de contextul aplicatiei Android, pentru a avea acces la senzori
     */
    public static void process(Context ctx){
        
        MotionClassifier.getInstance().registerChangeMotionListener(new ChangeMotionListener() {

            public void onChangeMotion(Label oldMotion, Label newMotion) {
                //TODO: add graphical user interface call here
            }
        });
        
        MotionClassifier.getInstance().startClassifying();
        
        MotionSensorUtils.beginListening(ctx, new LocationListener() {

            public void onLocationChanged(Location loc) {
                MotionClassifier.getInstance().addGpsData(
                        loc.getLatitude(),loc.getLongitude(),
                        loc.getAccuracy(),loc.getBearing(), loc.getTime());
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
                MotionClassifier.getInstance().startClassifying();
            }

            public void onProviderDisabled(String provider) {
                MotionClassifier.getInstance().stopClassifying();
            }
        });
    }
}
