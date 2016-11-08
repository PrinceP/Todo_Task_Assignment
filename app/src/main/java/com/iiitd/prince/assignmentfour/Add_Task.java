package com.iiitd.prince.assignmentfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Task extends AppCompatActivity {

    Button enter;
    EditText tasktext1;
    EditText tasktext2;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__task);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        tasktext1 = (EditText) findViewById(R.id.add_task_box_title);
        tasktext2 = (EditText) findViewById(R.id.add_task_box_detail);

        enter = (Button) findViewById(R.id.add_task_button);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredTaskt = tasktext1.getText().toString();
                String enteredTaskd = tasktext2.getText().toString();

                if(TextUtils.isEmpty(enteredTaskt)){
                    Toast.makeText(Add_Task.this, "You must enter a task first", Toast.LENGTH_LONG).show();
                    return;
                }
                if(enteredTaskt.length() < 6){
                    Toast.makeText(Add_Task.this, "Not a proper title", Toast.LENGTH_LONG).show();
                    return;
                }
                TodoTask taskObject = new TodoTask(enteredTaskt, enteredTaskd);
                databaseReference.push().setValue(taskObject);
                tasktext1.setText("");
                tasktext2.setText("");
                startActivity(new Intent(Add_Task.this, MainActivity.class));
            }
        });

    }
}
