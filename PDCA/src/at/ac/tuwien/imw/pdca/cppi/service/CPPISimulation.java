package at.ac.tuwien.imw.pdca.cppi.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.cppi.CPPIPlanProcess;

public class CPPISimulation {
	
	private final static Logger log = LogManager.getLogger(CPPISimulation.class);
	
	// TODO Implement me
	// private static CPPIxyProcess xpProcess;
	// ...
	
	// TODO Implement me
	// private static Thread xyProcessThread;
	// ...
	private static CPPIPlanProcess pPro;
	private static Thread pProThread;
	
	public static void main(String[] args) {

		CPPIService.getInstance().init();
		
		//xyProcess = new CPPITSRxy();
		//xyProcessThread = new Thread(xyProcess);
		//xyProcessThread.start();
		
		pPro = new CPPIPlanProcess();
		pProThread = new Thread(pPro);
		pProThread.start();
		
		//...
		
		new Thread(new CPPIStockPriceGenerator()).start();
	}
}
