package com.example.smoothiestodoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Selectionofingredients extends AppCompatActivity {


    static ArrayList<String> display2 = new ArrayList<String>();
    static  String fselect = "";
    static int count = 0;
    Button plus;
    Button minus;
    TextView textView;
    Intent intent;
     TextView textone;
     TextView texttwo;
     TextView textthree;
     TextView textfour;
     TextView textfive;
     TextView textsix;
     TextView totalamount;
     CheckBox checkBox1;
     CheckBox checkBox2;
     CheckBox checkBox3;
     CheckBox checkBox4;
     CheckBox checkBox5;
     CheckBox checkBox6;
     String st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectionofingredients);
        Intent intent = getIntent();
        st = getIntent().getStringExtra("fruitname");
        plus = findViewById(R.id.increase);
        minus = findViewById(R.id.decrease);
        textView = findViewById(R.id.integer_number);
        totalamount = findViewById(R.id.totalamount);
        textone = findViewById(R.id.textone);
        texttwo = findViewById(R.id.texttwo);
        textthree = findViewById(R.id.textthree);
        textfour = findViewById(R.id.textfour);
        textfive = findViewById(R.id.textsix);
        textsix = findViewById(R.id.texteight);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);



        plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count = count + 1;
                        textView.setText(Integer.toString(count));
                        int n = 50*count;
                        String s = n+"USD";
                        totalamount.setText(s);

                    }
                });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = count - 1;
                textView.setText(Integer.toString(count));
                int n = 50*count;
                String s = n+"  USD";
                totalamount.setText(s);
            }
        });
        checkBox1.setOnClickListener(new View.OnClickListener() {
            String st1 = textone.getText().toString();
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if(!(display2.contains("\n"+ "\n"+st1)) && checked)
                {
                     display2.add("\n"+ "\n"+st1);
                }
                else
                {
                     display2.remove("\n"+ "\n"+st1);
                }
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            String st1 = texttwo.getText().toString();
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if(!(display2.contains("\n"+ "\n"+st1)) && checked)
                {
                    display2.add("\n"+ "\n"+st1);
                }
                else
                {
                    display2.remove("\n"+ "\n"+st1);
                }
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            String st1 = textthree.getText().toString();
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if(!(display2.contains("\n"+ "\n"+st1)) && checked)
                {
                    display2.add("\n"+ "\n"+st1);
                }
                else
                {
                    display2.remove("\n"+ "\n"+st1);
                }
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            String st1 = textfour.getText().toString();
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if(!(display2.contains("\n"+ "\n"+st1)) && checked)
                {
                    display2.add("\n"+ "\n"+st1);
                }
                else
                {
                    display2.remove("\n"+ "\n"+st1);
                }
            }
        });
        checkBox5.setOnClickListener(new View.OnClickListener() {
            String st1 = textfive.getText().toString();
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if(!(display2.contains("\n"+ "\n"+st1)) && checked)
                {
                    display2.add("\n"+ "\n"+st1);
                }
                else
                {
                    display2.remove("\n"+ "\n"+st1);
                }
            }
        });
        checkBox6.setOnClickListener(new View.OnClickListener() {
            String st1 = textsix.getText().toString();
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if(!(display2.contains("\n"+ "\n"+st1)) && checked)
                {
                    display2.add("\n"+ "\n"+st1);
                }
                else
                {
                    display2.remove("\n"+ "\n"+st1);
                }
            }
        });
    }


    public static int setTexts()
    {
        return count;
    }
    public static ArrayList<String> setstring()
    {
        return display2;
    }


    public void openscreen(View v) {
        if (count > 0)
        {

            intent = new Intent(this, ConfirmOrderpage.class);
            intent.putExtra("fruitname", st);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Specify the quantity (It is 0 at present)", Toast.LENGTH_SHORT).show();
        }
    }
}