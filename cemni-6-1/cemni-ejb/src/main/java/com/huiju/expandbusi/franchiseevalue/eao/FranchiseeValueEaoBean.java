package com.huiju.expandbusi.franchiseevalue.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.expandbusi.franchiseevalue.entity.FranchiseeValue;

@Stateless(mappedName = "FranchiseeValueEaoBean")
public class FranchiseeValueEaoBean extends GenericEaoImpl<FranchiseeValue, Long> implements FranchiseeValueEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}