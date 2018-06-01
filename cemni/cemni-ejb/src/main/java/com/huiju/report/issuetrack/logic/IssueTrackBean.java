package com.huiju.report.issuetrack.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.report.issuetrack.eao.IssueTrackEaoLocal;

@Stateless(mappedName = "IssueTrackBean")
@SuppressWarnings({ "rawtypes" })
public class IssueTrackBean extends GenericLogicImpl<PosOrder, Long> implements IssueTrackRemote {
    @EJB
    private IssueTrackEaoLocal issuetrackEao;

    @Override
    protected GenericEao<PosOrder, Long> getGenericEao() {
        return issuetrackEao;
    }

    @Override
    public Map Query(Map searchParam) {
        return issuetrackEao.Query(searchParam);
    }

    @Override
    public List export(Map searchParam) {
        return issuetrackEao.export(searchParam);
    }

}