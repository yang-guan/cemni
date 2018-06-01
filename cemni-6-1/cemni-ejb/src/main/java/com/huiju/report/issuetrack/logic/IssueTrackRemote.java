package com.huiju.report.issuetrack.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface IssueTrackRemote extends GenericLogic<PosOrder, Long> {
    
	Map Query(Map searchParam);

    List export(Map searchParam);
}