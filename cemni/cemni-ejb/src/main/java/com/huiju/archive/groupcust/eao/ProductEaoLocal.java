package com.huiju.archive.groupcust.eao;

import javax.ejb.Local;

import com.huiju.archive.groupcust.entity.Product;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface ProductEaoLocal extends GenericEao<Product, Long> {
}