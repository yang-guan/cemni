package com.huiju.archive.supplier.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.archive.supplier.eao.SupplierEaoLocal;
import com.huiju.archive.supplier.entity.Supplier;

@Stateless(mappedName = "SupplierBean")
public class SupplierBean extends GenericLogicImpl<Supplier, Long> implements SupplierRemote {
    @EJB(mappedName = "SupplierEaoBean")
    private SupplierEaoLocal supplierEao;

    @Override
    protected GenericEao<Supplier, Long> getGenericEao() {
        return supplierEao;
    }
}