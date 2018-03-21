package oakberg.dk.BallBoiV4.firebase;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import oakberg.dk.BallBoiV4.entity.User;

/**
 * Created by Oakberg on 13/03/2018.
 */

public class FBDatabase
{
    public FBDatabase()
    {
        //Firebase database reference
        com.google.firebase.database.FirebaseDatabase firebaseDatabase = com.google.firebase.database.FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        //Insert
        databaseReference.push().setValue("something");
        databaseReference.child("sometext").setValue("something");

        //Read
        databaseReference.child("sometext").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("sometext", "Text: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        //HashMap Users
        HashMap<String, User> users = new HashMap();
        users.put("Johnny", new User(65, "Johnny Kingston"));
        users.put("Billy", new User(34, "Billy Johnson"));
        users.put("Bobby", new User(22, "Bobby Moore"));
        users.put("Danny", new User(17, "Danny Peterson"));
        users.put("Eric", new User(17, "Eric williams"));
        users.put("26Null", new User(26, null));
        users.put("Null", new User());

        //Insert
        databaseReference.child("users").setValue(users);

        //Read
        databaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Snapshot
                Log.d("users", "" + dataSnapshot);

                HashMap<String, User> hm = new HashMap();
                //Snapshoot loop
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //Snapshot key / value
                    Log.d("users", ds.getKey() + " " + ds.getValue());

                    //Snapshot child getValue
                    String name = ds.child("name").getValue(String.class);
                    int age = ds.child("age").getValue(Integer.class);
                    Log.d("users", "Name: " + name + " Age: " + age);

                    //Snapshot hashmap
                    hm.put(ds.getKey(), ds.getValue(User.class));
                }
                Log.d("users", "" + hm.get("Bobby"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //Read
        databaseReference.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("users", "" + dataSnapshot);
                Log.d("users", dataSnapshot.getKey() + " " + dataSnapshot.getValue());
                String name = dataSnapshot.child("name").getValue(String.class);
                int age = dataSnapshot.child("age").getValue(Integer.class);
                Log.d("users", name + " " + age);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //Query
        //databaseReference.child("users").orderByChild("age").equalTo(17).addValueEventListener(new ValueEventListener() {
        //databaseReference.child("users").orderByKey().equalTo("Billy").addValueEventListener(new ValueEventListener() {
        //databaseReference.orderByValue().equalTo("something").addValueEventListener(new ValueEventListener() {
        databaseReference.child("users").orderByChild("name").startAt("Carl").endAt("Howard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("users", dataSnapshot.toString());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //Snapshot key / value
                    Log.d("users", ds.getKey() + " " + ds.getValue());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //Update
        databaseReference.child("users").child("Danny").setValue(new User(17, "Danny Parker"));

        //Delete
        Query q = databaseReference.child("users").orderByChild("age").equalTo(17);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("users", dataSnapshot.toString());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ds.getRef().removeValue();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}