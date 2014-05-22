package at.ac.tuwien.imw.pdca.cppi.service;

import org.apache.log4j.BasicConfigurator;

import at.ac.tuwien.imw.pdca.cppi.*;

public class CPPISimulation {
	
	private static CPPIPlanProcess pPro;
	private static Thread pProThread;
	private static CPPIMeasureProcess mPro;
	private static Thread mProThread;
	private static CPPIActProcess aPro;
	private static Thread aProThread;
	private static CPPIDoProcess dPro;
	private static Thread dProThread;
	private static CPPICheckProcess cPro;
	private static Thread cProThread;
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		CPPIService.getInstance().init();
		
		pPro = new CPPIPlanProcess();
		pProThread = new Thread(pPro);
		pProThread.start();
		
		mPro = new CPPIMeasureProcess();
		mProThread = new Thread(mPro);
		mProThread.start();
		cPro = new CPPICheckProcess();
		cProThread = new Thread(cPro);
		cProThread.start();
		aPro = new CPPIActProcess();
		aProThread = new Thread(aPro);
		aProThread.start();
		dPro = new CPPIDoProcess();
		dProThread = new Thread(dPro);
		dProThread.start();
		
		new Thread(new CPPIStockPriceGenerator()).start();
	}
}
