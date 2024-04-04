package com.example.idansubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GameActivity extends AppCompatActivity {
    BoardGame bg;
    FrameLayout frame;
    Button showHideBtn;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           name = extras.getString("name");
        }



        frame = findViewById(R.id.frame);
       bg = new BoardGame(this);
      frame.addView(bg);
        showHideBtn=(Button) findViewById(R.id.showHide);
       showHideBtn.setOnClickListener(this::onClick);


    }
    public void retry(View view)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").push();
        User user = new User(name, bg.getTryCounter());
        myRef.setValue(user);

        Intent intent = new Intent( GameActivity.this,MainActivity.class);
        startActivity(intent);
    }



    public void onClick(View v) {
        bg.showHideSubs();
    }
}
