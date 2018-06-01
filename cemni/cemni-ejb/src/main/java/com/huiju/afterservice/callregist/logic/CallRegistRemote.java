package com.huiju.afterservice.callregist.logic;

import javax.ejb.Remote;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.afterservice.callregist.entity.CallRegist;

@Remote
public interface CallRegistRemote extends GenericLogic<CallRegist, Long> {
}