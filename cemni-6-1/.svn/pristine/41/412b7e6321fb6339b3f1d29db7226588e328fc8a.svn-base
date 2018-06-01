package com.huiju.archive.channel.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.archive.channel.eao.ChannelEaoLocal;
import com.huiju.archive.channel.entity.Channel;

@Stateless(mappedName = "ChannelBean")
public class ChannelBean extends GenericLogicImpl<Channel, Long> implements ChannelRemote {
    @EJB(mappedName = "ChannelEaoBean")
    private ChannelEaoLocal channelEao;

    @Override
    protected GenericEao<Channel, Long> getGenericEao() {
        return channelEao;
    }
}