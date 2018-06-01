package com.huiju.competitor.logic;

import javax.ejb.Remote;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.competitor.entity.SzNewStyles;

@Remote
public interface SzNewStylesRemote extends GenericLogic<SzNewStyles, Long> {
}