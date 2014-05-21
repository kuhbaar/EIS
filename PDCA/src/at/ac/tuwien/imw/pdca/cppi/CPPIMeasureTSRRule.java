package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.CheckingRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIMeasureTSRRule implements CheckingRules {
	private CPPIMeasureTSRProcess check;
	private CPPIService service;
	public CPPIMeasureTSRRule(CPPIMeasureTSRProcess check){
		this.check = check;
	}
	
	@Override
	public void applyCheckingRules() {
		service = CPPIService.getInstance(); //aktuelle Instanz holen
	}

}
