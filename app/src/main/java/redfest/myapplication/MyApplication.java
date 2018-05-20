package redfest.myapplication;

import android.app.Application;

import redfest.myapplication.di.components.ApplicationComponent;
import redfest.myapplication.di.components.DaggerApplicationComponent;
import redfest.myapplication.di.modules.ApplicationModule;

/**
 * Created by usman on 3/19/18.
 */

public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}
