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


import com.example.root.demoapp.data.Repository;
import com.example.root.demoapp.data.RepositoryModule;
import com.example.root.demoapp.data.remote.networking.NetworkModule;
import com.example.root.demoapp.presentation.di.modules.ActivityModule;
import com.example.root.demoapp.presentation.di.modules.FriendModule;
import com.example.root.demoapp.presentation.view.fragments.FriendListFragment;
import com.example.root.demoapp.presentation.view.fragments.LoginFragment;

import dagger.Component;


@Component(dependencies = {ApplicationComponent.class}, modules = {
        ActivityModule.class, FriendModule.class, NetworkModule.class, RepositoryModule.class})
public interface FriendComponent extends ActivityComponent {
    void inject(FriendListFragment friendListFragment);
    void inject(LoginFragment loginFragment);
    //void inject(UserDetailsFragment userDetailsFragment);

    Repository getRepository();
}
