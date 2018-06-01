package com.huiju.common.File.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.common.File.entity.NFileGroup;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "NFileGroupEaoBean")
public class NFileGroupEaoBean extends GenericEaoImpl<NFileGroup, Long> implements NFileGroupEaoLocal {

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
