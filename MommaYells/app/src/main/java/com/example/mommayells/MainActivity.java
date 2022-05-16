package com.example.mommayells;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton mImageButton,mLinkAddSchedule;
//    FloatingActionButton mGetInfoFab, mAddScheduleFab;
//    ExtendedFloatingActionButton mAddAction;
//    Boolean isAllFabsVisible;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private boolean isFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";
    private ScheduleViewModel mScheduleViewModel;
//    private ArrayList<Schedule> scheduleArrayList;
    public static final int TEXT_REQUEST = 1;
    private RecyclerView mRecyclerView;
    private ScheduleListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageButton = (AppCompatButton) findViewById(R.id.ButtonWhats);
        mLinkAddSchedule = (AppCompatButton) findViewById(R.id.Addbutton);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });

        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                mImageButton.setText(R.string.close);
            }
        }
        mScheduleViewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ScheduleListAdapter adapter = new ScheduleListAdapter(new ScheduleListAdapter.ScheduleDiff(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mAddScheduleFab = (AppCompatButton) findViewById(R.id.Addbutton);
        mLinkAddSchedule.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddSchedule.class);
            startActivity(intent);
        });
        mScheduleViewModel.getAllSchedule().observe(this, schedules -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(schedules);
        });


        mLinkAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, "Button clicked!");
                Intent intent = new Intent(MainActivity.this, AddSchedule.class);
//                startActivityForResult(intent, TEXT_REQUEST);
                startActivity(intent);
            }
        });
//
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
//
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                mScheduleViewModel.deleteOne(adapter.getScheduleAt(viewHolder.getAdapterPosition()));
//                adapter.notifyDataSetChanged();
//                Toast.makeText(MainActivity.this, "Schedule Dihapus", Toast.LENGTH_SHORT).show();
//
//            }
//        }).attachToRecyclerView(recyclerView);

//        mRecyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                intent.putExtra("nameEvent",schedule.getScheduleName());
//                intent.putExtra("dateEvent",schedule.getScheduleDate());
//                intent.putExtra("detailEvent",schedule.getScheduleDetail());
//                startActivity(intent);
//
//
//            }
//        });

    }

    public void displayFragment(){
        NotifFragment notifFragment = NotifFragment.newInstance();
        // TODO: Get the FragmentManager and start a transaction.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        // TODO: Add the SimpleFragment.
        fragmentTransaction.add(R.id.fragment_container,
                notifFragment).addToBackStack(null).commit();
        mImageButton.setText(R.string.close);
        isFragmentDisplayed = true;

    }

    public void closeFragment(){
        // Get the FragmentManager.
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Check to see if the fragment is already showing.
        NotifFragment notifFragment = (NotifFragment) fragmentManager
                .findFragmentById(R.id.fragment_container);
        if (notifFragment != null) {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(notifFragment).commit();
        }
        // Update the Button text.
        mImageButton.setText(R.string.open);
        // Set boolean flag to indicate fragment is closed.
        isFragmentDisplayed = false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
