package com.huiju.archive.franchisee.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.franchisee.entity.Contact;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ContactEaoBean")
public class ContactEaoBean extends GenericEaoImpl<Contact, Long> implements ContactEaoLocal {
	@Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
