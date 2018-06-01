package com.huiju.archive.franchisee.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.archive.franchisee.eao.ContactEaoLocal;
import com.huiju.archive.franchisee.entity.Contact;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ContactBean")
public class ContactBean extends GenericLogicImpl<Contact, Long> implements ContactRemote {
    @EJB
    private ContactEaoLocal ContactEao;

    @Override
    protected GenericEao<Contact, Long> getGenericEao() {
        return ContactEao;
    }
}