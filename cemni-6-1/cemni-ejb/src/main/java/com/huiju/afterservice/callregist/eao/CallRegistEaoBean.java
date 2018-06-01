package com.huiju.afterservice.callregist.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.afterservice.callregist.entity.CallRegist;

@Stateless(mappedName = "CallRegistEaoBean")
public class CallRegistEaoBean extends GenericEaoImpl<CallRegist, Long> implements CallRegistEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}