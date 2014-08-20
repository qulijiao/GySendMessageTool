package test.Thread;

public class TestSendThread implements Runnable {

	@Override
	public void run() {  
		while (true) { 
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
			String strtmp="" ;
			if (!TestSync.queue.isEmpty()) {
				strtmp = (String) TestSync.queue.remove();
				System.err.println("TestSendThread :"+strtmp); 
				TestSync.strpublic = "TestSendThread";
			}
			 
		}
		} 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 

	}

}
