package com.huiju.common.File.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.common.File.entity.NFileInfo;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "NFileInfoEaoBean")
public class NFileInfoEaoBean extends GenericEaoImpl<NFileInfo, Long> implements NFileInfoEaoLocal{

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
