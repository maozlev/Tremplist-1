package com.myapp.tremplist_update;

import android.util.Log;

public class FireBaseDBActivity extends FirebaseBaseModel {

    public void addUserToDB(User user){
        myRef.child("users").child(user.getFirst_name()).setValue(user);
    }
    public void addRideToDB(Ride ride){
        Log.d("66666666666", "666666666666");

        myRef.child("rides").child(ride.getId()+"").setValue(ride);
    }
}
