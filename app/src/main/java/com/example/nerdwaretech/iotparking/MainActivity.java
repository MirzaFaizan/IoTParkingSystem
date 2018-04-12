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
    TextView title,availableslots,totalslots;
    private DocumentReference documentReference;
    int totalslotsnumber = 12 ;
    int availableslotnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // intializaion firebase.
        FirebaseApp.initializeApp( this );
        documentReference = FirebaseFirestore.getInstance().collection( "Parking" ).document( "parkings" );


        // fetching data send from the maps activity.
        Bundle bundle = getIntent().getExtras();

        // intializationo of the widgets.
        title = (TextView) findViewById( R.id.title );
        availableslots = (TextView) findViewById( R.id.availableslots );
        totalslots = (TextView) findViewById( R.id.totalslots );

        title.setText( bundle.getString( "title" ) );

    }

    @Override
    protected void onStart() {
        super.onStart();

        documentReference.addSnapshotListener( this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {

                    availableslotnumber = totalslotsnumber - documentSnapshot.getLong( "Air" ).intValue();

                    availableslots.setText( "Available Slots: "+ availableslotnumber );

                  } else {
                    Log.d( "inElseIf", "nerdware" );

                    Toast.makeText( MainActivity.this, "Not Exists", Toast.LENGTH_LONG ).show();
                }
            }
        } );
    }
}
