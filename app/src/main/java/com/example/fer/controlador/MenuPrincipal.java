package com.example.fer.controlador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;


public class MenuPrincipal extends AppCompatActivity {
    private Button led,wheelControl,robotControl;
    private String address = null;
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        led = findViewById(R.id.ledControl);
        wheelControl = findViewById(R.id.wheelControl);
        robotControl = findViewById(R.id.robotControl);

        Intent intent = getIntent();
        //Consigue la direccion MAC desde DeviceListActivity via EXTRA
        address = intent.getStringExtra(DispositivosBT.EXTRA_DEVICE_ADDRESS);
    }
    public void onResume() {//Cuando la aplicacion se reanuda ya sea cuando empieza la primera vez
        super.onResume();
        led.setOnClickListener(clickLed);
        wheelControl.setOnClickListener(clickControl);
        robotControl.setOnClickListener(clickRobot);

    }
    private View.OnClickListener clickControl = new View.OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(MenuPrincipal.this, PantallaPrincipal.class);
            i.putExtra(EXTRA_DEVICE_ADDRESS, address);
            startActivity(i);
        }
    };
    private View.OnClickListener clickLed = new View.OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(MenuPrincipal.this, PantallaLed.class);
            i.putExtra(EXTRA_DEVICE_ADDRESS, address);
            startActivity(i);
        }
    };
    private View.OnClickListener clickRobot = new View.OnClickListener() {
        public void onClick(View v) {
            // A completar
        }
    };


}
