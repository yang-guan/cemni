package com.huiju.archive.franchisee.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.archive.franchisee.entity.Franchisee;

@Stateless(mappedName = "FranchiseeEaoBean")
public class FranchiseeEaoBean extends GenericEaoImpl<Franchisee, Long> implements FranchiseeEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}