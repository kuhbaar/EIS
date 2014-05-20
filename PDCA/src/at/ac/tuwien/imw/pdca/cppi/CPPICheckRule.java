package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.CheckingRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPICheckRule implements CheckingRules {
	private CPPICheckProcess check;
	private CPPIService service;
	public CPPICheckRule(CPPICheckProcess check){
		this.check = check;
	}
	
	@Override
	public void applyCheckingRules() {
		service = CPPIService.getInstance(); //aktuelle Instanz holen
		service.setDeviationValue(check.getCheckResult(check.objectiveSetting, service.getCurrentTSR()).getValue()); //Berechnen Deviation und setzen in CPPIService
	}

}
