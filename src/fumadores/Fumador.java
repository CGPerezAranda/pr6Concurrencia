package fumadores;

public class Fumador extends Thread {
    private int id;
    private Mesa mesa;

    public Fumador (int id, Mesa mesa){
        this.id = id;
        this.mesa = mesa;
    }
    public void run(){
        try{
            while(true){
                mesa.quiereFumar(id);
                Thread.sleep(2000); //Se fuma el cigarro
                mesa.terminaFumar(id);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}


