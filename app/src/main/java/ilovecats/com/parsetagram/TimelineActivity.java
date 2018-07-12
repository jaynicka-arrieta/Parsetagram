package ilovecats.com.parsetagram;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

import ilovecats.com.parsetagram.model.Post;

public class TimelineActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;
    Post.Query postQuery;

    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_timeline);

        rvPosts = (RecyclerView) findViewById(R.id.rvPost);
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(posts);

        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        rvPosts.setAdapter(postAdapter);
        populateTimeline();

    }

    private void populateTimeline() {
        postQuery = new Post.Query();
        postQuery.getTop().withUser();

        postQuery.getQuery(Post.class).findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    for (int i = objects.size() - 1; i >= 0; i--) {
                        try {
                            Log.d("CameraActivity", "Post[" + i +
                                    "] = " + objects.get(i).getDescription() +
                                    "\nusername = " + objects.get(i).getUser().fetchIfNeeded().getUsername());
                            Post post = objects.get(i);
                            posts.add(post);
                            postAdapter.notifyItemInserted(posts.size()-1);
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });

    }

}
