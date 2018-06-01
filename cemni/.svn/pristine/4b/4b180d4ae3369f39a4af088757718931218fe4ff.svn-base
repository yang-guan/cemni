package com.huiju.archive.groupcust.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.archive.groupcust.eao.ProductEaoLocal;
import com.huiju.archive.groupcust.entity.Product;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ProductBean")
public class ProductBean extends GenericLogicImpl<Product, Long> implements ProductRemote {
    @EJB(mappedName = "ProductEaoBean")
    private ProductEaoLocal productEao;

    @Override
    protected GenericEao<Product, Long> getGenericEao() {
        return productEao;
    }

}