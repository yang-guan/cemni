package com.huiju.archive.supplier.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.archive.supplier.entity.Supplier;

@Stateless(mappedName = "SupplierEaoBean")
public class SupplierEaoBean extends GenericEaoImpl<Supplier, Long> implements SupplierEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}