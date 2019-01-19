package com.example.ash.igclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class signUp extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUsername , edtSIgnUpEmail , edtSignUpP;
    private Button btnSignUp , btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");
        edtUsername = findViewById(R.id.edtUsername);
        edtSIgnUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpP = findViewById(R.id.edtSIgnUpP);

        edtSignUpP.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCOde, KeyEvent event) {
                if (keyCOde == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignUp);
                }
                return false;
            }
        });
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if (ParseUser.getCurrentUser()!=null){
           // ParseUser.getCurrentUser().logOut();
            TransitionToAnotherActivity();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnSignUp:
                if (edtUsername.getText().toString().equals("") || edtSignUpP.getText().toString().equals("") ||
                        edtSIgnUpEmail.getText().toString().equals("")){
                    FancyToast.makeText(signUp.this, "email, username and password are required",
                            Toast.LENGTH_SHORT, FancyToast.INFO, true).show();



                } else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtSIgnUpEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtSignUpP.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("signing up " + edtUsername.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(signUp.this, appUser.getUsername() + " is signed up successfully",
                                        Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                TransitionToAnotherActivity();

                            } else {
                                FancyToast.makeText(signUp.this, "There was an error! " + e.getMessage(),
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.btnLogin:
                Intent intent = new Intent(signUp.this , LogInActivity.class);
                startActivity(intent);
                break;
        }


    }
    public void rootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void TransitionToAnotherActivity(){
        Intent intent = new Intent(signUp.this, SocialMediaActivity.class);
        startActivity(intent);
    }

}




