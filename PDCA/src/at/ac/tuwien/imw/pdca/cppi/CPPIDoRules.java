package at.ac.tuwien.imw.pdca.cppi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.DoRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIDoRules implements DoRules {
	private final static Logger log = LogManager.getLogger(CPPIDoRules.class);
	@Override
	public void applyDoRules() {
		log.info("Neue Portfoliozusammensetzung: " + CPPIService.getInstance().getCppiValues().getPartRiskyAsset().toPlainString() + " risikoreich veranlagt, " + CPPIService.getInstance().getCppiValues().getPartRisklessAsset() + " risikolos veranlagt.");

	}

}
