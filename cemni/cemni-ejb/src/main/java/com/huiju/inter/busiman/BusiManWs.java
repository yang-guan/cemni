package com.huiju.inter.busiman;

import javax.ejb.Remote;

@Remote
public interface BusiManWs {

    String nc2crm(String message);
}