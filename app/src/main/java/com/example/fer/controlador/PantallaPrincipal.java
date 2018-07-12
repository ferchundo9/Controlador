package com.example.fer.controlador;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.UUID;

public class PantallaPrincipal extends AppCompatActivity {
    private AlertDialog.Builder dialog;
    private BluetoothConnector MyBluetooth;
    private AlertDialog alert;
    private Handler handler;
    // Identificador unico de servicio - SPP UUID
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // String para la direccion MAC
    private String address = null;
    //-------------------------------------------
    //@SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interfaz);
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
    public void delante (View view){
        ImageButton boton=null;
        ImageButton boton2=null;
        ImageButton boton3=null;
        switch(view.getId()){
            case R.id.imageButton1:
                boton=findViewById( R.id.imageButton9);
                boton2=findViewById( R.id.imageButton2);
                boton3=findViewById( R.id.imageButton10);
                view.setVisibility(View.INVISIBLE);
                boton.setVisibility(View.VISIBLE);
                boton2.setVisibility(View.VISIBLE);
                boton3.setVisibility(View.INVISIBLE);
                break;
            case R.id.imageButton3:
                boton=findViewById( R.id.imageButton11);
                boton2=findViewById( R.id.imageButton4);
                boton3=findViewById( R.id.imageButton12);
                view.setVisibility(View.INVISIBLE);
                boton.setVisibility(View.VISIBLE);
                boton2.setVisibility(View.VISIBLE);
                boton3.setVisibility(View.INVISIBLE);
                break;
            case R.id.imageButton5:
                boton=findViewById( R.id.imageButton13);
                boton2=findViewById( R.id.imageButton6);
                boton3=findViewById( R.id.imageButton14);
                view.setVisibility(View.INVISIBLE);
                boton.setVisibility(View.VISIBLE);
                boton2.setVisibility(View.VISIBLE);
                boton3.setVisibility(View.INVISIBLE);
                break;
            case R.id.imageButton7:
                boton=findViewById( R.id.imageButton15);
                boton2=findViewById( R.id.imageButton8);
                boton3=findViewById( R.id.imageButton16);
                view.setVisibility(View.INVISIBLE);
                boton.setVisibility(View.VISIBLE);
                boton2.setVisibility(View.VISIBLE);
                boton3.setVisibility(View.INVISIBLE);
                break;
        }
    }
    public void parar (View view){
        ImageButton boton=null;
        view.setVisibility(View.INVISIBLE);
        switch(view.getId()){
            case R.id.imageButton9:
                boton=findViewById( R.id.imageButton1);
                boton.setVisibility(View.VISIBLE);
                break;
            case R.id.imageButton10:
                boton=findViewById( R.id.imageButton2);
                boton.setVisibility(View.VISIBLE);
                break;
            case R.id.imageButton11:
                boton=findViewById( R.id.imageButton3);
                boton.setVisibility(View.VISIBLE);
                break;
            case R.id.imageButton12:
                boton=findViewById( R.id.imageButton4);
                boton.setVisibility(View.VISIBLE);
                break;
            case R.id.imageButton13:
                boton=findViewById( R.id.imageButton5);
                boton.setVisibility(View.VISIBLE);
                break;
            case R.id.imageButton14:
                boton=findViewById( R.id.imageButton6);
                boton.setVisibility(View.VISIBLE);
                break;
            case R.id.imageButton15:
                boton=findViewById( R.id.imageButton7);
                boton.setVisibility(View.VISIBLE);
                break;
            case R.id.imageButton16:
                boton=findViewById( R.id.imageButton8);
                boton.setVisibility(View.VISIBLE);
                break;
        }
    }
    public void atras(View view){
        ImageButton boton=null;
        ImageButton boton2=null;
        ImageButton boton3=null;
        switch(view.getId()){
            case R.id.imageButton2:
                boton=findViewById( R.id.imageButton10);
                boton2=findViewById( R.id.imageButton1);
                boton3=findViewById( R.id.imageButton9);
                view.setVisibility(View.INVISIBLE);
                boton.setVisibility(View.VISIBLE);
                boton2.setVisibility(View.VISIBLE);
                boton3.setVisibility(View.INVISIBLE);
                break;
            case R.id.imageButton4:
                boton=findViewById( R.id.imageButton12);
                boton2=findViewById( R.id.imageButton3);
                boton3=findViewById( R.id.imageButton11);
                view.setVisibility(View.INVISIBLE);
                boton.setVisibility(View.VISIBLE);
                boton2.setVisibility(View.VISIBLE);
                boton3.setVisibility(View.INVISIBLE);
                break;
            case R.id.imageButton6:
                boton=findViewById( R.id.imageButton14);
                boton2=findViewById( R.id.imageButton5);
                boton3=findViewById( R.id.imageButton13);
                view.setVisibility(View.INVISIBLE);
                boton.setVisibility(View.VISIBLE);
                boton2.setVisibility(View.VISIBLE);
                boton3.setVisibility(View.INVISIBLE);
                break;
            case R.id.imageButton8:
                boton=findViewById( R.id.imageButton16);
                boton2=findViewById( R.id.imageButton7);
                boton3=findViewById( R.id.imageButton15);
                view.setVisibility(View.INVISIBLE);
                boton.setVisibility(View.VISIBLE);
                boton2.setVisibility(View.VISIBLE);
                boton3.setVisibility(View.INVISIBLE);
                break;
        }
    }
    @Override
    public void onResume() {//Cuando la aplicacion se reanuda ya sea cuando empieza la primera vez
        super.onResume();
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
