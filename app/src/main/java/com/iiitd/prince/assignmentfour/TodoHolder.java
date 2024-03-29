package com.iiitd.prince.assignmentfour;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by Prince on 02-11-2016.
 */

public class TodoHolder extends RecyclerView.ViewHolder {

    private static final String TAG = TodoHolder.class.getSimpleName();
    public ImageView markIcon;
    public TextView categoryTitle;
    public ImageView deleteIcon;
    private List<TodoTask> taskObject;
    public TodoHolder(final View itemView, final List<TodoTask> taskObject) {
        super(itemView);
        this.taskObject = taskObject;
        categoryTitle = (TextView)itemView.findViewById(R.id.task_title);
        markIcon = (ImageView)itemView.findViewById(R.id.task_icon);
        markIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskTitle = taskObject.get(getAdapterPosition()).getTitle();
                Context context = view.getContext();
                Log.d(TAG, "Task Title " + taskTitle);
                Intent i = new Intent(context,TodoViewer.class);
                i.putExtra("TITLE",taskTitle);
                context.startActivity(i);

            }
        });


        deleteIcon = (ImageView)itemView.findViewById(R.id.task_delete);
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Delete icon has been clicked", Toast.LENGTH_LONG).show();
                String taskTitle = taskObject.get(getAdapterPosition()).getTitle();
                Log.d(TAG, "Task Title " + taskTitle);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.orderByChild("title").equalTo(taskTitle);
                Log.d(TAG, "++ " + applesQuery);
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });
            }
        });
    }
}
