package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.*;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIPlanProcess extends PlanProcess<BigDecimal> {
	private boolean running = false;
	private final static Logger log = LogManager.getLogger(CPPIPlanProcess.class);
	private CPPIPlanConfiguration conf;
	private CPPIService service;
	private CPPIValues values;
	private CPPIObjective objective;
	
	public CPPIPlanProcess(){
		super();
		conf = new CPPIPlanConfiguration(); //die Werte sind bereits hard-coded
		service = CPPIService.getInstance();
		service.init();
		service.setPlanConfiguration(conf); // Config für CPPIService-Instanz
		values = new CPPIValues(conf);
		service.setCppiValues(values);
		
		log.info("PlanProcess initiated CPPI Service");
	}
	
	public void run(){
		while(running){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}

	@Override
	public void plan() {
		// TODO Auto-generated method stub
		
	}
}
