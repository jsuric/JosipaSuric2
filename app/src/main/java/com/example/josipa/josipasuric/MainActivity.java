package com.example.josipa.josipasuric;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // u onCreate

        DBAdapter db = new DBAdapter(this);


        //---add a contact---
        db.open();
        long id = db.insertContact("Josipa", "josipa@gmail.com", 5000);
        id = db.insertContact("Marija", "marija@gmail.com", 10000);
        id = db.insertContact("Iva", "Iva@math.com", 8000);
        id = db.insertContact("Petra", "Petra@math.com", 9000);
        id = db.insertContact("Marko", "marko@math.com", 4000);
        db.close();

        db.open();
        id = db.insertRadnoMjesto("rukovodeci", 5, 10000);
        id = db.insertRadnoMjesto("vanjski", 1, 1000);
        id = db.insertRadnoMjesto("sef ureda", 3, 5000);
        id = db.insertRadnoMjesto("sef odjela ", 4, 8000);
        id = db.insertRadnoMjesto("asistent", 2, 3000);
        db.close();

        //--get all contacts---
        db.open();
        Cursor c = db.getAllContacts();
        if (c.moveToFirst())
        {
            do {
                DisplayContact(c);
            } while (c.moveToNext());
        }
        db.close();


        /*
        //--ispisi mjesta tu se ruši
        db.open();
        Cursor d = db.getAllMjesta();
        if (d.moveToFirst())
        {
            do {
                DisplayContact(d);
            } while (d.moveToNext());
        }
        db.close();
        /*


          //---get a contact---
        db.open();
        Cursor cu = db.getContact(2);
        if (cu.moveToFirst())
            DisplayContact(cu);
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
        db.close();


        //--get an address--
        db.open();
        Cursor cua = db.getAddress(2);
        if (cua.moveToFirst())
            DisplayAddress(cua);
        else
            Toast.makeText(this, "No address found", Toast.LENGTH_LONG).show();
        db.close();

        db.open();
        Cursor cua1 = db.getAddress(3);
        if (cua1.moveToFirst())
            DisplayAddress(cua1);
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
        db.close();

*/

    /*
    public void onClickDelete(View view) {
        String id = ((EditText) findViewById(R.id.txtID)).getText().toString();
        //---delete a contact---
        db.open();
        if (db.deleteContact(id))
            Toast.makeText(this, "Delete successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
        db.close();

    }
*/

}

    //funkcija za ispis
    public void DisplayContact(Cursor c) {
        TextView text = (TextView) findViewById(R.id.txt2);
        String ispis="id: " + c.getString(0) + "\n" +
                "Name: " + c.getString(1) + "\n" +
                "Email: " + c.getString(2) + "\n" +
                "Placa:  " + c.getString(3);
        text.setText(ispis);

    }

    public void DisplayAddress(Cursor c) {
        TextView text = (TextView) findViewById(R.id.txt2);
        String ispis="id: " + c.getString(0) + "\n" +
        "MjestoId: " + c.getString(1) + "\n" +
                "Nivo:  " + c.getString(2) + "\n" +
                "Min_placa:  " + c.getString(3);
        text.setText(ispis);



    }


    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }


}
