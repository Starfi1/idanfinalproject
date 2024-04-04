package com.example.idansubs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText=findViewById(R.id.edittext_user);


        RecyclerView recyclerView=findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final ArrayList<User> users=new ArrayList<>();

        UserAdapter adapter=new UserAdapter(users);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query myQuery = database.getReference("users").orderByChild("score");
        myQuery.addValueEventListener(new ValueEventListener() {
           @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
              for(DataSnapshot userSnapshot: snapshot.getChildren()){
                  User currentUser=userSnapshot.getValue(User.class);
                    users.add(currentUser);
               }
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })           ;


    }

    public void saveName(View view) {

            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("name",nameEditText.getText().toString());
            startActivity(intent);


    }


}