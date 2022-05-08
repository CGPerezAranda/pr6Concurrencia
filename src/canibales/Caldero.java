package canibales;

import java.util.concurrent.Semaphore;

public class Caldero {
    private int raciones;
    private Semaphore puedeComer;
    private Semaphore puedeCocinar;
    private Semaphore mutex;

    public Caldero(int raciones){
        this.raciones = raciones;
        puedeCocinar = new Semaphore(1, true);
        puedeComer = new Semaphore(0,true);

    }
}
