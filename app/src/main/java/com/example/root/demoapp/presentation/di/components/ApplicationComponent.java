/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
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
package com.example.root.demoapp.presentation.di.components;

import android.app.Application;
import android.content.Context;

import com.example.root.demoapp.presentation.di.modules.ApplicationModule;
import com.example.root.demoapp.presentation.view.activities.BaseActivity;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
    void inject(Application application);

    Context context();
}
