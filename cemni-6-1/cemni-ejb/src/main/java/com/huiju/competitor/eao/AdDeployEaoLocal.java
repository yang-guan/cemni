package com.huiju.competitor.eao;

import javax.ejb.Local;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.competitor.entity.AdDeploy;

@Local
public interface AdDeployEaoLocal extends GenericEao<AdDeploy, Long> {
}
