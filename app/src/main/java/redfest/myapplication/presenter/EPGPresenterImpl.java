package redfest.myapplication.presenter;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import redfest.myapplication.interactor.NetworkInteractor;
import redfest.myapplication.network.models.EPGResponse;
import redfest.myapplication.view.EPGView;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by usman on 3/19/18.
 */

public class EPGPresenterImpl implements EPGPresenter , NetworkInteractor.OnFinishedListener {

    public NetworkInteractor interactor;
    private boolean isFirstStart;

    @Nullable
    public  EPGView epgView;


    @Inject
    public EPGPresenterImpl(NetworkInteractor interactor) {
        isFirstStart = true;
        this.interactor = interactor;
    }


    @Override
    public void bindView(EPGView view) {
        checkNotNull(view);
        this.epgView = view;

        if(isFirstStart){
            getEPG();
            isFirstStart = false;
        }
    }

    @Override
    public void unbindView() {
        epgView = null;
    }

    @Override
    public void getEPG() {
        interactor.fetchEPGData(this);
    }

    @Override
    public void onApiStart() {
        if(epgView!=null){
            epgView.showLoading();
        }
    }

    @Override
    public void onApiEnd() {
        if(epgView!=null){
            epgView.dismissLoading();
        }
    }

    @Override
    public void onApiSuccess(EPGResponse response) {
        if(epgView!=null){
            epgView.dismissLoading();
            epgView.populateEPGView(response);
        }

    }

    @Override
    public void onApiFail() {
        if(epgView!=null){
            epgView.showAPIError();
        }
    }

    @Override
    public void onApiError() {
        if(epgView!=null){
            epgView.showAPIError();
        }
    }
}
