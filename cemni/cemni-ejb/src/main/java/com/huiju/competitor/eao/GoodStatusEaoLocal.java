package com.huiju.competitor.eao;

import javax.ejb.Local;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.competitor.entity.GoodStatus;

@Local
public interface GoodStatusEaoLocal extends GenericEao<GoodStatus, Long> {
}
