package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.*;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIPlanProcess extends PlanProcess<BigDecimal> {

	private final static Logger log = LogManager.getLogger(CPPIPlanProcess.class);
	private CPPIPlanConfiguration conf;
	private CPPIService service;
	private CPPIValues values;
	
	public CPPIPlanProcess(){
		super();
		conf = new CPPIPlanConfiguration(); //die Werte sind bereits hard-coded
		service = CPPIService.getInstance();
		service.init();
		service.setPlanConfiguration(conf); // Config für CPPIService-Instanz
		
		log.info("PlanProcess initiated CPPI Service");
	}
	
	public void run(){
		while(true){
			log.info("CPPIPlanProcess started");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			plan();
		}
	}

	@Override
	public void plan() {
		values = new CPPIValues(conf); //neue Werte berechnen
		service = CPPIService.getInstance(); //aktuelle Instanz holen
		service.setCppiValues(values); //aktuelle Werte setzen
		log.info("CPPIPlanProcess refreshed values");
	}
}
