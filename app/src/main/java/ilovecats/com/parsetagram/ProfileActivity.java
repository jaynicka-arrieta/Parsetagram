package ilovecats.com.parsetagram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {

    Button btnLogout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNav();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


    }

    public void logout() {
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
        if (currentUser == null) {
            final Intent i = new Intent(getApplicationContext(), MainActivity.class);
            Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Logout unsuccessful", Toast.LENGTH_SHORT).show();
        }
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
