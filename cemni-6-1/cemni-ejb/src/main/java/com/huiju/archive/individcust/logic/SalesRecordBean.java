package com.huiju.archive.individcust.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.archive.individcust.eao.SalesRecordEaoLocal;
import com.huiju.archive.individcust.entity.SalesRecord;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "SalesRecordBean")
public class SalesRecordBean extends GenericLogicImpl<SalesRecord, Long> implements SalesRecordRemote {

	@EJB(mappedName="SalesRecordEaoBean")
    private SalesRecordEaoLocal salesRecordEao;
	@Override
	protected GenericEao<SalesRecord, Long> getGenericEao() {
		return salesRecordEao;
	}
}