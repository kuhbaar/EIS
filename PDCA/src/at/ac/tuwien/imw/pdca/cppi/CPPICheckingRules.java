package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.CheckingRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPICheckingRules implements CheckingRules {
	private CPPIService service;
	private CPPIValues val;
	private final static Logger log = LogManager.getLogger(CPPICheckingRules.class.toString());

	public CPPICheckingRules() {
		super();
	}

	public void applyCheckingRules() {
		//aktuelle Instanzen holen
		service = CPPIService.getInstance();
		val = service.getCppiValues();
		
		//Rechnen und setzen des aktuellen Floors F_t
		BigDecimal abgelaufeneZeit = new BigDecimal((service.getCurrentPeriod()-val.getConfPeriod()));
		abgelaufeneZeit = abgelaufeneZeit.divide(new BigDecimal(val.getConf().getRisklessAssetLastDays()),4,RoundingMode.HALF_UP).setScale(4);
		BigDecimal fristigkeit = BigDecimal.ONE.subtract(abgelaufeneZeit);
		BigDecimal floor = new BigDecimal(Math.pow(val.getConf().getFloor().divide((BigDecimal.ONE.add(val.getConf().getRisklessAssetInterest())),4,RoundingMode.HALF_UP).doubleValue(),fristigkeit.doubleValue())).setScale(4, RoundingMode.HALF_UP);
		val.setFloor(floor);
		
		//Rechnen und setzen der Cushion C_t
		BigDecimal cushion = val.getPortfolio().subtract(floor).max(BigDecimal.ZERO);
		val.setCushion(cushion);
		
		log.info("Folgende CheckingRules wurden angewandt: Floor = " + floor.toPlainString() + " , Cushion = " + cushion.toPlainString());
	}

}
