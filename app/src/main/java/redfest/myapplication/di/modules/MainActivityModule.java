package redfest.myapplication.di.modules;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import redfest.myapplication.MainActivity;
import redfest.myapplication.di.ObserveOn;
import redfest.myapplication.di.PerActivity;
import redfest.myapplication.di.SubscribeOn;
import redfest.myapplication.interactor.NetworkInteractor;
import redfest.myapplication.interactor.NetworkInteractorImpl;
import redfest.myapplication.network.ApiHelper;
import redfest.myapplication.presenter.EPGPresenter;
import redfest.myapplication.presenter.EPGPresenterImpl;
import redfest.myapplication.view.EPGView;

/**
 * Created by usman on 3/19/18.
 */



@Module
public class MainActivityModule {
    private final MainActivity activity;


    public MainActivityModule (MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public MainActivity providesMainActivity(){
        return  activity;
    }

    @Provides
    @PerActivity
    public EPGView providesMainView(){
        return  activity;
    }


    @Provides
    @PerActivity
    public EPGPresenter providesMainPresenter(NetworkInteractor interactor){
        return new EPGPresenterImpl(interactor);
    }

    @Provides
    @ObserveOn
    Scheduler providesObserveOnScheduler(){
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @SubscribeOn
    Scheduler providesSubscribeOnScheduler(){
        return Schedulers.newThread();
    }





    @Provides
    @PerActivity
    NetworkInteractor providesInteractor(ApiHelper apiHelper, @SubscribeOn Scheduler subscriberScheduler,
                                         @ObserveOn Scheduler observerScheduler){
        return new NetworkInteractorImpl(apiHelper,subscriberScheduler,observerScheduler);
    }


}
