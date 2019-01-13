package com.rate_your_assignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sushmashapkota.rate_your_assignment.R;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class Profile extends AppCompatActivity {
    // LinearLayout linearLayout;
    public void report(View view) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


// I dont need this but Im just saving it for futire, JustInCase
        Intent i = getIntent();
        final String activeUsername = i.getStringExtra("username"); //activeUsername is the username that you just tapped in
        Log.i("AppInfo", activeUsername);
        setTitle(activeUsername + "\'s Profile"); //Changes the title


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("images");  // Calls the query of images

        query.whereEqualTo("username", activeUsername);
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {
                            ParseFile file = (ParseFile) object.get("image");
                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e == null) {
                                        Bitmap image = BitmapFactory.decodeByteArray(data, 0, data.length);

                                        ImageView imageView = new ImageView((getApplicationContext()));

                                        imageView.setImageBitmap(image);
                                        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                                ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT
                                        ));

                                        //  linearLayout.addView(imageView);

                                    }
                                }
                            });
                        }
                    }
                }
            }
        });


        Button button = (Button) findViewById(R.id.button10); //Button declaration
        final TextView disprating = (TextView) findViewById(R.id.text3);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar2); //Declaring ratingBar
        final EditText editText = (EditText) findViewById(R.id.editText); // Assignment Name

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                disprating.setText("Rating: " + ratingBar.getRating()); //Shows you what your rating is
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Log.i("boiii", "" + v);           //Just to check
                Log.i("boiii", activeUsername); //Just to check
                ParseObject assignments = new ParseObject("Assignments"); // Creates a class name Assignments
                assignments.put("username", activeUsername);                         // Puts the username
                assignments.put("rating", v);                                       //Uploads rating score
                // assignments.put("Assignment", editText.getText());
                assignments.put("points", "" + editText.getText());                   //Points but not used in dashboard

                assignments.saveInBackground(new SaveCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("Parse", "Save Succeeded");         // To give a log or print in
                        } else {
                            Log.i("Parse", "Save Failed");
                        }

                    }
                });


            }
        });


// Toolbar and circle-type.. thing .. We are not using it
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
