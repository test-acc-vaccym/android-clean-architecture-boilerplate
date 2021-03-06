/*
 * Copyright 2017 Felipe Joglar Santos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fjoglar.android.boilerplate.data.source.remote;

import android.support.annotation.Nullable;

import com.fjoglar.android.boilerplate.data.source.DataSource;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Concrete implementation of a remote data source that adds a latency simulating network.
 */
public class RemoteDataSource implements DataSource {

    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;

    @Nullable
    private static RemoteDataSource INSTANCE = null;

    // Prevent direct instantiation.
    private RemoteDataSource() {

    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @return the {@link RemoteDataSource} instance
     */
    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance()} to create a new instance next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<String> getWelcomeMessage() {

        final String WELCOME_MESSAGE = "Hello World!";

        return Observable.just(WELCOME_MESSAGE)
                         .delay(SERVICE_LATENCY_IN_MILLIS, TimeUnit.MILLISECONDS);
    }
}