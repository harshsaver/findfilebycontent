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
import android.widget.Toast;

public class NavAct extends AppCompatActivity {
ImageButton offline,online;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

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
                Toast.makeText(NavAct.this,"Feature under development. Try again later!", Toast.LENGTH_LONG).show();
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
        startActivity(new Intent(NavAct.this, SearchAct.class));
        finish();
    }
}
}
