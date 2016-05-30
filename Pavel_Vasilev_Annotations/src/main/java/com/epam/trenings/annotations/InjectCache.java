package com.epam.trenings.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Pol on 5/28/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectCache {
    String name();
}
