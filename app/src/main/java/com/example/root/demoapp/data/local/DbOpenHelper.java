package com.example.root.demoapp.data.local;

import android.content.Context;

import com.example.root.demoapp.data.model.DaoMaster;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;

/**
 * Created by root on 25/07/2017.
 */

public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    public DbOpenHelper(Context context) {
        super(context, "Friends-db");
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

        switch (oldVersion) {
            case 1:
            case 2:
                //db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN "
                // + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
        }
    }
}
