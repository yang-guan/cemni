package com.huiju.archive.channel.logic;

import javax.ejb.Remote;

import com.huiju.archive.channel.entity.ChannelInfo;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ChannelInfoRemote extends GenericLogic<ChannelInfo, Long> {
}