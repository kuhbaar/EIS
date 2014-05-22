package at.ac.tuwien.imw.pdca.cppi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.DoProcess;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIStockPriceGenerator;

public class CPPIDoProcess extends DoProcess {

	private final static Logger log = LogManager.getLogger(CPPIDoProcess.class);


	public CPPIDoProcess(){
		super();
		this.doRules = new CPPIDoRules();
	}
	
	@Override
	public synchronized void run() {
		log.info("CPPIDoProcess started");
		while(true){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			operate();
		}
	}

	@Override
	public void operate() {
		doRules.applyDoRules();
	}

}
