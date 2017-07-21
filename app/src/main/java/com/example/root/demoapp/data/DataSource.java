package com.example.root.demoapp.data;

import io.reactivex.disposables.Disposable;

/**
 * Created by root on 21/07/2017.
 */

public interface DataSource {
    Disposable getData();
}
