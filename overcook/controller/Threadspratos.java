package controller;

import java.util.concurrent.Semaphore;

public class Threadspratos extends Thread {
	private int id;
	private Semaphore semaforo;
	private static int iniciou;
	private static int terminou;
	private static int entregue;
	private String prato;
	
	public Threadspratos(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	@Override
	public void run() {
		if( id % 2 != 0) {
			prato = "Sopa de Cebola";
		} else {
			prato = "Lasanha a Bolonhesa";
		}
		iniciou++;
		System.out.println("Thread [#" + id + "] - O prato " + prato + " foi o " + iniciou + "o. a iniciar o preparo.");
		fazendo();		 
		terminou++;
		System.out.println("Thread [#"+ id + "] - O prato " + prato + " esta em 100% e foi o " + terminou + "o. a terminar o preparo.");
		try {
			semaforo.acquire();
			entregar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			sair();
		}
		
		super.run();
	}
	
	private void sair() {
		entregue++;
		System.out.println("Thread [#"+ id + "] - O prato " + prato + " foi o " + entregue + "o. a ser entregue.");
		
	}
	private void fazendo() {
	int tempototal;
		if(id%2!=0) {
			tempototal = (int)((Math.random()*301)+500);
		}	else {
			tempototal= (int)((Math.random()*601)+600);
		} double porcentagem = (tempototal/ 100);
		while (porcentagem < 100) {		
			System.out.println("Thread [#"+ id + "] - O prato " + prato + " esta em " + (porcentagem) + " %.");
			porcentagem += (tempototal/100);
			try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		}	
	}

	private void entregar() {
		System.out.println("Thread [#"+ id + "] - O prato " + prato + " saiu para a entrega.");
		int tempo = 500;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
