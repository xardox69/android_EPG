package redfest.myapplication.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import redfest.myapplication.MyApplication;
import redfest.myapplication.di.ApplicationContext;
import redfest.myapplication.di.modules.ApplicationModule;
import redfest.myapplication.network.ApiHelper;

/**
 * Created by usman on 3/19/18.
 */

    @Singleton
    @Component(modules=ApplicationModule.class)
    public interface ApplicationComponent {

        void inject(MyApplication app);

        @ApplicationContext
        Context context();

        ApiHelper providesApiHelper();


    }
