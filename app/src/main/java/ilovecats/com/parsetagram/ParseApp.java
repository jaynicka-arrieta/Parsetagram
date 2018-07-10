package ilovecats.com.parsetagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import ilovecats.com.parsetagram.model.Post;

public class ParseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        //sets up parse server
        final Parse.Configuration config = new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("jaynicka-parse")
                .clientKey("artemooblue")
                .server("http://parsetagram-ja.herokuapp.com/parse")
                .build();
        Parse.initialize(config);

    }
}
