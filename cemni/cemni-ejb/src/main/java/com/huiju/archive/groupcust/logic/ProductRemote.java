package com.huiju.archive.groupcust.logic;

import javax.ejb.Remote;

import com.huiju.archive.groupcust.entity.Product;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ProductRemote extends GenericLogic<Product, Long> {
}