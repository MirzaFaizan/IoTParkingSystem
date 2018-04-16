package com.example.nerdwaretech.iotparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {
    TextView title, availableslots, totalslots;
    private DocumentReference documentReference;
    int totalslotsnumber = 12;
    int availableslotnumber;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // intializaion firebase.
        FirebaseApp.initializeApp( this );
        documentReference = FirebaseFirestore.getInstance().collection( "Parking" ).document( "parkings" );


        // fetching data send from the maps activity.
        bundle = getIntent().getExtras();

        // intializationo of the widgets.
        title = (TextView) findViewById( R.id.title );
        availableslots = (TextView) findViewById( R.id.availableslots );
        totalslots = (TextView) findViewById( R.id.totalslots );

        // used to set the title of the university parking.
        title.setText( bundle.getString( "title" ) );

    }

    @Override
    protected void onStart() {
        super.onStart();

        documentReference.addSnapshotListener( this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {

                    Log.d("Matlab kuch bhi","matlab kuch bhui");
                        availableslotnumber = totalslotsnumber - documentSnapshot.getLong( "Air" ).intValue();
                        availableslots.setText( "Available Slots: " + availableslotnumber );

                        // a condition used to check whether the parking is full or not.
                        if (availableslotnumber == 0) {
                            Toast.makeText( getApplicationContext(), "Parking is full", Toast.LENGTH_LONG ).show();
                        }

                    if (availableslotnumber <0){
                        availableslots.setText( "Parking Full: No slot available" );
                    }




                }
                // when the database is empty.
                else {

                    Toast.makeText( MainActivity.this, "No data exists", Toast.LENGTH_LONG ).show();
                }
            }
        } );
    }
}
