package com.huiju.console.orgext.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.console.orgext.entity.OrgExt;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "OrgExtEaoBean")
public class OrgExtEaoBean extends GenericEaoImpl<OrgExt, Long> implements OrgExtEaoLocal {

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

}