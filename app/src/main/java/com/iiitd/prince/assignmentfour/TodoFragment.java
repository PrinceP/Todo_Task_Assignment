package com.iiitd.prince.assignmentfour;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;


public class TodoFragment extends Fragment {
    private static final String TITLE = "title";
    private static final String DETAIL = "detail";


    private TextView titleview;
    private TextView detailview;
    private String title;
    private String detail;


public static TodoFragment newInstance(String param1, String param2) {
        TodoFragment fragment = new TodoFragment();
        Bundle args = new Bundle();
        args.putSerializable(TITLE, param1);
        args.putSerializable(DETAIL, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
            detail = getArguments().getString(DETAIL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_todo, container, false);



        titleview = (TextView) v.findViewById(R.id.task_box_title);
        detailview = (TextView) v.findViewById(R.id.task_box_detail);

        titleview.setText(title);
        detailview.setText(detail);

        return v;
    }

}
