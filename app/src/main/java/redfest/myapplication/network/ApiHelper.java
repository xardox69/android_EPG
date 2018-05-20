package redfest.myapplication.network;

import android.content.Context;
import io.reactivex.Observable;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import redfest.myapplication.di.ApplicationContext;
import redfest.myapplication.network.models.EPGResponse;
import redfest.myapplication.utils.ResponseUtil;

/**
 * Created by usman on 3/19/18.
 */

public class ApiHelper {

    @ApplicationContext
    @Inject
    public Context context;

    private Gson gson;


    @Inject
    public ApiHelper() {
        gson =  new Gson();
    }


    public Observable<EPGResponse> getEPG(){

        EPGResponse myResponse  =   gson.fromJson(ResponseUtil.loadJSONFromAsset(context), EPGResponse.class);

        if(myResponse!=null) {
            //return myResponse;
            return Observable.just(myResponse);
           // Observable.just(myResponse).zipWith(Observable.interval(500, TimeUnit.MILLISECONDS), (response, interval) -> response);
        }else{
            return Observable.just(new EPGResponse());
        }
    }



}
