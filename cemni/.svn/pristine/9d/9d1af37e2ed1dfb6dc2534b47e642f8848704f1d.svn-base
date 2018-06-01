package com.huiju.competitor.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.competitor.entity.AdDeploy;

@Stateless(mappedName = "AdDeployEaoBean")
public class AdDeployEaoBean extends GenericEaoImpl<AdDeploy, Long> implements AdDeployEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}