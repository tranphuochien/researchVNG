/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.root.demoapp.presentation.view.fragments;

import android.app.Fragment;
import android.widget.Toast;

import com.example.root.demoapp.presentation.di.HasComponent;


public abstract class BaseFragment extends Fragment {
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
