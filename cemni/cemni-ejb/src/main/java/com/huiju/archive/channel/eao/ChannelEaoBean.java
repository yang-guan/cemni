package com.huiju.archive.channel.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.archive.channel.entity.Channel;

@Stateless(mappedName = "ChannelEaoBean")
public class ChannelEaoBean extends GenericEaoImpl<Channel, Long> implements ChannelEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}