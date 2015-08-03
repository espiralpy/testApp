package com.example.yary.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class Dashboard extends FragmentActivity {
    TextView loginUp;
    String datas;
    Button camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        defineElements();
        printUser();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        StartFragment runFragment = new StartFragment();

        transaction.add(R.id.fragment_holder, runFragment);
        transaction.commit();

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load camera intent
                Intent openCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                File save = new File(Environment.getExternalStorageDirectory(), "testAppPicture");
                save.mkdirs();

                //Save photo in device.
                File image = new File(save, "photo.jpg");
                Uri uri = Uri.fromFile(image);
                openCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(openCamera, 1);
            }
        });
    }

    public void defineElements(){
        loginUp = (TextView)findViewById(R.id.txtName);
        camera = (Button)findViewById(R.id.btnFrag02);
    }

    public void printUser(){
        //recollet datas from intent
        datas = getIntent().getStringExtra("user");
        loginUp.setText(datas);
    }

    public void selectFragment(View view){

        Fragment newFragment;

        if (view == findViewById(R.id.btnStart)){
            newFragment = new StartFragment();
        } else if (view == findViewById(R.id.btnFrag01)){
            newFragment = new Fragment01();
        }  else {
            newFragment = new StartFragment();
        }

        FragmentTransaction transactionMove = getSupportFragmentManager().beginTransaction();
        transactionMove.replace(R.id.fragment_holder, newFragment);
        transactionMove.addToBackStack(null);
        transactionMove.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Clean "intent" datas.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
