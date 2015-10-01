package com.aehtiopicus.licpad.core.utils;

import java.util.ArrayList;
import java.util.List;

public class PortScanner {

	private static final Integer[] DEFAUL_PORTS = new Integer[] { 7070, 8080, 8181,
			8282, 8383, 8484, 8585, 9090 };

	public static Integer[] scannPossiblePorts() {
		List<Integer> availablePorts = new ArrayList<>();

		for (int port : DEFAUL_PORTS) {
			if(isPortAvailable(port)){
				availablePorts.add(port);
			}			
		}
		Integer portArray [] = new Integer []{};
		return availablePorts.toArray(portArray);
	}
	
	public static boolean isPortAvailable(int port){
		try {
			java.net.ServerSocket server = new java.net.ServerSocket(port);
			server.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}
