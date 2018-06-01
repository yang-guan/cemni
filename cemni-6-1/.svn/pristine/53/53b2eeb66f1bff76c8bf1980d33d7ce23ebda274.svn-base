package com.huiju.archive.channel.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.archive.channel.eao.ChannelInfoEaoLocal;
import com.huiju.archive.channel.entity.ChannelInfo;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ChannelInfoBean")
public class ChannelInfoBean extends GenericLogicImpl<ChannelInfo, Long> implements ChannelInfoRemote {
    @EJB(mappedName = "ChannelInfoEaoBean")
    private ChannelInfoEaoLocal channelInfoEao;

    @Override
    protected GenericEao<ChannelInfo, Long> getGenericEao() {
        return channelInfoEao;
    }
}