package com.huiju.archive.groupcust.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.groupcust.entity.Product;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ProductEaoBean")
public class ProductEaoBean extends GenericEaoImpl<Product, Long> implements ProductEaoLocal {
	
	@Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}