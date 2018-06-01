package com.huiju.common.area.logic;

import javax.ejb.Remote;

import com.huiju.common.area.entity.Area;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface AreaRemote extends GenericLogic<Area, Long> {
}