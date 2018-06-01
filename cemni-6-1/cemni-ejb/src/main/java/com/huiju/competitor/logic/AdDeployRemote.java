package com.huiju.competitor.logic;

import javax.ejb.Remote;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.competitor.entity.AdDeploy;

@Remote
public interface AdDeployRemote extends GenericLogic<AdDeploy, Long> {
}