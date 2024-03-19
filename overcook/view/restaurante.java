package view;

import java.util.concurrent.Semaphore;

import controller.Threadspratos;

public class restaurante {

	public static void main(String[] args) {

		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		for(int id = 0; id < 5; id++) {
			Thread pratos = new Threadspratos(id, semaforo);
			pratos.start();
		}
	}

}
