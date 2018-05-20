package redfest.myapplication.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

import static redfest.myapplication.utils.AppConstants.RESPONSE_FILENAME;

/**
 * Created by usman on 3/22/18.
 */

public class ResponseUtil {


    public static String loadJSONFromAsset(final Context context ) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(RESPONSE_FILENAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
