package redfest.myapplication.interactor;

import redfest.myapplication.network.models.EPGResponse;
import redfest.myapplication.presenter.EPGPresenter;

/**
 * Created by usman on 3/19/18.
 */

public interface NetworkInteractor {

    /**
     * makes network call
     * @param listener
     */
    void fetchEPGData(OnFinishedListener listener);


    interface OnFinishedListener {

        void onApiStart();

        void onApiEnd();

        void onApiSuccess(EPGResponse response);

        void onApiFail();

        void onApiError();

    }
}
