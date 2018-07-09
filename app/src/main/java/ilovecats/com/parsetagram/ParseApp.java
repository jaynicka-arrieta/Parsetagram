package ilovecats.com.parsetagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //sets up parse server
        final Parse.Configuration config = new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("jaynicka-parse")
                .clientKey("artemooblue")
                .server("http://parsetagram-ja.herokuapp.com/parse")
                .build();
        Parse.initialize(config);

    }
}
