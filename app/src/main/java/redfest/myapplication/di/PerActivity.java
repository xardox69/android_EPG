package redfest.myapplication.di;

/**
 * Created by usman on 3/19/18.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
