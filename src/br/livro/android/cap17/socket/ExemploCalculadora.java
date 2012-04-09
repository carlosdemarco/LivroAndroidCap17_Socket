package br.livro.android.cap17.socket;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Calculadora em Android, mas faz a soma chamando um Socket que estão no
 * servidor
 * 
 * @author ricardo
 * 
 */
public class ExemploCalculadora extends Activity implements OnClickListener {
	private static final String CATEGORIA = "livro";
	private static final String IP = "192.168.20.201";
	private static final int PORTA = 7777;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		setContentView(R.layout.soma);

		Button b = (Button) findViewById(R.id.btSomar);
		b.setOnClickListener(this);
	}

	public void onClick(View view) {
		EditText textn1 = (EditText) findViewById(R.id.n1);
		EditText textn2 = (EditText) findViewById(R.id.n2);
		TextView textSoma = (TextView) findViewById(R.id.soma);

		int n1 = Integer.parseInt(textn1.getText().toString());
		int n2 = Integer.parseInt(textn2.getText().toString());

		try {
			Log.i(CATEGORIA,"Socket conectando no endereço: " + IP);
			Calculadora calculadora = new Calculadora(IP, PORTA);

			int soma = calculadora.somar(n1, n2);

			String textoSoma = "SOMA: " + soma;

			textSoma.setText(textoSoma);

			Log.i(CATEGORIA, String.valueOf(textoSoma));

			textSoma.setVisibility(View.VISIBLE);
		} catch (IOException e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
	}
}