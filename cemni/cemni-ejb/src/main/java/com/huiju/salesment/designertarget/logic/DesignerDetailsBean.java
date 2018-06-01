package com.huiju.salesment.designertarget.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.salesment.designertarget.eao.DesignerDetailsEaoLocal;
import com.huiju.salesment.designertarget.entity.DesignerDetails;

@Stateless(mappedName = "DesignerDetailsBean")
public class DesignerDetailsBean extends
		GenericLogicImpl<DesignerDetails, Long> implements
		DesignerDetailsRemote {
	@EJB(mappedName = "DesignerDetailsEaoBean")
	private DesignerDetailsEaoLocal designerdetailsEao;

	@Override
	protected GenericEao<DesignerDetails, Long> getGenericEao() {
		return designerdetailsEao;
	}
}