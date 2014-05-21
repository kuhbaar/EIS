package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.CorrectiveActOutput;
import at.ac.tuwien.imw.pdca.CorrectiveActRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPICorrectiveActRules implements CorrectiveActRules {
	
	private CPPIActProcess act;
	private CPPIService service;
	private CPPICorrectiveRiskyAssets correctiveRiskyAssets;
	
	private final static Logger log = LogManager.getLogger(CPPIMeasureRules.class.toString());
	
	public CPPICorrectiveActRules(CPPIActProcess act){
		this.act = act;
	}
	
	@Override
	public void applyActRules() {
		service = CPPIService.getInstance(); //aktuelle Instanz holen 
		CPPIValues val = service.getCppiValues();
		CPPIPlanConfiguration conf = service.getPlanConfiguration();
		
		//Berechnen von TSR
		BigDecimal partRiskyAsset = val.getPartRiskyAsset();
		BigDecimal partRisklessAsset= val.getPartRisklessAsset();
		
		BigDecimal newRiskyAssets = conf.getLaverage().multiply(val.getCushion())
				.min(conf.getMaximumRiskyFraction().multiply(val.getPortfolio()));
		val.setPartRiskyAsset(newRiskyAssets);
		BigDecimal newRisklessAssets = val.getPortfolio().subtract(newRiskyAssets);
		val.setPartRisklessAsset(newRisklessAssets);
		correctiveRiskyAssets = new CPPICorrectiveRiskyAssets(newRiskyAssets.subtract(partRiskyAsset));
		act.setCorrectiveOutput(correctiveRiskyAssets);	
			
	}

}