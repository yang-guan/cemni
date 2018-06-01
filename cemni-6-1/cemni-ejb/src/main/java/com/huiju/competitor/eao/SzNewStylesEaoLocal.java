package com.huiju.competitor.eao;

import javax.ejb.Local;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.competitor.entity.SzNewStyles;

@Local
public interface SzNewStylesEaoLocal extends GenericEao<SzNewStyles, Long> {
}
