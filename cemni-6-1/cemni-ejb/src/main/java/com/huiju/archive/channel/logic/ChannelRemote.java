package com.huiju.archive.channel.logic;

import javax.ejb.Remote;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.archive.channel.entity.Channel;

@Remote
public interface ChannelRemote extends GenericLogic<Channel, Long> {
}