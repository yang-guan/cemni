package com.huiju.sms.sms.logic;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.console.dict.entity.Dict;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.sms.sms.entity.Sms;
import com.huiju.sms.sms.entity.SmsParamVar;
import com.huiju.sms.sms.entity.TypeCond;

@Remote
@SuppressWarnings({ "rawtypes" })
public interface SmsRemote extends GenericLogic<Sms, Long> {

    List<Dict> selSmsTypeStore();

    List<SmsParamVar> selParamVar(Integer type);

    List<TypeCond> selTypeCond(Integer type);

    List<IndividCust> qryObjCust(Long smsId);

    /**
     * 发送对象-excel导入
     * 
     * @param file
     * @return
     * @throws Exception
     * 
     * @author：yuhb
     * @date：2017年2月18日 下午5:52:36
     */
    Map uploadExcel(File file) throws Exception;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 扫描短信模版-发短信
     * 
     * @author：yuhb
     * @date：2017年1月2日 上午11:14:06
     */
    void scanSms();

    void immediateSendSms(Integer type, int src, Object mobileOrPosNo);

    void bigSaleSendSms(String posNo);
}