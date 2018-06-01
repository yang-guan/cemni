package com.huiju.actment.activity.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.actment.activity.eao.ActGiveEaoLocal;
import com.huiju.actment.activity.entity.ActGive;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ActGiveBean")
public class ActGiveBean extends GenericLogicImpl<ActGive, Long> implements ActGiveRemote {
	@EJB(mappedName = "ActGiveEaoBean")
	private ActGiveEaoLocal ActGiveEao;

	@Override
	protected GenericEao<ActGive, Long> getGenericEao() {
		return ActGiveEao;
	}
}
