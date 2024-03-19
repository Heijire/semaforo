package view;
import java.util.concurrent.Semaphore;
import controller.threadscontas;
public class principal {
	public static void main(String[] args) {
		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		for(int id = 0; id < 21; id++) {
			Thread thread = new threadscontas(id, semaforo);
			thread.start();
		}	
	}
}
