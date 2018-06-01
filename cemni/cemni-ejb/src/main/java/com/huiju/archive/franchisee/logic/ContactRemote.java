package com.huiju.archive.franchisee.logic;

import javax.ejb.Remote;

import com.huiju.archive.franchisee.entity.Contact;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ContactRemote extends GenericLogic<Contact, Long> {

}
