package test.Thread;

public class TestAddThread implements Runnable {
static int i=10;
	@Override
	public void run() { 
		while (true) { 		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		i++;
		TestSync.queue.add(String.valueOf(i));
		System.err.println("TestAddThread:"+ i);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
