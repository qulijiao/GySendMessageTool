package test.Thread;

public class TestSend2 implements Runnable {

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
				System.err.println("TestSend2 :"+strtmp); 
				TestSync.strpublic = "TestSend2";
			}
			 
		}
		} 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 

	}

}
