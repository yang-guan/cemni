package com.huiju.actment.activity.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.actment.activity.entity.ActGive;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ActGiveEaoBean")
public class ActGiveEaoBean extends GenericEaoImpl<ActGive, Long> implements ActGiveEaoLocal {
	@Override
	@PersistenceContext(unitName = "showcase")
	public void setEntityManager(EntityManager em) {
		super.setEntityManager(em);
	}
}
