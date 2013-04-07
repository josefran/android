package com.example.asteroides;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Asteroides extends Activity {

	public static AlmacenPuntuaciones almacen = new AlmacenPuntuacionesArray();
	private Button bAcercaDe;
	private MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bAcercaDe =(Button) findViewById(R.id.button3);
		bAcercaDe.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				lanzarAcercaDe(null);
			}
		});

		mp = MediaPlayer.create(this, R.raw.audio);
		mp.start();
	}

	//	@Override 
	//	protected void onStop() {
	//		super.onStop();
	//		mp.pause();
	//	}

	@Override 
	protected void onPause() {
		super.onPause();
		mp.pause();

	}

	@Override 
	protected void onResume() {
		super.onResume();
		mp.start();
	}

	//	@Override
	//	public boolean onCreateOptionsMenu(Menu menu) {
	//		// Inflate the menu; this adds items to the action bar if it is present.
	//		getMenuInflater().inflate(R.menu.asteroides, menu);
	//		return true;
	//	}

	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu, menu);
		return true; /** true -> el menú ya está visible */
	}

	@Override 
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.acercaDe:
			lanzarAcercaDe(null);
			break;
		case R.id.config:
			lanzarPreferencias(null);
			break;
		}
		return true; /** true -> consumimos el item, no se propaga*/
	}


	public void lanzarAcercaDe(View view){
		Intent i = new Intent(this, AcercaDe.class);
		startActivity(i);
	}

	public void lanzarPreferencias(View view){
		Intent i = new Intent(this, Preferencias.class);
		startActivity(i);
	}

	public void lanzarPuntuaciones(View view) {
		Intent i = new Intent(this, Puntuaciones.class);
		startActivity(i);
	}

	public void lanzarJuego(View view){
		Intent i = new Intent(this, Juego.class);
		startActivity(i);
	}

	public void lanzarExit(View view){
		finish();
	}

	@Override
	protected void onSaveInstanceState(Bundle estadoGuardado){
		super.onSaveInstanceState(estadoGuardado);
		if (mp != null) {
			int pos = mp.getCurrentPosition();
			estadoGuardado.putInt("posicion", pos);
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle estadoGuardado){
		super.onRestoreInstanceState(estadoGuardado);
		if (estadoGuardado != null && mp != null) {
			int pos = estadoGuardado.getInt("posicion");
			mp.seekTo(pos);
		}
	}

}


