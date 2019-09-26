package project.oop.engine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NavAct extends AppCompatActivity {
ImageButton offline,online;
RadioGroup rg;
RadioButton rb1,rb2;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        rg = findViewById(R.id.rg1);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        offline= findViewById(R.id.btn_gooffline);
        online= findViewById(R.id.btn_goonline);
        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permission();
            }
        });
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavAct.this, olsearch.class));
                finish();
                //Toast.makeText(NavAct.this,"Feature under development. Try again later!", Toast.LENGTH_LONG).show();
            }
        });

    }
public void permission(){
    if (ContextCompat.checkSelfPermission(NavAct.this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
        // Permission is not granted
        ActivityCompat.requestPermissions(NavAct.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);

    }else{
        int selected = rg.getCheckedRadioButtonId(),stat = 0;
        if(selected==-1){
            Toast.makeText(NavAct.this,"Select a mode!", Toast.LENGTH_LONG).show();
        }else{
            if(rb1.isChecked()){
                stat = 1;
            }else if(rb2.isChecked()){
                stat = 2;
            }
            Intent myIntent = new Intent(NavAct.this, SearchAct.class);
            myIntent.putExtra("iv", stat);
            startActivity(myIntent);
            finish();
        }
    }
}
}
