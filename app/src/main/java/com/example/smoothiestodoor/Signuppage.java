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
import com.example.smoothiestodoor.databinding.SignuppageBinding;


public class Signuppage extends AppCompatActivity {

    public static String  PREFS_NAME = "Myuserpref";


    SignuppageBinding signuppageBinding;
    SharedPreferences sharedPreferences;
    static String namest , emailst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        signuppageBinding = SignuppageBinding.inflate(getLayoutInflater());
        View view = signuppageBinding.getRoot();
        setContentView(view);
        sharedPreferences = getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);


        signuppageBinding.buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserEntity userEntity = new UserEntity();
                namest = signuppageBinding.username.getText().toString();
                userEntity.setUsername(namest);
                emailst = signuppageBinding.email.getText().toString();
                userEntity.setEmail(emailst);
                userEntity.setPassword(signuppageBinding.password.getText().toString());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("uname",namest);
                editor.putString("email",emailst);
                editor.apply();

                if(validate(userEntity))
                {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run()
                        {
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run()
                                {
                                    Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_SHORT).show();
                                    editor.putBoolean("hasloggedin",true);
                                    editor.commit();
                                    Intent intent = new Intent(Signuppage.this, Drinksmenulistpage.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            });

                        }
                    }).start();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Fill all feilds", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signuppageBinding.textViewalaccount.setPaintFlags(signuppageBinding.textViewalaccount.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        Intent intent = getIntent();
        String text = "Sign in";
        SpannableString span = new SpannableString(text);
        ClickableSpan click = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(Signuppage.this, Signinpage.class);
                startActivity(intent);
            }
        };
        span.setSpan(click,0,text.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signuppageBinding.textViewmovetosignin.setText(span);
        signuppageBinding.textViewmovetosignin.setMovementMethod((LinkMovementMethod.getInstance()));

    }

    private boolean validate(UserEntity userEntity)
    {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        if(userEntity.getUsername().isEmpty() || userEntity.getPassword().isEmpty()||
                userEntity.getEmail().isEmpty()){
            return false;
        }
        if (!userEntity.getEmail().matches(emailPattern)) {
            Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void openscreen(View v)
    {
        Intent intent=new Intent(this, Drinksmenulistpage.class);
        startActivity(intent);
    }
}