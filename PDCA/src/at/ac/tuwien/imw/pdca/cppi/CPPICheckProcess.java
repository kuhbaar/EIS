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
		this.service=CPPIService.getInstance();
		this.checkingRules = new CPPICheckingRules();
		this.objectiveSetting = new CPPIFloorObjective();
	}
	
	@Override
	public synchronized void run() {
		log.info("CPPICheckProcess started");
		while(true){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			objectiveSetting.setObjectiveSetting(service.getCppiValues().getFloor());
			this.checkingRules.applyCheckingRules();
			service.setDeviation(getCheckResult(objectiveSetting,service.getCurrentTSR()));
		}
	}

	@Override
	public Deviation<BigDecimal> getCheckResult(ObjectiveSetting<BigDecimal> objective,
			MeasuredPerformanceValue<BigDecimal> performanceMeasureValue) {
		return new CPPICushion(service.getCppiValues().getCushion());
	}
}
