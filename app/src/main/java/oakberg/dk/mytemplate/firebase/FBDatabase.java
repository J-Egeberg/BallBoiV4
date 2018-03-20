package oakberg.dk.mytemplate.firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import oakberg.dk.mytemplate.entity.User;

/**
 * Created by Oakberg on 13/03/2018.
 */

public class FBDatabase {

    public FBDatabase()
    {

        //Creating a DB
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();

        //insert "something"
        databaseReference.push().setValue("something");

        //insert via key (her sometext)
        databaseReference.child("sometext").setValue("something");

        //hiv data ud
        databaseReference.child("sometext").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("sometext", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //hiv data ud og vis i console
        databaseReference.child("sometext").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("sometext", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //liste af users tilføjes
        HashMap<String, User> users = new HashMap();
        users.put("George", new User("George Williams", 35));
        users.put("William", new User("William Williams", 55));
        users.put("Billy", new User("Billy Williams", 65));

        databaseReference.child("users").setValue(users);

        User u = new User("Mark", 6);
        databaseReference.child("users").child("Mark").setValue(u);

        databaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("user", dataSnapshot.toString());

                HashMap<String, User> hm = new HashMap();

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Log.d("user", ds.getKey() + " " + ds.getValue());
                    String name = ds.child("name").getValue(String.class);
                    int age = ds.child("age").getValue(int.class);

                    Log.d("users", "User" + name + " " + age);

                    hm.put(ds.getKey(), ds.getValue(User.class));
                }

                Log.d("user", "", hm.get("Billy"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
