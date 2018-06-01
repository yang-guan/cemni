package com.huiju.console.org.logic;

import java.util.List;

import javax.ejb.Remote;

import com.huiju.console.org.entity.Org;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings({ "rawtypes" })
public interface OrgRemote extends GenericLogic<Org, Long> {

    List selOrgByParent(Integer type, Long fromOrgId);

    /**
     * 查询组织机构下所有的子节点
     * 
     * @author：yuhb
     * @date：2017年3月31日 下午4:46:58
     */
    List getOrgTreeList(String orgName);

    /**
     * 大区-区域-门店：与字典表项笛卡尔集
     * 
     * @return
     * 
     * @author：yuhb
     * @date：2017年2月25日 下午8:41:54
     */
    List qryBigAreaStore_dict(Integer dictCode);
}