package com.khr.justquitit;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOfeedback {
    private DatabaseReference databaseReference;

    public DAOfeedback(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(FeedbackData.class.getSimpleName());
    }
    public Task<Void> add(FeedbackData feed){
        return databaseReference.push().setValue(feed);
    }
}
