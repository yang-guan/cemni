package com.huiju.archive.groupcust.logic;

import java.io.File;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.archive.groupcust.entity.GroupCust;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface GroupCustRemote extends GenericLogic<GroupCust, Long> {

    /**
     * excel导入
     * 
     * @param file
     * @return
     * 
     * @author：yuhb
     * @date：2017年2月21日 上午1:35:43
     */
    Map<String, Object> excel(File file);
}