package com.huiju.inter.integral;

import javax.ejb.Remote;

@Remote
public interface IntegralWs {

    String integral2crm(String message);
}