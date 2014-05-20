package at.ac.tuwien.imw.pdca.cppi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.DoProcess;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIStockPriceGenerator;

public class CPPIDoProcess extends DoProcess {

	private final static Logger log = LogManager.getLogger(CPPIDoProcess.class);
	private CPPIStockPriceGenerator gen;
	private CPPIService service;
	private CPPITSR tsr;
	
	public CPPIDoProcess(){
		super();
		gen = new CPPIStockPriceGenerator();
	}
	
	@Override
	public void run() {
		while(true){
			log.info("CPPIDoProcess started");
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
		service = CPPIService.getInstance(); ////aktuelle Instanz holen
		//sachen berechnen
	}

}
