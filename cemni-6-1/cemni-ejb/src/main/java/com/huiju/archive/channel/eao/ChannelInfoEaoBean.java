package com.huiju.archive.channel.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.channel.entity.ChannelInfo;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ChannelInfoEaoBean")
public class ChannelInfoEaoBean extends GenericEaoImpl<ChannelInfo, Long> implements ChannelInfoEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}