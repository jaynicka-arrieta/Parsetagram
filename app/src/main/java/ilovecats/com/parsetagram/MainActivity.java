package ilovecats.com.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private Button btSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);
        btSignUp = (Button) findViewById(R.id.btnSignUp);


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                login(username, password);

            }
        });

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btSignUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String username = etUsername.getText().toString();
                        final String password = etPassword.getText().toString();

                        if (username.equals("") || password.equals("")) {
                            Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                        } else {
                            signUp(username, password);
                        }
                    }
                });
            }
        });

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            final Intent i = new Intent(getApplicationContext(), TimelineActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    Log.d("MainActivity", "Login succecssful");
                    final Intent i = new Intent(getApplicationContext(), TimelineActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Log.e("MainActivity", "Login failure");
                    Toast.makeText(getApplicationContext(), "Login Failure", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void signUp(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("MainActivity", "Sign up succecssful");
                    final Intent i = new Intent(getApplicationContext(), TimelineActivity.class);
                    Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Username taken", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });
    }
}
