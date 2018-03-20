package oakberg.dk.mytemplate.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import oakberg.dk.mytemplate.firebase.FBDatabase;
import oakberg.dk.mytemplate.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FBDatabase();
    }
}
