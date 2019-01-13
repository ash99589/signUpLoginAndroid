package com.example.ash.igclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class signUp extends AppCompatActivity implements View.OnClickListener {
    private Button btnSave;
    private EditText edtn , edts;
    private TextView txtGd;
    private Button geek;
    private String all;
    private Button btnAA;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(signUp.this);
        edtn = findViewById(R.id.edtn);
        edts = findViewById(R.id.edts);
        txtGd = findViewById(R.id.txtGd);
        geek = findViewById(R.id.geek);
        btnAA = findViewById(R.id.btnAA);

        txtGd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Boxer");
                parseQuery.getInBackground("1BOKTPfzJb", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null){

                            txtGd.setText(object.get("name") + " - " + "punch speed:" + object.get("p_speed"));

                        }
                    }
                });

            }
        });

        geek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                all = "";
                ParseQuery<ParseObject> querryAll = ParseQuery.getQuery("Boxer");
                querryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {


                        if (e == null){

                            if (objects.size() > 0){
                                for (ParseObject boxer : objects){

                                    all = all + boxer.get("name") + "\n";
                                }
                                FancyToast.makeText(signUp.this, all, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();


                            } else{
                                FancyToast.makeText(signUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


                            }
                        }
                    }
                });
            }
        });

        btnAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUp.this , signUpLoginActivity.class);
                startActivity(intent);

            }
        });



    }
    @Override
    public void onClick(View view) {

        final ParseObject obj = new ParseObject("Boxer");
        obj.put("p_speed" , Integer.parseInt(edts.getText().toString()));
        obj.put("name", edtn.getText().toString());
        obj.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {

                    FancyToast.makeText(signUp.this, obj.get("name") + "is saved to the server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                } else {
                    FancyToast.makeText(signUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                }
            }
        });



    }

    }




