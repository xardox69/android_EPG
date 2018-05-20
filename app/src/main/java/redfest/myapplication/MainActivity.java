package redfest.myapplication;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;


import java.util.ArrayList;

import javax.inject.Inject;

import redfest.myapplication.di.components.DaggerMainActivityComponent;
import redfest.myapplication.di.components.MainActivityComponent;
import redfest.myapplication.di.modules.MainActivityModule;
import redfest.myapplication.network.models.EPGChannel;
import redfest.myapplication.network.models.EPGResponse;
import redfest.myapplication.presenter.EPGPresenter;
import redfest.myapplication.utils.EPGViewUtils;
import redfest.myapplication.view.EPGView;

public class MainActivity extends AppCompatActivity implements EPGView{

    @Inject
    EPGPresenter presenter;

    /* layout to show channels */
    private LinearLayout channelsLayout;

    /* layout to show programs */
    private LinearLayout programsLayout;

    /* network progress */
    private RelativeLayout progressLayout;
    private ScrollView scrollView;

    /*snackbar to show error messages */
    private Snackbar snackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(MainActivity.this))
                .applicationComponent(((MyApplication)getApplication()).getComponent())
                .build();
        mainActivityComponent.inject(this);

        initViews();



    }

    /**
     * Initializes the views in activity
     */
    private void initViews() {
        channelsLayout = findViewById(R.id.channels_layout);
        programsLayout = findViewById(R.id.programs_vertical_layout);
        progressLayout = findViewById(R.id.progress_layout);
        scrollView =  findViewById(R.id.scroll_view);
        snackbar = Snackbar
                .make(findViewById(android.R.id.content), getString(R.string.txt_error), Snackbar.LENGTH_LONG);
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();

    }

    @Override
    public void populateEPGView(EPGResponse response) {
        addTimelinetoEpg();
        addDayToEPG(response.getToday());
        addChannelsToEpg(response.getChannels());
        addProgramsToEPG(response.getChannels());

    }

    @Override
    public void addDayToEPG(String day) {
        EPGViewUtils.addDayToEPG(channelsLayout,this,day);
    }

    @Override
    public void addProgramsToEPG(ArrayList<EPGChannel> channels) {

        EPGViewUtils.addProgramsToEPG(programsLayout,this,channels);
    }

    @Override
    public void addTimelinetoEpg() {

        EPGViewUtils.addTimeLineToEPG(programsLayout,this);
    }

    @Override
    public void addChannelsToEpg(ArrayList<EPGChannel> channels) {

        EPGViewUtils.addChannels(this,channels,channelsLayout);

    }

    @Override
    public void showLoading() {

        progressLayout.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
    }

    @Override
    public void dismissLoading() {

        progressLayout.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAPIError() {
        //TODO: implement view
        snackbar.show();
    }
}
