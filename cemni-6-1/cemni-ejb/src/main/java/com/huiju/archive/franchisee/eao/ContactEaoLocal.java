package com.huiju.archive.franchisee.eao;

import javax.ejb.Local;

import com.huiju.archive.franchisee.entity.Contact;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface ContactEaoLocal extends GenericEao<Contact, Long>{

}
