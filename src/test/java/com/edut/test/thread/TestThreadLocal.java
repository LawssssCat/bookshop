package com.edut.test.thread;

public class TestThreadLocal implements Runnable {

	String name ; 
	int  i = 0 ;
	
	@Override
	public void run() {
		for (; i < 100 ; i++) {
			name = Thread.currentThread().getName() ;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!Thread.currentThread().getName().equals(name)) {
				System.out.println(false);
			}
		}
		System.out.println("finish ... "+ Thread.currentThread().getName());
		
	} 
	public static void main(String[] args) {
		TestThreadLocal t = new TestThreadLocal();
		new Thread(t, "AAA").start(); 
		new Thread(t, "BBB").start(); 
	}
	
	
}
