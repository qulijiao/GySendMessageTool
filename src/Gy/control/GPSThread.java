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
		System.err.println("����GPSThreadʵ��");
		this.GPSsendFrequency = GPSsendFrequency;
		status= STATUS.idle;
		lasttime = System.currentTimeMillis();
	}
	@Override
	public void run() {
		System.err.println("gps ������");
		while(true){  
			while ( status== STATUS.starting && GPSsendFrequency>0) { 
				if (lasttime+GPSsendFrequency*1000 < System.currentTimeMillis()) {			
					System.err.println("����0200:"+String.valueOf(lasttime));
					lasttime = System.currentTimeMillis();
				}
				Controlor.sleep(500);
			}
			Controlor.sleep(3000); //�����ӕr��ֹcpu̫��
		}
	}

	public static void main(String[] args) { 
		new Thread(new GPSThread(3)).start();
		
	}
}
