package com.example.shehani.app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class sensorDetails extends AppCompatActivity {

    BluetoothAdapter myBluetoothAdapterDevice;
    BluetoothDevice myBluetoothDevice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_details);

        myBluetoothAdapterDevice = BluetoothAdapter.getDefaultAdapter();
        if (myBluetoothAdapterDevice == null) { // if device doesn't support bluetooth display error msg
            new AlertDialog.Builder(this)
                    .setTitle("Device is not compatible")
                    .setMessage("Phone doesn't support Bluetooth Connectivity")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    }).show();
        }

        if (!myBluetoothAdapterDevice.isEnabled()) {  // if the adapter is !null the device supports bluethooth
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); // determine if bluetooth is available
            startActivityForResult(enableBtIntent, 1);
        }


        Set<BluetoothDevice> pairedDevicesForBluetooth = myBluetoothAdapterDevice.getBondedDevices();
        if (pairedDevicesForBluetooth.size() > 0) {
            for (BluetoothDevice device : pairedDevicesForBluetooth) { // get the bluetooth module device
                myBluetoothDevice = device;
            }
        }


        Button btn1 = (Button) findViewById(R.id.btnTemp);
        Button btn2 = (Button) findViewById(R.id.btnHumidity);
        Button btn3 = (Button) findViewById(R.id.btngas);
        Button btn4 = (Button) findViewById(R.id.btnco);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(sensorDetails.this, TemperaturePage.class);
                startActivity(int1);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(sensorDetails.this, HumidityPage.class);
                startActivity(int2);
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(sensorDetails.this, GasPage.class);
                startActivity(int3);
            }
        });


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4 = new Intent(sensorDetails.this, CoPage.class);
                startActivity(int4);
            }
        });


        connectThreadToBluetooth c = new connectThreadToBluetooth(myBluetoothDevice);
        c.start();
    }


    private class connectThreadToBluetooth extends Thread {

        private final BluetoothSocket myBluetoothSocket;
        private final BluetoothDevice myBluetoothDevice;
        private final UUID bluetoothUUID = UUID.fromString("00000000-0000-1000-8000-00805F9B34FB"); // the standard base uuid for bluetooth devices

        public connectThreadToBluetooth(BluetoothDevice bluetoothdevice) {
            BluetoothSocket btsocket = null;
            myBluetoothDevice = bluetoothdevice;
            try {
                btsocket = bluetoothdevice.createRfcommSocketToServiceRecord(bluetoothUUID);
            } catch (IOException e) {
            }
            myBluetoothSocket = btsocket;
        }


        public void run() {
            myBluetoothAdapterDevice.cancelDiscovery();
            try {
                myBluetoothSocket.connect();
            } catch (IOException connectException) {
                try {

                    myBluetoothSocket.close();
                } catch (IOException closeException) {
                }
                return;
            }


            ConnectedThreadByBluetoothDevice  v = new ConnectedThreadByBluetoothDevice(myBluetoothSocket);
            v.start();
        }

        public void cancel() {
            try {
                myBluetoothSocket.close();
            } catch (IOException e) {
            }
        }
    }


    private class ConnectedThreadByBluetoothDevice extends Thread {

        private final BluetoothSocket btSocket;
        private final InputStream btInStream;
        private final OutputStream btOutStream;


        public ConnectedThreadByBluetoothDevice(BluetoothSocket socket) {
            btSocket = socket;
            InputStream input = null;
            OutputStream output = null;
            try {
                input = socket.getInputStream();
                output = socket.getOutputStream();
            } catch (IOException e) {
            }
            btInStream = input;
            btOutStream = output;
        }


        public void run() {
            byte[] bufferData = new byte[1024];
            int startPoint = 0;
            int data = 0;
            while (true) {
                try {
                    data += btInStream.read(bufferData, data, bufferData.length - data);
                    for (int i = startPoint; i < data; i++) {
                        if (bufferData[i] == "#".getBytes()[0]) {
                          //  Handler.obtainMessage(1, begin, i, buffer).sendToTarget();
                            startPoint = i + 1;
                            if (i == data - 1) {
                                data = 0;
                                startPoint = 0;
                            }
                        }
                    }
                } catch (IOException e) {
                    break;
                }
            }
        }

        public void write(byte[] bytes) {
            try {
                btOutStream.write(bytes);
            } catch (IOException e) {
            }
        }

        public void cancel() {
            try {
                btSocket.close();
            } catch (IOException e) {
            }
        }

    }




}



