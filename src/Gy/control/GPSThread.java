package Gy.control;

import Gy.control.Global.STATUS;

public class GPSThread implements Runnable{
	int GPSsendFrequency=1;
	public void setGPSsendFrequency(int gPSsendFrequency) {
		GPSsendFrequency = gPSsendFrequency;
	}

	long lasttime ;
	STATUS status = STATUS.idle;
	public GPSThread(int GPSsendFrequency){
		System.err.println("创建gpsthread实例");
		this.GPSsendFrequency = GPSsendFrequency;
		status= STATUS.idle;
		lasttime = System.currentTimeMillis();
	}
	@Override
	public void run() {
		System.err.println("gps 任務開啟");
		while(true){  
			while ( status== STATUS.starting && GPSsendFrequency>0) { 
				if (lasttime+GPSsendFrequency*1000 < System.currentTimeMillis()) {			
					System.err.println("生成0200:"+String.valueOf(lasttime));
					lasttime = System.currentTimeMillis();
				}
				Controlor.sleep(500);
			}
			Controlor.sleep(3000); //加入延時防止cpu太高
		}
	}

	public static void main(String[] args) {
//		long currentTime = System.currentTimeMillis();
//		System.err.println(System.currentTimeMillis());
//		Controlor.sleep(1000);
//		System.err.println(System.currentTimeMillis());
		new Thread(new GPSThread(3)).start();
		
	}
}
