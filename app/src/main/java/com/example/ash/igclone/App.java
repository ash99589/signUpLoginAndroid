package com.example.ash.igclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("0HOHuoCC6ZjIiuxw8fZNtwYZyJyDJF59GG0IFbkA")
                // if defined
                .clientKey("Aew3M0EeWrMkL7u5ClkFnT0kkIDd42UhkueDG44Y")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
