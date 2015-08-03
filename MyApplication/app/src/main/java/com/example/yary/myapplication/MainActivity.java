/**
 * Created by YARY on 01/08/2015.
 */

package com.example.yary.myapplication;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText txtUser,txtPass;
    TextView loginUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineElements();
        doLogin();
    }

    public void defineElements(){
        txtUser = (EditText)findViewById(R.id.txtUser);
        txtPass = (EditText)findViewById(R.id.txtPassword);
        loginUp = (TextView)findViewById(R.id.labDone);
    }

    public void doLogin(){
        final Database connection = new Database(this, "testApp", null, 1);
        SQLiteDatabase db = connection.getWritableDatabase();

        if (connection.insertUser()){
            Toast.makeText(getApplicationContext(), "inserting: user:admin, pass:admin ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "error inserting default user", Toast.LENGTH_SHORT).show();
        }

        loginUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (connection.read(txtUser.getText().toString(), txtPass.getText().toString())){
                    lanzarActivity(v, txtUser.getText().toString(), txtPass.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "There is not user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void lanzarActivity(View v, String texto, String pass) {
        Intent i = new Intent(this, Dashboard.class);
            i.putExtra("user", texto);
            i.putExtra("pass", pass);
        startActivity(i);
    }

}
