package com.khr.justquitit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.material.snackbar.Snackbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.khr.justquitit.adapter.SavingHistoryAdapter;
import com.khr.justquitit.utils.SpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Savings extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ImageButton imgBtn;
    TextView tvTotal;
    int cols, sumCol;
    private RecyclerView recyclerView;
    private SavingHistoryAdapter savingAdapter;
    private ArrayList<SavingHistory> savingArray;
    private static final int VERTICAL_ITEM_SPACE = 28;

    FirebaseAuth fAuth;
    FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);

        // saving Total


        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        ActionBar myActionbar = getSupportActionBar();
        myActionbar.setDisplayHomeAsUpEnabled(true);


        imgBtn = findViewById(R.id.imgBtn_saving);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        /* savingArray = PrefConfig.readListFromPref(this);
        if(savingArray == null){
            savingArray = new ArrayList<>();
        } */

        InitializeCardView();
        //saving total algo
        tvTotal = findViewById(R.id.tv_total);

        totalText();


//       ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
 //       itemTouchHelper.attachToRecyclerView(recyclerView);


    }

    public void totalText(){
        if(savingArray != null) {
            int total = 0;
            for (int i = 0; i < savingArray.size(); i++) {
                SavingHistory savingHistory = savingArray.get(i);
                int temp = Integer.parseInt(savingHistory.getSaving());
                total += temp;

                String secondTemp = String.valueOf(total);
                System.out.println("TotalSave = "+secondTemp);

                tvTotal.setText(secondTemp);
            }
        }else if (savingArray == null){
            tvTotal.setText("0.00");
        }
    }
/*
    SavingHistory deletedSaving = null;
//    List<String> archived = new ArrayList<>();

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {

            final int position = viewHolder.getAdapterPosition();
                deletedSaving = savingArray.get(position);
                savingArray.remove(position);
                SavingHistoryAdapter.notifyItemRemoved(position);
                Snackbar.make(recyclerView, deletedSaving, Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    savingArray.add(position, deletedSaving);
                                    SavingHistoryAdapter.notifyItemInserted(position);
                                }
                            }).show();
            }
        }
    }; */


    private void InitializeCardView() {

        recyclerView = findViewById(R.id.recycleviewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        savingArray = PrefConfig.readListFromPref(this);
        if(savingArray == null){
            savingArray = new ArrayList<>();
        }

//        double sum = 0;
//        for(int i = 0; i < savingArray.size(); i++)
//            sum += savingArray.get(i);
//        return sum;

        //savingArray = new ArrayList<>();

        savingAdapter = new SavingHistoryAdapter(getApplicationContext(), savingArray);
        recyclerView.setAdapter(savingAdapter);

        //add ItemDecoration
        recyclerView.addItemDecoration(new SpacingItemDecoration(VERTICAL_ITEM_SPACE));


        //test 1 2 3
      //  CreateDataForCard();
    }
    //Test data. Nnti tukar kepada Firebase Realtime guna database trigger

    //--------------------------------------------------------------------------------------------------------------------------------------

    //custom pop up dialog ---> official
    private void showDialog() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.layout_custom_dialog);

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_windows);

        ImageView btnClose = dialog.findViewById(R.id.btn_close);
        Button buttonSave = dialog.findViewById(R.id.btn_yes);
        Button buttonCancel = dialog.findViewById(R.id.btn_no);
        EditText savingNotes, savingRM;
        savingNotes = dialog.findViewById(R.id.saving_notes);
        savingRM = dialog.findViewById(R.id.saving_rm);



        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the user input
                String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                String notes = savingNotes.getText().toString();
                String money = savingRM.getText().toString();

                if(notes.isEmpty()){
                    savingNotes.setError("This Field cannot be empty");
                } else if(notes.length() > 40){
                    savingNotes.setError("Short message is the best");
                } else {
                    savingNotes.setError(null);
                    //Save user input inside ArrayList<SavingHistory>
                    SavingHistory saving = new SavingHistory(date, notes, money);
                    savingArray.add(0, saving);
                    //Supposedly make the ArrayList permanentl  inside the user data.
                    PrefConfig.writeListInPref(getApplicationContext(), savingArray);
                    dialog.dismiss();
                }



            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}

    /*public void onBackPressed() {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Are you sure want to exit");
        alertDlg.setCancelable(false);
        alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(a);
            }
        });
        alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertDlg.create().show();
    }*/