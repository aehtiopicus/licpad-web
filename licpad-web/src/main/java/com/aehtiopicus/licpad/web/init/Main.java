package com.aehtiopicus.licpad.web.init;

import javax.swing.JOptionPane;

public class Main
{
	
	private static  Main main;
	private boolean isRunning = false;
	
	public static void main(String... anArgs) throws Exception
    {
		Main.getInstance().start();
    }
	
	public static Main getInstance(){
		if(main== null){
			main = new Main();
		}
		return main;
	}
	
	private WebServer server;
    
    private Main()
    {
    	 System.setProperty("org.apache.jasper.compiler.disablejsr199", "true");
        server = new WebServer(
        	WebServerConfig.Factory.newDevelopmentConfig("happy", Integer.parseInt(JOptionPane.showInputDialog("aa", 9090)), "localhost"));        
    }
    
    public void start() throws Exception
    {
    	if(!isRunning){
    		server.start();        
        	server.join();
        	isRunning = true;
    	}
    }
    
    public void killServer() {
    	new th(server).start();
    }
    
    class th extends Thread{
    	private WebServer server;
    	
    	public th(WebServer server){
    		this.server = server;	
    	}
    	
    	

		@Override
		public void run() {
			try{
    			Thread.currentThread();
				Thread.sleep(1000);
    			server.stop();
    		}catch(Exception e){
    			e.printStackTrace();
    		}finally{
    			System.exit(0);
    		}
			
		}
    }
}
