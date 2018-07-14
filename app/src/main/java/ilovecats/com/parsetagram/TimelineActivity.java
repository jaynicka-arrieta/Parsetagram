package ilovecats.com.parsetagram;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

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
        bottomNav();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTimelineAsync(0);
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        rvPosts = (RecyclerView) findViewById(R.id.rvPosts);
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

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        postAdapter.clear();
        populateTimeline();
        swipeContainer.setRefreshing(false);
    }

    public void bottomNav() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
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
