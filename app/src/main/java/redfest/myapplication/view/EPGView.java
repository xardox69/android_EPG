package redfest.myapplication.view;

import java.util.ArrayList;

import redfest.myapplication.network.models.EPGChannel;
import redfest.myapplication.network.models.EPGResponse;

/**
 * Created by usman on 3/19/18.
 */

public interface EPGView {

    /**
     * Called t populate EPG data
     * @param response EPGResponse
     */
    void populateEPGView(EPGResponse response);

    /**
     * Adds timeline to EPG view
     */
    void addTimelinetoEpg();

    /**
     * Adds programs to channels in EPGView
     * @param channels
     */
    void addProgramsToEPG(ArrayList<EPGChannel> channels);

    /**
     * Adds a day value to EPGView
     * @param day
     */
    void addDayToEPG(String day);


    /**
     * Adds channels returned by the api call
     * @param channels
     */
    void addChannelsToEpg(ArrayList<EPGChannel> channels);

    /**
     * Shows loading while making network call
     */
    void showLoading();

    /**
     * Called when the call is finished
     */
    void dismissLoading();

    /**
     * Shows error message if there is network error
     */
    void showAPIError();



}
