package project.oop.engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class NavAct extends AppCompatActivity {
ImageButton offline,online;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        offline= findViewById(R.id.btn_gooffline);
        online= findViewById(R.id.btn_goonline);
        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavAct.this, SearchAct.class));
                finish();
            }
        });
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NavAct.this,"Feature under development. Try again later!", Toast.LENGTH_LONG).show();
            }
        });

    }

}
