package redfest.myapplication.di.components;

import dagger.Component;
import redfest.myapplication.MainActivity;
import redfest.myapplication.di.PerActivity;
import redfest.myapplication.di.modules.MainActivityModule;

/**
 * Created by usman on 3/19/18.
 */

@Component(dependencies = ApplicationComponent.class,modules=MainActivityModule.class)
@PerActivity
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
