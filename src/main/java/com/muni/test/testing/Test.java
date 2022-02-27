package com.muni.test.testing;

public class Test {
	
	private String var1;
	
	private Test() {
		this.var1 = "dummy value";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Test obj = new Test();
		
		System.out.println("----print----"+ obj.var1);

	}

}
