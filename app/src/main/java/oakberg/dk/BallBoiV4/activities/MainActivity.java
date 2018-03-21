package oakberg.dk.BallBoiV4.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import oakberg.dk.BallBoiV4.firebase.FBDatabase;
import oakberg.dk.BallBoiV4.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FBDatabase();
    }
}
