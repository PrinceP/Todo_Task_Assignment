package com.iiitd.prince.assignmentfour;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.iiitd.prince.assignmentfour.R.id.toolbar;

public class TodoViewer extends FragmentActivity {

    private ViewPager mViewPager;
    private DatabaseReference databaseReference;
    private String title;
    private List<TodoTask> allTask;


    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_viewer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);

        allTask = new ArrayList<TodoTask>();

        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");
        Log.d("d",title);

        getFeedItemlist();

    }

    private void getFeedItemlist() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    ArrayList<String> t = new ArrayList<String>();
                    ArrayList<String> d = new ArrayList<String>();

                    for(DataSnapshot doubleSnapshot : singleSnapshot.getChildren()) {
                        if(doubleSnapshot.getKey().equals("title"))
                        {
                            t.add ((String) doubleSnapshot.getValue());
                        }
                        if(doubleSnapshot.getKey().equals("detail"))
                        {
                            d.add((String) doubleSnapshot.getValue());

                        }
                    }
                for(int i =0;i<t.size();i++){

                    allTask.add(new TodoTask(t.get(i),d.get(i)) );

                }


                }


                mViewPager  = (ViewPager) findViewById(R.id.vpPager);
                FragmentManager fragmentManager = getSupportFragmentManager();

                mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
                    @Override
                    public Fragment getItem(int position) {
                        TodoTask t = allTask.get(position);
                        return TodoFragment.newInstance(t.getTitle(),t.getDetail());
                    }

                    @Override
                    public int getCount() {
                        return allTask.size();
                    }

                    @Override
                    public void notifyDataSetChanged() {
                        super.notifyDataSetChanged();
                    }
                });


                for (int i = 0; i < allTask.size(); i++) {
                    if (allTask.get(i).getTitle().equals(title)) {
                        mViewPager.setCurrentItem(i);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add) {
            Intent intent = new Intent(this, Add_Task.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
