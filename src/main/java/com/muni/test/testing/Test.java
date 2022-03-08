package com.muni.test.testing;

import java.util.ArrayList;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

public class Test {
	
	Object var1;

	private Test() {
		this.var1 = new Object();
	}
	
	@FunctionalInterface
	interface MyInterface {
		public String interfaceMethod();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		@SuppressWarnings("rawtypes")
		ArrayList<String> myList = new ArrayList<String>();

		myList.add("first value");
		myList.add("second value");
		myList.add("third value");
		myList.add("fourth value");
		myList.add("fifth value");
		myList.add("secondary value");
		myList.add("fifth eighth value");
		myList.add("fifteenth value");
		
		MyInterface obj = () -> {
			return "test obj with functional interface";
		}; 
		
		System.out.println(obj.interfaceMethod());
				
		MyInterface obj1 = () -> {return "print test obj";};
		
		System.out.println(obj1.interfaceMethod());
		
		Thread thrd = new Thread(
			() -> System.out.println("----------thread started-----------")
		);
		
		Thread thrd1 = new Thread( new Runnable() {
			public void run() {
			System.out.println("....another thread....");
		}});
		
		thrd1.run();
		

		thrd.start();
		System.out.println("-----thread priority------"+thrd.getPriority());
		
		// new Test();
		//System.out.println("----print----" + obj.var1);
		// myList.forEach(System.out::println);
		// myList.forEach( x -> System.out.println(x));

		String printlist = myList.stream().filter(x -> x.startsWith("fif"))
				.map(y->y.concat(" ")).reduce("", (a, b) -> a.concat(b));
		//printlist.forEach(System.out::println);
		System.out.println("----print----"+printlist);
		
	}
}
