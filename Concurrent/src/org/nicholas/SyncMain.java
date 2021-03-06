package org.nicholas;

/**
 * 使用一个monitor，效率较低
 * @author Administrator
 *
 */
public class SyncMain {

	static Object lock = new Object();

	static String source;

	public static String read() {

		synchronized (lock) {
			System.out.println("Thread " + getCurrentThreadName()
					+ " start  to read");
			
			if (null == source) {
				System.out.println("Thread " + getCurrentThreadName()
						+ " start  to write");
				source = getCurrentThreadName();
				
			}
		}

		return source;
	}

	private static String getCurrentThreadName() {
		return Thread.currentThread().getName();
	}

	public static void main(String[] args) {
		
		
		Runnable r = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 2; i++)
					System.out.println(getCurrentThreadName()
							+ " read value = " + read());
			}
		};

		Thread t1 = new Thread(r);
		t1.setName("t1");
		Thread t2 = new Thread(r);
		t2.setName("t2");

		t2.start();
		t1.start();
	}
}
