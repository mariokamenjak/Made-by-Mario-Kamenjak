package program.window;

import java.io.IOException;

public class TerminalLauncher {
	public static void runlauncher(String args[]) throws IOException
    {
    	String command= "/usr/bin/xterm"; 
    	Runtime rt = Runtime.getRuntime(); 	
    	Process pr = rt.exec(command);
    }
}
