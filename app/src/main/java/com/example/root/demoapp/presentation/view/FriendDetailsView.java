/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.root.demoapp.presentation.view;

import com.example.root.demoapp.data.model.FriendResponse;


public interface FriendDetailsView extends LoadDataView {
  void renderUser(FriendResponse friendResponse);
}
