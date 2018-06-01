package com.huiju.archive.individcust.eao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.module.data.Page;
import com.huiju.module.data.eao.GenericEao;

@Local
@SuppressWarnings("rawtypes")
public interface IndividCustEaoLocal extends GenericEao<IndividCust, Long> {

    Page<IndividCust> findAllExcludeBolb(Page<IndividCust> custPage, Map<String, Object> paramsMap);

    void updateBelongStore(String isAllSelected, List<Long> individCustIds, Map searchParam, String belongStoreNo, String belongStoreName, String startPage, String endPage);

    Page<IndividCust> findCustPage(Page<IndividCust> custPage, List<Map<String, Object>> paramsList);

    public String getIndividCustWhereStr(Map searchParam);

    String convertInString(List list, String str);
}