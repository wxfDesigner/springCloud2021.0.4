package com.springCloud.k8s.gateway.app;

import java.io.File;

/**
 * Unit test for simple App.
 */
public class ApplicationTest{
	
	public static void main(String[] args) {
		System.out.println("lb://springCloud-k8s-provider-app".substring(5));
		System.out.println(File.pathSeparator);
	}
	

}
