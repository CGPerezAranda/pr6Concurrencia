
package sensores;

import java.util.concurrent.Semaphore;

public class Sistema {
    private int[] medidas;
    private int nmedidas;
    private Semaphore mutex, puedeProcesar;
    private Semaphore[] puedeMedir;

    public Sistema(){
        nmedidas = 0; //sin medidas
        puedeMedir = new Semaphore[3];
        mutex = new Semaphore(1,true);
        medidas = new int[3];
        for (int i = 0; i < 3;i++){
            puedeMedir[i] = new Semaphore(1,true);
        }
        puedeProcesar = new Semaphore(0,true);
    }
    public void ponerMedida(int id, int dato) throws InterruptedException {

        puedeMedir[id].acquire();
        mutex.acquire();
        nmedidas++;
        System.out.println("Sensor "+ id +" toma medida "+dato);
        medidas[id] = dato;
        if(nmedidas == 3){
            puedeProcesar.release();
        }
        mutex.release();

    }

    public void procesarMedidas() throws InterruptedException {
        puedeProcesar.acquire();
        mutex.acquire();//no es necesario
        System.out.println("Medidas: "+medidas[0]+","+medidas[1]+","+medidas[2]);
        nmedidas = 0;
        for (Semaphore s : puedeMedir) {
            s.release();
        }
        mutex.release();//no es necesario

    }

    public static void main (String[] args){
        Sistema s = new Sistema();
        Sensor[] sensores = new Sensor[3];
        for (int i =0; i<3; i++){
            sensores[i] = new Sensor(i,s);
        }
        Trabajador t = new Trabajador(s);
        t.start();
        for (int i =0; i<3; i++){
            sensores[i].start();
        }
    }
}
