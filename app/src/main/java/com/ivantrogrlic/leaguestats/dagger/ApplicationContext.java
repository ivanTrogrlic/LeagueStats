package com.ivantrogrlic.leaguestats.dagger;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** Created by ivanTrogrlic on 19/07/2017. */
@Qualifier
@Retention(RUNTIME)
public @interface ApplicationContext {}
