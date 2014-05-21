package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.CheckProcess;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.ObjectiveSetting;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIStockPriceGenerator;

public class CPPIMeasureProcess extends CheckProcess<BigDecimal> {
	
	private final static Logger log = LogManager.getLogger(CPPIMeasureProcess.class.toString());
	private CPPIService service;
	private CPPIValues val;
	
	public CPPIMeasureProcess(){
		super();
		this.checkingRules = new CPPIMeasureRules(this);
		service = CPPIService.getInstance();
		this.objectiveSetting = new CPPITSRObjective();
		val = service.getCppiValues();
	}

	@Override
	public void run() {
		log.info("CPPIMeasureProcess process started");
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
			objectiveSetting.setObjectiveSetting(service.getCurrentTSR().getValue());
			this.checkingRules.applyCheckingRules(); //bereitet Deviationberechnung vor und berechnet performance
			service.setCurrentTSR(this.performanceValue);
			service.setTSRChange(getCheckResult(this.objectiveSetting, this.performanceValue));
		}
	}

	@Override
	public Deviation<BigDecimal> getCheckResult(ObjectiveSetting<BigDecimal> objective,
			MeasuredPerformanceValue<BigDecimal> performanceMeasureValue) {
		return new CPPITSRChange(performanceMeasureValue.getValue().subtract(objective.getObjectiveSetting())); 
	}

}
