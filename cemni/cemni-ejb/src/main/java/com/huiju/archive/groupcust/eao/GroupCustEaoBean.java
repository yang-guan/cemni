package com.huiju.archive.groupcust.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.groupcust.entity.GroupCust;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "GroupCustEaoBean")
public class GroupCustEaoBean extends GenericEaoImpl<GroupCust, Long> implements GroupCustEaoLocal {
	
	@Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}