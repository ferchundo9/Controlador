package com.example.fer.controlador;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothConnector {
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothAdapter btAdapter;
    private BluetoothSocket btSocket;
    private BluetoothDevice device;
    private OutputStream mmOutStream;
    public BluetoothConnector(String MACAddress)throws IOException{
        btAdapter=BluetoothAdapter.getDefaultAdapter();
        device = btAdapter.getRemoteDevice(MACAddress);
        btSocket = createBluetoothSocket(device);
    }
    public void connect()throws IOException{
        btSocket.connect();
        mmOutStream=btSocket.getOutputStream();
    }
    public void disconnect()throws IOException{
        btSocket.close();
    }
    public void write(String message)throws IOException{
        if(!btSocket.isConnected())throw new IOException();
        mmOutStream.write(message.getBytes());
    }
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        //crea una conexion de salida segura para el dispositivo usando el servicio UUID
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }
}
