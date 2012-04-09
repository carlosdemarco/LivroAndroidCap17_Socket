package br.livro.android.cap17.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import android.util.Log;

/**
 * Simples Calculadora.
 * 
 * Recebe os números pelo construtor na DataInputStream
 * 
 * e envia a resposta com o método enviar(DataOutputStream)
 * 
 * @author ricardo
 * 
 */
public class Calculadora {
	private static final String CATEGORIA = "livro";
	private int soma;
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;

	public Calculadora(String ip, int porta) throws IOException {
		Log.i(CATEGORIA, "Conectando no socket " + ip + ", porta: " + porta);
		// Abre o socket
		socket = new Socket(ip, porta);
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
		Log.i(CATEGORIA, "Conexão realizada com sucesso.");
	}

	public int somar(int n1, int n2) throws IOException {
		try {
			Log.i(CATEGORIA, "Enviando numeros... " + n1 + " + " + n2);

			// Envia os numeros
			out.writeInt(n1);
			out.writeInt(n2);

			// Envia
			out.flush();

			Log.i(CATEGORIA, "Lendo resposta...");

			// Lê a resposta
			soma = in.readInt();

			Log.i(CATEGORIA, "soma: " + soma);

			return soma;
		} finally {
			// Sempre fechar as streams
			close();
		}
	}

	public void close() throws IOException {
		out.close();
		in.close();
		// Fecha o socket
		socket.close();
	}
}
