package com.example.fer.controlador;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;

public class PantallaLed extends AppCompatActivity {
    private AlertDialog.Builder dialog;
    private Handler handler;
    private BluetoothConnector MyBluetooth;
    private AlertDialog alert;
    private String address;
    private ImageButton ledApagado,ledEncendido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);
        ledApagado=findViewById(R.id.buttonLedApagado);
        ledEncendido=findViewById(R.id.buttonLedEncendido);
        this.dialog = new AlertDialog.Builder(this).setTitle("Error de conexión").setMessage("Error en la conexión con el dispositivo");
        dialog.setCancelable(false);
        dialog.setNegativeButton("Regresar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        this.handler  = new Handler();
        alert = dialog.create();
    }
    @Override
    public void onResume() {//Cuando la aplicacion se reanuda ya sea cuando empieza la primera vez
        super.onResume();
        ledApagado.setOnClickListener(clickApagado);
        ledEncendido.setOnClickListener(clickEncendido);
        //Consigue la direccion MAC desde DeviceListActivity via intent
        Intent intent = getIntent();
        //Consigue la direccion MAC desde DeviceListActivity via EXTRA
        address = intent.getStringExtra(MenuPrincipal.EXTRA_DEVICE_ADDRESS);
        //Crea la conexion
        try {
            MyBluetooth = new BluetoothConnector(address);
            MyBluetooth.connect();
        }catch (IOException e) {
            lanzarAlerta();
        }
    }
    @Override
    public void onPause()
    {
        super.onPause();
        try {
            MyBluetooth.disconnect();
        } catch (IOException e2) {}
    }
    private View.OnClickListener clickEncendido = new View.OnClickListener() {
        public void onClick(View v) {
            try{
                MyBluetooth.write("0");
            }catch(IOException e) {
                lanzarAlerta();
            }
            ledEncendido.setVisibility(View.INVISIBLE);
            ledApagado.setVisibility(View.VISIBLE);
        }
    };
    private View.OnClickListener clickApagado = new View.OnClickListener() {
        public void onClick(View v) {
            try{
                MyBluetooth.write("1");
            }catch(IOException e) {
                lanzarAlerta();
            }
            ledEncendido.setVisibility(View.VISIBLE);
            ledApagado.setVisibility(View.INVISIBLE);
        }
    };
    private void lanzarAlerta(){
        alert.show();
        // Hide after some seconds
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    finish();
                }
            }
        };
        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });
        handler.postDelayed(runnable, 10000);
    }
}
