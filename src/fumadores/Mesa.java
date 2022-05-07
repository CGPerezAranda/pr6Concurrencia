package fumadores;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Mesa {
    private int ingrediente;
    private Semaphore puedePoner;
    private Semaphore[] puedeFumar;

    public Mesa(){
        ingrediente =-1; //mesa vacía
        puedePoner = new Semaphore(1, true);
        puedeFumar = new Semaphore[3];
        for(int i = 0; i<3; i++){
            puedeFumar[i] = new Semaphore(0,true);
        }
    }

    public void quiereFumar(int id) throws InterruptedException {
        puedeFumar[id].acquire();
        ingrediente = -1;
        System.out.println("Fumador "+ id + " empieza a fumar");
    }

    public void terminaFumar(int id){
        System.out.println("El fumador "+id+" termina de fumar.");
        puedePoner.release();
    }

    public void poneIngrediente(int ing) throws InterruptedException {
        puedePoner.acquire();
        ingrediente = ing;
        System.out.println("\t\tEl agente pone " + ing);
        puedeFumar[ing].release();

    }
    public static void main (String[] args){
        Mesa m = new Mesa ();
        Fumador f0 = new Fumador(0,m);
        Fumador f1 = new Fumador(1,m);
        Fumador f2 = new Fumador(2,m);
        Agente ag = new Agente(m);
        ag.start();
        f0.start();f1.start();f2.start();
    }

}
