package com.huiju.inter.sms;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface YunpianSmsRemote {

    /**
     * 批量发送短信
     * 
     * @param smsId
     *            短信模版ID
     * @param type
     *            短信类型
     * @param mobile
     *            手机号码
     * @param text
     *            短信内容
     * 
     * @author：yuhb
     * @date：2017年1月24日 上午12:00:35
     */
    void multiSends(Long smsId, Integer type, List<Long> mobile, List<String> text);

    /**
     * 单个发送短信
     * 
     * @param smsId
     *            短信模版ID
     * @param type
     *            短信类型
     * @param mobile
     *            手机号码
     * @param text
     *            短信内容
     * 
     * @author：yuhb
     * @date：2017年1月24日 上午12:19:57
     */
    Integer singleSends(Long smsId, Integer type, Long mobile, String text);

    /**
     * 手动触发-重新发送
     * 
     * @param ids
     *            需要重新发送短信的ids
     * @param userName
     *            发送人
     * 
     * @author：yuhb
     * @date：2017年1月24日 上午1:15:11
     */
    void reSendSms(List<Long> ids, String userName);
}