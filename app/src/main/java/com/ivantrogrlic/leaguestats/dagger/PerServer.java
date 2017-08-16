package com.ivantrogrlic.leaguestats.dagger;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** Created by ivanTrogrlic on 14/07/2017. */
@Scope
@Retention(RUNTIME)
public @interface PerServer {}
