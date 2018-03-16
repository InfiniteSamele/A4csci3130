package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, BnumberField, PField, addressField, provienceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.create);
        nameField = (EditText) findViewById(R.id.name);
        BnumberField = (EditText) findViewById(R.id.Bnumber);
        PField = (EditText) findViewById(R.id.Pbusiness);
        addressField = (EditText) findViewById(R.id.address);
        provienceField = (EditText) findViewById(R.id.province);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String num = BnumberField.getText().toString();
        String Pbus = PField.getText().toString();
        String add = addressField.getText().toString();
        String pro = provienceField.getText().toString();

        Contact person = new Contact(personID, name, num, Pbus, add, pro);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
