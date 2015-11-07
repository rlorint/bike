package com.example.roxy.antitheft;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Set;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {

            new AlertDialog.Builder(this)
                    .setTitle("Bluetooth warning")
                    .setMessage("Your bluetooth needs to be on in order to proceed. " +
                            "Activate bluetooth?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            mBluetoothAdapter.enable();

                            Intent goToInitialPage = new Intent(getApplicationContext(), InitialPage.class);
                            startActivity(goToInitialPage);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ImageView iv = (ImageView)findViewById(R.id.imageView);
                            iv.setImageResource(R.drawable.sad);

                            TextView tv = (TextView)findViewById(R.id.textView);
                            tv.setText("Sorry! The application cannot run without bluetooth");
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else {
            AcceptBluetoothConnection acceptBluetoothConnection = new AcceptBluetoothConnection(mBluetoothAdapter);
            acceptBluetoothConnection.start();
            Intent goToInitialPage = new Intent(getApplicationContext(), InitialPage.class);
            startActivity(goToInitialPage);
        }

        Button bluetooth = (Button)findViewById(R.id.bluetooth);
        bluetooth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mBluetoothAdapter.enable();
                Intent goToInitialPage = new Intent(getApplicationContext(), InitialPage.class);
                startActivity(goToInitialPage);
            }
        });

        Button buttonDiscover = (Button)findViewById(R.id.buttonDiscover);
        buttonDiscover.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                doDiscovery();
            }

        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void doDiscovery() {
        //if (D) Log.d(TAG, "doDiscovery()");

       /* // Indicate scanning in the title
        setProgressBarIndeterminateVisibility(true);
        setTitle(R.string.scanning);

        // Turn on sub-title for new devices
        findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);

        // If we're already discovering, stop it
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }

        // Request discover from BluetoothAdapter
        mBtAdapter.startDiscovery();*/
    }
}