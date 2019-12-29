package com.edut.test.thread;

public class TestThreadLocalUsed implements Runnable {

	ThreadLocal<String > tl = new ThreadLocal<>() ; 
	int  i = 0 ;
	
	@Override
	public void run() {
		for (; i < 1000 ; i++) {
			String name = Thread.currentThread().getName() ;
			tl.set(name);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!Thread.currentThread().getName().equals(tl.get())) {
				System.out.println(false);
			}
		}
		System.out.println("finish ... "+ Thread.currentThread().getName());
		
	} 
	public static void main(String[] args) {
		TestThreadLocalUsed t = new TestThreadLocalUsed();
		new Thread(t, "AAA").start(); 
		new Thread(t, "BBB").start(); 
	}
	
	
}
