package com.huiju.competitor.logic;

import javax.ejb.Remote;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.competitor.entity.GoodStatus;

@Remote
public interface GoodStatusRemote extends GenericLogic<GoodStatus, Long> {
}