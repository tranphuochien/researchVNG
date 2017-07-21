/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.root.demoapp.presentation.view;

import com.example.root.demoapp.data.model.Friend;

import java.util.Collection;


public interface FriendListView extends LoadDataView {

  void renderUserList(Collection<Friend> userModelCollection);

  void viewUser(Friend userModel);
}
