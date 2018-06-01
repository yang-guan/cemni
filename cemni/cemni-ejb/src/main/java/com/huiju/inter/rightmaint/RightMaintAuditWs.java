package com.huiju.inter.rightmaint;

import javax.ejb.Remote;

@Remote
public interface RightMaintAuditWs {

    String oa2crm(String message);
}