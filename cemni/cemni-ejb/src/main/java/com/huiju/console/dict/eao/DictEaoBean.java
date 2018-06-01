package com.huiju.console.dict.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.console.dict.entity.Dict;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "DictEaoBean")
public class DictEaoBean extends GenericEaoImpl<Dict, Long> implements DictEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}