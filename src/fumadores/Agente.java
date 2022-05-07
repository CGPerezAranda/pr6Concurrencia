package fumadores;

import java.util.Random;

public class Agente extends Thread {
    private Mesa mesa;
    Random r;
    public Agente (Mesa m){
        this.mesa = m;
        r = new Random();
    }
    public void run(){
        try{
            while(true){
                mesa.poneIngrediente(r.nextInt(3));
                //Thread.sleep(50)
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
