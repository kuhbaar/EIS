package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.ActProcess;
import at.ac.tuwien.imw.pdca.CorrectiveActOutput;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIActProcess extends ActProcess {

	private final static Logger log = LogManager.getLogger(CPPIMeasureProcess.class.toString());
	CPPIService service = CPPIService.getInstance();
	CPPIValues val = service.getCppiValues();
	CorrectiveActOutput correctiveRiskyAssets;

	public CPPIActProcess() {
		super();
		this.correctiveActRules = new CPPICorrectiveActRules(this);
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
			
		}
	}

	@Override
	public CorrectiveActOutput act(Deviation deviation) {
		return correctiveRiskyAssets;
	}

	public void setCorrectiveOutput(CPPICorrectiveRiskyAssets correctiveRiskyAssets) {
		this.correctiveRiskyAssets = correctiveRiskyAssets;
		
	}

}
