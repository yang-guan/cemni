package com.huiju.archive.channel.eao;

import javax.ejb.Local;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.archive.channel.entity.Channel;

@Local
public interface ChannelEaoLocal extends GenericEao<Channel, Long> {
}