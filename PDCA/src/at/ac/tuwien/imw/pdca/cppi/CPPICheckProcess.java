package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.CheckProcess;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.ObjectiveSetting;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPICheckProcess extends CheckProcess<BigDecimal> {
	private final static Logger log = LogManager.getLogger(CPPICheckProcess.class);
	private CPPIService service;
	
	public CPPICheckProcess(){
		super();
		service = CPPIService.getInstance();
		this.checkingRules = new CPPICheckingRules();
	}
	
	@Override
	public void run() {
		while(true){
			log.info("CPPICheckProcess started");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("Deviation: ");
		}
		
	}

	@Override
	public Deviation<BigDecimal> getCheckResult(ObjectiveSetting<BigDecimal> objective,
			MeasuredPerformanceValue<BigDecimal> performanceMeasureValue) {
		this.checkingRules.applyCheckingRules();
		return null;
	}

}
