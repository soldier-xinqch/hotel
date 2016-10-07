package org.hotel.controller;

public class Test {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "/sss/eeee";
		String ssss = "";
		String[] temps = url.split("\\/");
		for (String string : temps) {
			ssss +=string;
		}
		System.out.println(ssss);
	}
}
