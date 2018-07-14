package ilovecats.com.parsetagram;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import ilovecats.com.parsetagram.model.Post;

public class DetailActivity extends AppCompatActivity {

    Post post;
    TextView tvHandle;
    ImageView ivImagePost;
    TextView tvDescription;
    TextView tvTimeStamp;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNav();

        tvHandle = (TextView) findViewById(R.id.tvHandle);
        tvHandle.setTextColor(Color.BLACK);
        ivImagePost = (ImageView) findViewById(R.id.ivImagePost);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvDescription.setTextColor(Color.BLACK);
        tvTimeStamp = (TextView) findViewById(R.id.tvTimeStamp);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra("post"));
        tvHandle.setText(post.getUser().getUsername().toString());
        tvDescription.setText(post.getUser().getUsername() + " - " + post.getDescription().toString());

        String timestamp = post.getCreatedAt().toString().substring(4, 11);
        tvTimeStamp.setText(timestamp);

        GlideApp.with(getApplicationContext())
                .load(post.getImage().getUrl())
                .into(ivImagePost);
    }

    public void bottomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        final Intent i = new Intent(getApplicationContext(), TimelineActivity.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.action_post:
                        final Intent e = new Intent(getApplicationContext(), CameraActivity.class);
                        startActivity(e);
                        finish();
                        return true;
                    case R.id.action_user:
                        final Intent f = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(f);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
}
