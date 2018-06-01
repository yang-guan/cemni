package com.huiju.report.issuetrack.eao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface IssueTrackEaoLocal extends GenericEao<PosOrder, Long> {

    Map Query(Map searchParam);

    List export(Map searchParam);
    
}