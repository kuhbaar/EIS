package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.ActProcess;
import at.ac.tuwien.imw.pdca.CorrectiveActOutput;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIActProcess extends ActProcess<BigDecimal, BigDecimal> {

	private final static Logger log = LogManager.getLogger(CPPIActProcess.class.toString());
	private CPPIService service = CPPIService.getInstance();
	private CorrectiveActOutput<BigDecimal> correctiveRiskyAssets;

	public CPPIActProcess() {
		super();
		correctiveActRules = new CPPICorrectiveActRules(this);
	}
	
	@Override
	public synchronized void run() {
		log.info("CPPIActProcess process started");
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
			service.setCorrectiveOutput(act(service.getDeviation()));
		}
	}

	@Override
	public CorrectiveActOutput<BigDecimal> act(Deviation<BigDecimal> deviation) {
		this.correctiveActRules.applyActRules(); //bereitet Deviationberechnung vor
		BigDecimal correctiveOutput = correctiveRiskyAssets.getValue();
		if(!(correctiveOutput.equals(null))) {
			if (correctiveOutput.compareTo(BigDecimal.ZERO) > 0) {
				log.info("Need to buy " + correctiveOutput +" more risky assets");
				return correctiveRiskyAssets;
			}
			else if (correctiveOutput.compareTo(BigDecimal.ZERO) < 0) {
				log.info("Need to sell " + correctiveOutput.multiply(new BigDecimal(-1)) + "risky assets");
				return correctiveRiskyAssets;
			}
		}
		log.info("no change neccessary");
		return correctiveRiskyAssets;

	}

	public void setCorrectiveOutput(CPPICorrectiveRiskyAssets correctiveRiskyAssets) {
		this.correctiveRiskyAssets = correctiveRiskyAssets;
	}

}
