package sensores;

import java.util.Random;

public class Sensor extends Thread{
    private Sistema sistema;
    private int id;
    private Random r;

    public Sensor (int id, Sistema s){
        sistema = s;
        this.id = id;
        r=new Random();
    }
    public void run(){
        try{
            while(true){
                sistema.ponerMedida(id, r.nextInt(100));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
