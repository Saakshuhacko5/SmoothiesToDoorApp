package com.example.smoothiestodoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smoothiestodoor.databinding.SigninpageBinding;


public class Signinpage extends AppCompatActivity  {
    SharedPreferences sharedPreferences;
    public static String  PREFS_NAME = "Myuserpref";
    SigninpageBinding signinpageBinding;
    static  String st;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        signinpageBinding = SigninpageBinding.inflate(getLayoutInflater());
        View view = signinpageBinding.getRoot();
        setContentView(view);
        st = signinpageBinding.username.getText().toString();
        sharedPreferences = getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        signinpageBinding.buttonsignin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String usernametext = signinpageBinding.username.getText().toString();
                String passwordtext = signinpageBinding.password.getText().toString();
                if(usernametext.isEmpty() || passwordtext.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(usernametext , passwordtext);
                            if(userEntity == null)
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run()
                                    {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else
                            {
                                username = usernametext;
                                editor.putBoolean("hasloggedin",true);
                                editor.apply();
                                Intent intent=new Intent(Signinpage.this, Drinksmenulistpage.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }
                        }
                    }).start();
                }
            }
        });



        signinpageBinding.textViewdontacc.setPaintFlags(signinpageBinding.textViewdontacc.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        Intent intent = getIntent();
        String text = "Sign up";
        SpannableString span = new SpannableString(text);
        ClickableSpan click = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(Signinpage.this, Signuppage.class);
                startActivity(intent);
            }
        };
        span.setSpan(click,0,text.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signinpageBinding.textViewmovetosignup.setText(span);
        signinpageBinding.textViewmovetosignup.setMovementMethod((LinkMovementMethod.getInstance()));
    }

}