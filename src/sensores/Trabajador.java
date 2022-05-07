package sensores;

public class Trabajador extends Thread{
    private Sistema s;
    public Trabajador (Sistema s){
        this.s = s;
    }
    public void run(){
        try{
            while(true){
                s.procesarMedidas();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
