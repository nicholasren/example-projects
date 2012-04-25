package org.nicholas;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 可重入的读写锁，会导致变量被写多次
 * @author Administrator
 *
 */

public class Main {

	static ReadWriteLock rwLock = new ReentrantReadWriteLock();

	static Lock rLock = rwLock.readLock();
	static Lock wLock = rwLock.writeLock();

	static String source;

	public static String read() {

		rLock.lock();
		System.out.println("Thread " + getCurrentThreadName()
				+ " start  to read");
		if (null == source) {

			rLock.unlock();
			wLock.lock();
			try {
				System.out.println("Thread " + getCurrentThreadName()
						+ " start  to write");
				source = getCurrentThreadName();
			} finally {
				wLock.unlock();
			}
		} else {
			rLock.unlock();
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
					System.out.println(getCurrentThreadName() + " read value = " + read());
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
