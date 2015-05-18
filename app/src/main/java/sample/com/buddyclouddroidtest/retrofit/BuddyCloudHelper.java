package sample.com.buddyclouddroidtest.retrofit;

import android.util.Base64;

/**
 * Created by Destri on 5/18/15.
 */
public class BuddyCloudHelper {

    public static String createBasicHttpAuthToken(String user, String password) {
        String auth = user + ":" + password;
        String authToken = Base64.encodeToString(auth.getBytes(),
                Base64.NO_WRAP);
        return "Basic " + authToken;

    }
}
