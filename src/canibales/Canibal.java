package canibales;

public class Canibal extends Thread{
	private Fiesta fiesta;
	private int id;
	
	public Canibal (int id, Fiesta fiesta) {
		this.id = id;
		this.fiesta = fiesta;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				fiesta.comer(id);
			
			}
			catch (InterruptedException e) {
			e.printStackTrace();
			}
		}
	}
}
