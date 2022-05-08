package canibales;

import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.print.attribute.PrintServiceAttribute;

public class Fiesta {
    private Caldero caldero;
    private Semaphore quiereComer;
    private Semaphore cocinando;
    private Semaphore esperaCocinero;

    public Fiesta(Caldero caldero){
    	this.caldero = caldero;
        cocinando = new Semaphore(0, true);
        quiereComer = new Semaphore(1,true);
        esperaCocinero = new Semaphore(0,true);

    }
    public void comer (int id) throws InterruptedException{
    	quiereComer.acquire();
    	if(caldero.getRaciones() == 0) {
    		System.out.println("Canibal "+ id + " esperando a que el cocinero llene el caldero.");
    		cocinando.release();
    		esperaCocinero.acquire();    		
    	}
    	System.out.println("Canibal " + id + " está comiendo una racion");
    	Thread.sleep(2000 + new Random().nextInt(id+1));
    	caldero.tomarRacion();
    	System.out.println("El caldero tiene "+ caldero.getRaciones() + " raciones");
    	quiereComer.release();
    }
    public void cocinar () throws InterruptedException{
    	cocinando.acquire();
    	System.out.println("Cocinando...");
    	Thread.sleep(1000 + new Random().nextInt(3000));
    	caldero.llenarCaldero();
    	System.out.println("caldero lleno con "+caldero.getMaxRaciones()+" raciones");
    	esperaCocinero.release();    	
    }
    
    public static void main (String[] args) {
    	Caldero caldero = new Caldero(10);
    	Fiesta fiesta = new Fiesta(caldero);
    	Cocinero cocinero = new Cocinero(fiesta);
    	Canibal[] canibales = new Canibal[5];
    	for (int i=0; i< 5 ; i++) {
    		canibales[i] = new Canibal(i, fiesta);
    	}
    	cocinero.start();
    	for (int i =0; i<5; i++) {
    		canibales[i].start();
    	}
		/*
		 * for (int i =0; i<5; i++) { try { canibales[i].join();
		 * }catch(InterruptedException e) { e.printStackTrace(); } }
		
    	try {
    		cocinero.join();
    	}catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    	 */
    	
    }
}
