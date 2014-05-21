package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.CheckingRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIMeasureRules implements CheckingRules {
	private CPPIMeasureProcess measure;
	private CPPIService service;
	private CPPIPortfolio portfolio;
	private CPPITSR tsr;
	
	private final static Logger log = LogManager.getLogger(CPPIMeasureRules.class.toString());
	
	public CPPIMeasureRules(CPPIMeasureProcess measure){
		this.measure = measure;
	}
	
	@Override
	public void applyCheckingRules() {
		service = CPPIService.getInstance(); //aktuelle Instanz holen 
		CPPIValues val = service.getCppiValues();
		CPPIPlanConfiguration conf = service.getPlanConfiguration();
		
		//Berechnen von TSR
		BigDecimal previousStockPrice = service.getPreviousStockPrice();
		BigDecimal currentStockPrice = service.getCurrentStockPrice();
		
		BigDecimal newTSR = currentStockPrice.divide(previousStockPrice, 6, BigDecimal.ROUND_UP).subtract(BigDecimal.ONE);
		tsr = new CPPITSR(newTSR);
		measure.setPerformanceValue(tsr); //setzen der neue performanceValue
		
		//Berechnen von Wealth
		BigDecimal riskless = val.getPartRisklessAsset().multiply(BigDecimal.ONE.add(conf.getRisklessAssetInterest())
				.pow(service.getCurrentPeriod()/conf.getRisklessAssetLastDays()));
		BigDecimal riskful = val.getPartRiskyAsset().multiply(BigDecimal.ONE.add(tsr.getValue()));
		portfolio = new CPPIPortfolio(riskful.add(riskless));
		log.info("new portfolio: " +portfolio.getValue().setScale(4, RoundingMode.HALF_UP));
		service.setPortfolio(this.portfolio); //setzen Wealth		
		
	}

}
