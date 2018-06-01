package com.huiju.salesment.designertarget.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.salesment.designertarget.entity.DesignerDetails;

@Stateless(mappedName = "DesignerDetailsEaoBean")
public class DesignerDetailsEaoBean extends GenericEaoImpl<DesignerDetails, Long> implements DesignerDetailsEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}