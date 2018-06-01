package com.huiju.inter.individCust;

import javax.ejb.Remote;

@Remote
public interface IndividCustWs {

    String nc2crm(String message);

    String wechar2crm(String message);

    String yw2crm(String message);

    String batchFresh_nc2crm(String message);
}