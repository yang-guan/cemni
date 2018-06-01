package com.huiju.archive.individcust.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.individcust.entity.IndividCustImg;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "IndividCustImgEaoBean")
public class IndividCustImgEaoBean extends GenericEaoImpl<IndividCustImg, Long> implements IndividCustImgEaoLocal {

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

}