package com.huiju.inter.activity.logic;

import javax.ejb.Remote;

import com.huiju.inter.activity.entity.ActivityAudit;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ActivityAuditRemote extends GenericLogic<ActivityAudit, Long> {

}