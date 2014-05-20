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

public class CPPICheckProcess extends CheckProcess<BigDecimal> {
	
	private final static Logger log = LogManager.getLogger(CPPICheckProcess.class.toString());
	private CPPIObjective objective;
	private CPPIService service;
	
	public CPPICheckProcess(){
		super();
	}

	@Override
	public void run() {
		log.info("CPPICheckProcess process started");
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
			service = CPPIService.getInstance(); //aktuelle Instanz holen
			objective = new CPPIObjective(service.getCppiValues()); //objective berechnen
			service.setDeviationValue(this.getCheckResult(objective, service.getCurrentTSR()).getValue()); //Berechnen Deviation und setzen in CPPIService
		}
	}

	@Override
	public Deviation<BigDecimal> getCheckResult(ObjectiveSetting<BigDecimal> objective,
			MeasuredPerformanceValue<BigDecimal> performanceMeasureValue) {
		return new CPPITSRChange(performanceMeasureValue.getValue().subtract(objective.getObjectiveSetting())); //Wt-Ft = Deviation
	}

}
