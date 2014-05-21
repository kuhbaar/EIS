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
	
	public CPPICheckProcess(){
		super();
		this.checkingRules = new CPPICheckingRules();
	}
	
	@Override
	public void run() {
		log.info("CPPICheckProcess started");
		while(true){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.objectiveSetting = new CPPIFloorObjective();
			objectiveSetting.setObjectiveSetting(CPPIService.getInstance().getCppiValues().getFloor());
			getCheckResult(objectiveSetting,CPPIService.getInstance().getCurrentTSR());
		}
		
	}

	@Override
	public Deviation<BigDecimal> getCheckResult(ObjectiveSetting<BigDecimal> objective,
			MeasuredPerformanceValue<BigDecimal> performanceMeasureValue) {
		this.checkingRules.applyCheckingRules();
		return new CPPICushion(CPPIService.getInstance().getCppiValues().getCushion());
	}

}
