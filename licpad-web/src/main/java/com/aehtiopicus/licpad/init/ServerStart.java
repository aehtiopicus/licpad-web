package com.aehtiopicus.licpad.init;

import com.aehtiopicus.licpad.config.WebServer;

public class ServerStart {

	public static void main(String [] args) throws Exception
    {
        new ServerStart().start();
    }
	
	private WebServer server;
    
    public ServerStart()
    {
        server = new WebServer(8080);        
    }
    
    public void start() throws Exception
    {
        server.start();        
        server.join();
    } 
}
