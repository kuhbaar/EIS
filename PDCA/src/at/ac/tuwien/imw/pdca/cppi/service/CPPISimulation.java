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
		try {
		pPro = new CPPIPlanProcess();
		pProThread = new Thread(pPro);
		pProThread.start();
		
		Thread.sleep(1000);
		
		mPro = new CPPIMeasureProcess();
		mProThread = new Thread(mPro);
		mProThread.start();
		Thread.sleep(1000);
		cPro = new CPPICheckProcess();
		cProThread = new Thread(cPro);
		cProThread.start();
		Thread.sleep(1000);
		aPro = new CPPIActProcess();
		aProThread = new Thread(aPro);
		aProThread.start();
		Thread.sleep(1000);
		dPro = new CPPIDoProcess();
		dProThread = new Thread(dPro);
		dProThread.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new CPPIStockPriceGenerator()).start();
	}
}
