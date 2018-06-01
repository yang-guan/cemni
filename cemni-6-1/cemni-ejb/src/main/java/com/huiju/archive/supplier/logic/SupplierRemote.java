package com.huiju.archive.supplier.logic;

import javax.ejb.Remote;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.archive.supplier.entity.Supplier;

@Remote
public interface SupplierRemote extends GenericLogic<Supplier, Long> {
}