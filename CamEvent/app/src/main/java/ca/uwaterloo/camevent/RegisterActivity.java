package ca.uwaterloo.camevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    UserDBHandler userDB = new UserDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void goToSignInPage(View v) {
        final int maxPasswordLength = 16;
        final EditText et_username = (EditText) findViewById(R.id.editText_username);
        final EditText et_password = (EditText) findViewById(R.id.editText_password);
        final EditText et_cmPassword = (EditText) findViewById(R.id.editText_cmPassword);
        //Button b_register = (Button) findViewById(R.id.button_register);

        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        String cmPassword = et_cmPassword.getText().toString();
        String email_regex = "^[a-z0-9A-Z]+[a-z0-9A-Z_-]*@[a-z0-9A-Z]+[a-z0-9A-Z\\.-]*\\.[a-z0-9A-Z]+$";
        if(username.isEmpty() || password.isEmpty() || cmPassword.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Some fields are empty. You should fill out all the fields.",
                    Toast.LENGTH_SHORT).show();
        } else if(!username.matches(email_regex)) {
            Toast.makeText(getApplicationContext(),
                    "Invalid email address. Please try again!",
                    Toast.LENGTH_SHORT).show();
        } else if(password.length() > maxPasswordLength) {
            Toast.makeText(getApplicationContext(),
                    "Your password is too long. The maximum length of password is 16 characters. Try again!",
                    Toast.LENGTH_SHORT).show();
        }else if(password.equals(cmPassword)) {
            Intent goToSignInActivity = new Intent(RegisterActivity.this, SignInActivity.class);
            if(userDB.getUsernamesList().contains(username)) {
                Toast.makeText(getApplicationContext(),
                        "The username has been registered. Try again!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Userinfo user = new Userinfo(username, password);
                userDB.addUserinfo(user);
                RegisterActivity.this.startActivity(goToSignInActivity);
                finish();
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "The passwords you've typed are not matched. Try again!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

