package redfest.myapplication.presenter;

import redfest.myapplication.view.EPGView;

/**
 * Created by usman on 3/19/18.
 */

public interface EPGPresenter {

    /**
     * Binds view to presenter
     * @param view the epg view
     */
    void bindView(EPGView view);
    void unbindView();

    /**
     * API call to get EPG data
     */
    void getEPG();
}
