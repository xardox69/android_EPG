package redfest.myapplication;

/**
 * Created by usman on 3/22/18.
 */

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import redfest.myapplication.interactor.NetworkInteractor;
import redfest.myapplication.interactor.NetworkInteractorImpl;
import redfest.myapplication.network.ApiHelper;
import redfest.myapplication.network.models.EPGResponse;
import redfest.myapplication.presenter.EPGPresenter;
import redfest.myapplication.presenter.EPGPresenterImpl;

import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static redfest.myapplication.utils.AppConstants.API_CALL_DELAY;

public class EPGPresenterTest {

    EPGPresenter presenter;

    NetworkInteractor interactor;

    @Mock
    public ApiHelper apiHelper;

    @Mock
    MainActivity activity;

    @Mock
    Context context;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        apiHelper = Mockito.mock(ApiHelper.class,RETURNS_MOCKS);
        NetworkInteractorImpl listingImpl = new NetworkInteractorImpl(apiHelper,
                Schedulers.trampoline(),Schedulers.trampoline());
        interactor = listingImpl;
        presenter = new EPGPresenterImpl(interactor);

    }


    /**
     * Test when the api call is a success, showloading and dismiss loading should be called
     */
    @Test
    public void fetchValidDataShouldLoadIntoView() {

        EPGResponse response = TestResponseGenerator.generateResponse();

        when(apiHelper.getEPG())
                .thenReturn(Observable.just(response));
        presenter.bindView(activity);

        API_CALL_DELAY = 0;
        InOrder inOrder = Mockito.inOrder(activity);
        inOrder.verify(activity, times(1)).showLoading();
        inOrder.verify(activity, times(1)).dismissLoading();

    }


    /**
     * Test to check in case of exception making api call
     */
    @Test
    public void ExceptionApiTest() {
        Exception exception = new Exception();


        when(context.getString(R.string.txt_error))
                .thenReturn("Error occured");
        activity = Mockito.mock(MainActivity.class,RETURNS_MOCKS);
        when(apiHelper.getEPG())
                .thenReturn(Observable.<EPGResponse>error(exception));
        presenter.bindView(activity);

        InOrder inOrder = Mockito.inOrder(activity);
        inOrder.verify(activity, times(1)).showLoading();
        inOrder.verify(activity, times(1)).showAPIError();


    }



}
