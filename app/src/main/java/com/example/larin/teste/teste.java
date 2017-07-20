package com.example.larin.teste;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.FragmentManager;
import java.net.InetAddress;
import java.net.Socket;
import android.os.AsyncTask;
import java.io.IOException;
import java.net.UnknownHostException;

public class teste extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ProgressDialog progressDialog;
    private MessageHandler messageHandler;
    private static final int SERVERPORT = 8888;
    private static final String SERVER_IP = "192.168.0.5";

    //Set up a string array to inflate the listview
   // String[] names = {"sentado", "deitado", "em pe"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teste, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_home){
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new ThirdFragment())
                    .commit();
        }
        else if (id == R.id.nav_camera) {
            // Handle the camera action
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new FirstFragment())
                    .commit();

            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Calibrating");
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(true);
           messageHandler = new MessageHandler();
        } else if (id == R.id.nav_gallery) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new SecondFragment())
                    .commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startCounterButton1(View v) {
        progressDialog.show();
        Thread thread = new Thread(new Timer());
        thread.start();
        new MyClientTask().execute();
    }

    public void startCounterButton2(View v) {
        progressDialog.show();
        Thread thread = new Thread(new Timer());
        thread.start();
        new MyClientTask().execute();
    }

    public void startCounterButton3(View v) {
        progressDialog.show();
        Thread thread = new Thread(new Timer());
        thread.start();
        new MyClientTask().execute();
    }

    public void startCounterButton4(View v) {
        progressDialog.show();
        Thread thread = new Thread(new Timer());
        thread.start();
        new MyClientTask().execute();
    }

    public class Timer implements Runnable {

        @Override

        public void run() {
            for (int i = 15; i >= 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }

                Bundle bundle = new Bundle();
                bundle.putInt("current count", i);
                Message message = new Message();
                message.setData(bundle);
                messageHandler.sendMessage(message);
            }
            progressDialog.dismiss();
        }
    }

    private class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message message) {

            int currentCount = message.getData().getInt("current count");
            progressDialog.setMessage("Please wait in..." + currentCount);
        }
    }

    public class MyClientTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                Socket socket = new Socket(serverAddr,SERVERPORT);
                socket.close();

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
         }

    }

}


