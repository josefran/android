package com.example.asteroides;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Juego extends Activity {

	private VistaJuego vistaJuego;

	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.juego);

		vistaJuego = (VistaJuego) findViewById(R.id.VistaJuego);
	}

	@Override 
	protected void onPause() {
		super.onPause();
		vistaJuego.getThread().pausar();
		vistaJuego.pausarSensores();

	}

	@Override 
	protected void onResume() {
		super.onResume();
		vistaJuego.getThread().reanudar();
		vistaJuego.reanudarSensores();
	}

	@Override 
	protected void onDestroy() {
		vistaJuego.getThread().detener();
		super.onDestroy();
	}
	
}