package com.huiju.expandbusi.franchiseeprofit.costsupport.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.franchiseeprofit.costsupport.entity.CostSupport;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "CostSupportEaoBean")
public class CostSupportEaoBean extends GenericEaoImpl<CostSupport, Long> implements CostSupportEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}