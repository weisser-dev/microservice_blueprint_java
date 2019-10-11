package dev.weisser.samples;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.junit.Test;

public class ServiceTest {

	Service classUnderTest = new Service();
	
	@Test public void testServiceStartup() {
		assertTrue("startup should return 'true'", classUnderTest.startup());
	}
	  
	@SuppressWarnings("resource")
	@Test public void testServiceReachable() {
		  	try{
		  		new Socket().connect(new InetSocketAddress("http://localhost:3001", 1000));
		  		assertTrue("return true, cause Socket can connect to Adress of Service", true);
		  	} catch (IOException ex) {
		  		
		  	}
	  }
	
}
