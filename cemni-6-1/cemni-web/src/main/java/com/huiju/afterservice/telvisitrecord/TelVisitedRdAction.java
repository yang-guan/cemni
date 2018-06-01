package com.huiju.afterservice.telvisitrecord;

import com.huiju.afterservice.telvisitrecord.entity.TelVisitRecord;
import com.huiju.module.web.action.BaseAction;

public class TelVisitedRdAction extends BaseAction<TelVisitRecord, Long> {
    private static final long serialVersionUID = 1L;

    public String init() throws Exception {
        jsPath.add("/js/console/store/Q.store.chooseStoreWin.js");
        jsPath.add("/js/archive/individcust/Q.archive.chooseIndiWin.js");
        jsPath.add("/js/afterservice/telvisitrecord/Q.afterservice.telvisitedrd.js");

        String[] authorities = { "D_TELVISITEDRDE_LIST", "D_TELVISITEDRDE_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

}