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
	private CPPIValues val;
	private CorrectiveActOutput<BigDecimal> correctiveRiskyAssets;

	public CPPIActProcess() {
		super();
		correctiveActRules = new CPPICorrectiveActRules(this);
		val = service.getCppiValues();
	}
	
	@Override
	public void run() {
		log.info("CPPIActProcess process started");
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
			act(null);
		}
	}

	@Override
	public CorrectiveActOutput act(Deviation deviation) {
		this.correctiveActRules.applyActRules(); //bereitet Deviationberechnung vor
		BigDecimal correctiveOutput = correctiveRiskyAssets.getValue();
		log.info(correctiveOutput);
		return null;

	}

	public void setCorrectiveOutput(CPPICorrectiveRiskyAssets correctiveRiskyAssets) {
		this.correctiveRiskyAssets = correctiveRiskyAssets;
	}

}
