package redfest.myapplication.interactor;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import redfest.myapplication.di.ObserveOn;
import redfest.myapplication.di.SubscribeOn;
import redfest.myapplication.network.ApiHelper;
import redfest.myapplication.network.models.EPGResponse;
import redfest.myapplication.presenter.EPGPresenter;

import static redfest.myapplication.utils.AppConstants.API_CALL_DELAY;

/**
 * Created by usman on 3/19/18.
 */

public class NetworkInteractorImpl implements NetworkInteractor {
    private NetworkInteractor.OnFinishedListener listener;
    ApiHelper apiHelper;
    Scheduler subscriberScheduler;
    Scheduler observerScheduler;


    @Inject
    public NetworkInteractorImpl(ApiHelper apiHelper,@SubscribeOn Scheduler subscriberScheduler,
                                 @ObserveOn Scheduler observerScheduler) {
        this.apiHelper = apiHelper;
        this.subscriberScheduler = subscriberScheduler;
        this.observerScheduler = observerScheduler;
    }

    @Override
    public void fetchEPGData(final OnFinishedListener listener) {
        if(this.listener == null ) {
            this.listener = listener;
        }

        if(subscriberScheduler  == Schedulers.trampoline()){
            apiHelper.getEPG().subscribeOn(subscriberScheduler).observeOn(observerScheduler).subscribeWith(observer);
        }else {

            apiHelper.getEPG().subscribeOn(subscriberScheduler).delay(API_CALL_DELAY, TimeUnit.MILLISECONDS, false).observeOn(observerScheduler).subscribeWith(observer);
        }

    }

    public Observer<EPGResponse> observer = new Observer<EPGResponse>() {

        @Override
        public void onSubscribe(Disposable d) {
            listener.onApiStart();
        }

        @Override
        public void onNext(EPGResponse response) {
            listener.onApiSuccess(response);
        }

        @Override
        public void onError(Throwable e) {
            listener.onApiError();
        }

        @Override
        public void onComplete() {
            listener.onApiEnd();
        }
    };
}
