package com.huiju.afterservice.rightmaint.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.afterservice.rightmaint.entity.RightMaint;

@Stateless(mappedName = "RightMaintEaoBean")
public class RightMaintEaoBean extends GenericEaoImpl<RightMaint, Long> implements RightMaintEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}