package com.huiju.expandbusi.salesmananalyze.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.expandbusi.salesmananalyze.eao.SalesmanAnalyzeEaoLocal;
import com.huiju.expandbusi.salesmananalyze.entity.SalesmanAnalyze;

@Stateless(mappedName = "SalesmanAnalyzeBean")
public class SalesmanAnalyzeBean extends GenericLogicImpl<SalesmanAnalyze, Long> implements SalesmanAnalyzeRemote {
    @EJB(mappedName = "SalesmanAnalyzeEaoBean")
    private SalesmanAnalyzeEaoLocal salesmananalyzeEao;

    @Override
    protected GenericEao<SalesmanAnalyze, Long> getGenericEao() {
        return salesmananalyzeEao;
    }
}