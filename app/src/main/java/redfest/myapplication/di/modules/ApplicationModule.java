package redfest.myapplication.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import redfest.myapplication.MyApplication;
import redfest.myapplication.di.ApplicationContext;

/**
 * Created by usman on 3/19/18.
 */

@Module
public class ApplicationModule {
    private MyApplication application;


    public ApplicationModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    public Context provideContext() {
        return application.getApplicationContext();
    }

}
