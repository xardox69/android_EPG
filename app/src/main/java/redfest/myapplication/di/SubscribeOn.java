package redfest.myapplication.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by usman on 3/22/18.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface SubscribeOn {
}
