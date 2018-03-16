package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText nameField, BnumberField, PField, addressField, provienceField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        BnumberField = (EditText) findViewById(R.id.Bnumber);
        PField = (EditText) findViewById(R.id.Pbusiness);
        addressField = (EditText) findViewById(R.id.address);
        provienceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            BnumberField.setText(receivedPersonInfo.Bnumber);
            PField.setText(receivedPersonInfo.Pbusiness);
            addressField.setText(receivedPersonInfo.address);
            provienceField.setText(receivedPersonInfo.province);
        }
    }

    public void updateContact(View v){
        //TODO: Update contact funcionality
        String personID = receivedPersonInfo.uid;
        String name = nameField.getText().toString();
        String num = BnumberField.getText().toString();
        String Pbus = PField.getText().toString();
        String add = addressField.getText().toString();
        String pro = provienceField.getText().toString();

        Contact person = new Contact(personID, num, name, Pbus, add, pro);

        appState.firebaseReference.child(personID).setValue(person);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
