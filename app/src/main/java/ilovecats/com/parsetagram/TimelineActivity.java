package ilovecats.com.parsetagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class TimelineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_timeline);
    }
}
