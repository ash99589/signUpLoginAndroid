package com.example.ash.igclone;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class signUpLoginActivity extends AppCompatActivity {
    private EditText edtsu , edtsup , edtli , edtlip;
    private Button btnsu , btnli;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup_login_activity);
        edtsu = findViewById(R.id.edtsu);
        edtsup = findViewById(R.id.edtsup);
        edtli = findViewById(R.id.edtli);
        edtlip = findViewById(R.id.edtlip);
        btnsu = findViewById(R.id.btnsu);
        btnli = findViewById(R.id.btnli);

        btnsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtsu.getText().toString());
                appUser.setPassword(edtsup.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(signUpLoginActivity.this, appUser.get("username") + " is aigned up to the server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intent = new Intent(signUpLoginActivity.this , WelcomeActivity.class);
                            startActivity(intent);

                        }else{
                            FancyToast.makeText(signUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });

        btnli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logInInBackground(edtli.getText().toString(), edtlip.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){
                            FancyToast.makeText(signUpLoginActivity.this, user.get("username") + " is logged in to the server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intent = new Intent(signUpLoginActivity.this , WelcomeActivity.class);
                            startActivity(intent);

                        } else {
                            FancyToast.makeText(signUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });

            }
        });

    }
}
