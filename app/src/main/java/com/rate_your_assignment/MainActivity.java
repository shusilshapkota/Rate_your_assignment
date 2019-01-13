package com.rate_your_assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
    // Declaring Variables
    EditText usernameField; //Username
    EditText passwordField; //Password
    //EditText nameField;
    TextView changeSignUpModeTextView;  //TextView LogIn
    TextView text1;
    Button signUpButton; //Log In button
    Boolean signUpModeActive;
    ImageView logo;
    RelativeLayout layout;
    Switch studentOrTeacherSwitch; //Switch

    public void signUpOrLogIn(View view) {

    }


/*

    @Override
    // When you press the enter button, it calls the function signUpOrLogIn which decides either to sign you up or Log In
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        //If keyCode equals Enter
        if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_UP) {
            //Calls the function
            signUpOrLogIn(view);
        }
        return false;
    }

    @Override
    // If something is clicked
    public void onClick(View view) {
        // changeSignUpMode is the textView where it says LogIn
        if (view.getId() == R.id.changeSignUpMode) {
            if (signUpModeActive == true) { //If textview "LogIn" is pressed
                Log.i("AppInfo", "This one is called");
                signUpModeActive = false; //First thing, it makes it false
                changeSignUpModeTextView.setText("Sign Up"); //Changes text to SignUp
                signUpButton.setText("Log In"); //Changes button text to LogIn
            } else { //Otherwise it does opposite thing
                Log.i("AppInfo", "This is called");
                changeSignUpModeTextView.setText("Log In");
                signUpButton.setText("Sign Up");
                signUpModeActive = true;
            }

            //If user taps on the Layout or the logo, it hides the keyboard
        } else if (view.getId() == R.id.layout || view.getId() == R.id.logo) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0); //Hides from the screen
        }


    }


    //Function of calling UserList class only if teacher is logging in
    public void showUser() {
        Intent i = new Intent(getApplicationContext(), UserList.class); //Changes the target to UserList class where you can see list of users
        startActivity(i); // and it runs
    }

    //Function of calling NewsFeed class if students are logging in
    public void studentsUser() {
        Intent i = new Intent(getApplicationContext(), NewsFeed.class);
        startActivity(i); //And, it runs
    }

    public void signUpOrLogIn(View view) {
     */
/*   Log.i("AppInfo", String.valueOf(usernameField.getText())); //To print the username, just to check if it records your username
        Log.i("AppInfo", String.valueOf(passwordField.getText())); //To print the password, just to check if it records your password

        //studentOrTeacherSwitch is the variable
        if (signUpModeActive == true && studentOrTeacherSwitch.isChecked()) { //If student is logging in and button is tapped
            ParseUser user = new ParseUser();
            user.setUsername(String.valueOf(usernameField.getText())); //Uploads the username that user enters to parse dashboard
            user.setPassword(String.valueOf(passwordField.getText())); //Uploads the password that user enters to parse dashboard
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) { //If there is no error
                        Log.i("AppInfo", "SignUp successful"); //Just to print
                        studentsUser(); //Calls the function studentUser

                    } else {
                        //Gives a user a prompt message
                        Toast.makeText(getApplicationContext(), e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            //String.valueOf is used to get the actual typed string value
            //It just shows the info even though you hit the sign Up button because I don't have restrictions in else statement, if switch is not checked it just sends to log In datas
            String us = String.valueOf(usernameField.getText());
            String pa = String.valueOf(passwordField.getText());
            Log.i("Details", String.valueOf(usernameField.getText()));
            ParseUser.logInInBackground(String.valueOf(usernameField.getText()), String.valueOf(passwordField.getText()), new LogInCallback() {


                @Override

                public void done(ParseUser user, ParseException e) {
                    if (e == null) {
                        Log.i("AppInfo", "Log In Successful");
                        if (studentOrTeacherSwitch.isChecked()) { //If student log In, It shows the profile where students can only upload pictures
                            studentsUser(); //Students Class
                        } else {
                            showUser(); //Teacher Class
                        }
                    } else {
                        // To show if they do not have their accounts
                        if (studentOrTeacherSwitch.isChecked() == false) { //For students
                            Toast.makeText(getApplicationContext(), "Make sure, you're in Log In Mode!!  " + "Please try again! May be data" + e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_LONG).show();
                        } else { //For teachers
                            Toast.makeText(getApplicationContext(), "An error occurred, Please try again! May be data" + e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
*//*

    }


    // Main function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentOrTeacherSwitch = (Switch) findViewById(R.id.studentOrTeacherSwitch); // Declaring the switch of Teacher or Student

        if (ParseUser.getCurrentUser() != null) { // Only loads up if there is no input
            showUser();   // Teacher class
            studentsUser();   // Student's class
        }
        signUpModeActive = true;   // Initializing boolean variable true

        // Declaring username and password Variables
        usernameField = (EditText) findViewById(R.id.username);
        passwordField = (EditText) findViewById(R.id.password);
        // nameField = (EditText) findViewById(R.id.name);
        changeSignUpModeTextView = (TextView) findViewById(R.id.changeSignUpMode);
        text1 = (TextView) findViewById(R.id.text1);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        logo = (ImageView) findViewById(R.id.logo);
        layout = (RelativeLayout) findViewById(R.id.layout);

        // Setting onClickListener so that you could hide the keyBoard when layout or picture is tapped
        logo.setOnClickListener(this);
        layout.setOnClickListener(this);

        changeSignUpModeTextView.setOnClickListener(this);
        usernameField.setOnKeyListener(this);
        passwordField.setOnKeyListener(this);

        //   ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
*/

/*
    // This is where you type code if you want menu or setting, I'm not using setting in my first viewController
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }
}
