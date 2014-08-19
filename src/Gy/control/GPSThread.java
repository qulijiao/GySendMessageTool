package Gy.control;

public class GPSThread implements Runnable{
	int GPSsendFrequency;
	long lasttime ;
	public GPSThread(int GPSsendFrequency){
		this.GPSsendFrequency = GPSsendFrequency;
		lasttime = System.currentTimeMillis();
	}
	@Override
	public void run() {
		while(true){
			if (lasttime+GPSsendFrequency*1000 < System.currentTimeMillis()) {			
				System.err.println("Éú³É0200"+String.valueOf(lasttime));
				lasttime = System.currentTimeMillis();
			}
		}
	}

	public static void main(String[] args) {
		long currentTime = System.currentTimeMillis();
		System.err.println(System.currentTimeMillis());
		Controlor.sleep(1000);
		System.err.println(System.currentTimeMillis());
		new Thread(new GPSThread(3)).start();
		
	}
}
