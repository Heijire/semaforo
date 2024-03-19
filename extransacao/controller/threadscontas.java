package controller;
import java.util.concurrent.Semaphore;
public class threadscontas extends Thread {
	private int id;
	private Semaphore semaforo;
	public threadscontas(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	@Override
	public void run() {
		int tempoCal, tempoBd;
			if(id % 3 == 1) {		
				for(int i=0;i<2;i++) {
					tempoCal = (int)((Math.random()*801)+200);
					tempoBd = 1000;
					calculo(tempoCal);
					try {
						semaforo.acquire();
						BD(tempoBd);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						semaforo.release();
					}	
				}
				System.err.println("A Thread[#"+ id + "] - terminou.");
			} else if(id % 3 == 2) {	
				for(int i=0;i<4;i++) {
					tempoCal = (int)((Math.random()*1001)+500);
					tempoBd = 1500;
					calculo(tempoCal);
					try {
						semaforo.acquire();
						BD(tempoBd);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						semaforo.release();
					}
				}System.err.println("A Thread[#"+ id + "] - terminou");
			} else {
				for(int i=0;i<3;i++) {
					tempoCal = (int)((Math.random()*1001)+1000);
					tempoBd = 1500;
					calculo(tempoCal);
					try {
						semaforo.acquire();
						BD(tempoBd);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						semaforo.release();
					}
				}
				System.err.println("A Thread[#"+ id + "] - terminou.");
			}
			super.run();
		}
	private void calculo(int tempo) {
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A Thread[#"+ id + "] - esta calculando.");
	}

	private void BD(int tempoBd) {
		try {
			sleep(tempoBd);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A Thread[#"+ id + "] - esta fazendo transação.");
	}
}
	
