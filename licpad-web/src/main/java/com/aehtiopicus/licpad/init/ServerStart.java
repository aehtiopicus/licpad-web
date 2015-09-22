package com.aehtiopicus.licpad.init;



public class ServerStart {

	public static void main(String [] args) throws Exception
    {
        new ServerStart().start();
    }
	
	private WebServer server;
    
    public ServerStart()
    {
        server = new WebServer(WebServerConfig.Factory.newDevelopmentConfig("happy", 8000, "localhost"));        
    }
    
    public void start() throws Exception
    {
        server.start();        
        server.join();
    } 
}
