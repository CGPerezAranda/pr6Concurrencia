package canibales;

public class Cocinero extends Thread{
	private Fiesta fiesta;
	
	public Cocinero (Fiesta fiesta) {
		this.fiesta = fiesta;
	}
	
	public void run () {
		while(true) {
			try {
				fiesta.cocinar();
			}
			catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			}
		}
	}
}
