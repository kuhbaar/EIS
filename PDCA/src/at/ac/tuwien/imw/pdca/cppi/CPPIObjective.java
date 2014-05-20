package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import at.ac.tuwien.imw.pdca.ObjectiveSetting;

public class CPPIObjective extends ObjectiveSetting<BigDecimal> {
	public CPPIObjective(CPPIValues val){
		this.setObjectiveSetting(val.getFloor());
	}
}
