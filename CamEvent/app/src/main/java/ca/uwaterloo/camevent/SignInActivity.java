package ca.uwaterloo.camevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignInActivity extends AppCompatActivity {
    UserDBHandler userDB = new UserDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        //Button b_signIn = (Button) findViewById(R.id.button_signIn);
        Button b_signUp = (Button) findViewById(R.id.button_signUp);
        b_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(SignInActivity.this, RegisterActivity.class);
                SignInActivity.this.startActivity(signUpIntent);
                finish();
            }
        });

    }

    public void goToProfilePage(View v) {
        final EditText et_username = (EditText) findViewById(R.id.editText_username);
        final EditText et_password = (EditText) findViewById(R.id.editText_password);

        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        String email_regex = "^[a-z0-9A-Z]+[a-z0-9A-Z_-]*@[a-z0-9A-Z]+[a-z0-9A-Z\\.-]*\\.[a-z0-9A-Z]+$";
        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Username or password is empty. You should fill out all fields.",
                    Toast.LENGTH_SHORT).show();
        } else if(!username.matches(email_regex)) {
            Toast.makeText(getApplicationContext(),
                    "Invalid email address. Please try again!",
                    Toast.LENGTH_SHORT).show();
        } else {
            String  passwordFromDB = userDB.searchPassword(username);
            if(password.equals(passwordFromDB)) {
                Intent signInIntent = new Intent(SignInActivity.this, MeActivity.class);
                SignInActivity.this.startActivity(signInIntent);
                //finish();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Username and password do not match. Try again!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
