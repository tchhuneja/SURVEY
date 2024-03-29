package com.example.wamp.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.PatternMatcher;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.wamp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Credentials extends AppCompatActivity {


    Button nextButton;
    EditText emailEdit,nameEdit,staff_idEdit,deptEdit;
    String email,name,staff_id,department;
    Intent intent;

    RelativeLayout rellay1, rellay2;

    boolean allFieldsFilled;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);//For Animation

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);

        rellay1 =  findViewById(R.id.rellay1);
        rellay2 =  findViewById(R.id.rellay2);//For Animation

        handler.postDelayed(runnable, 2000);

        nextButton=findViewById(R.id.nextButton);

        staff_idEdit=findViewById(R.id.firstEdit);
        deptEdit=findViewById(R.id.secondEdit);
        nameEdit=findViewById(R.id.thirdEdit);
        emailEdit=findViewById(R.id.fourthEdit);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailEdit.getText().toString();
                name = nameEdit.getText().toString();
                staff_id = staff_idEdit.getText().toString();
                department = deptEdit.getText().toString();

                allFieldsFilled = true;

                if ((email.length() == 0) || !(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                    allFieldsFilled = false;
                    emailEdit.setError("Invalid E-mail");
                }

                if (name.length() == 0) {
                    allFieldsFilled = false;
                    nameEdit.setError("This is a required Field");
                }

                if (staff_id.length() == 0) {
                    allFieldsFilled = false;
                    staff_idEdit.setError("This is a required Field");
                }
                if (department.length() == 0) {
                    allFieldsFilled = false;
                    deptEdit.setError("This is a required Field");
                }

                if (allFieldsFilled) {

                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("department", department);
                    intent.putExtra("email", email);
                    intent.putExtra("name", name);
                    intent.putExtra("staff_id", staff_id);

                    startActivity(intent);
                    finish();

                    overridePendingTransition(R.anim.goup, R.anim.godown);
                } else
                    Toast.makeText(Credentials.this, "Some Fields are Missing", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
