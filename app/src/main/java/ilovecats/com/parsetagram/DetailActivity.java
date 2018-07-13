package ilovecats.com.parsetagram;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_post);

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
}
