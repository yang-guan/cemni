package com.huiju.archive.channel;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.archive.channel.entity.Channel;
import com.huiju.archive.channel.entity.ChannelInfo;
import com.huiju.archive.channel.logic.ChannelInfoRemote;
import com.huiju.archive.channel.logic.ChannelRemote;
import com.huiju.archive.franchisee.entity.Contact;
import com.huiju.archive.franchisee.logic.ContactRemote;
import com.huiju.common.DataDict;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

// 渠道商
public class ChannelAction extends BaseAction<Channel, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private ChannelRemote channelLogic;
    @EJB
    private ChannelInfoRemote channelInfoLogic;
    @EJB
    private ContactRemote contactLogic;

    public String init() throws Exception {
        jsPath.add("/js/archive/channel/Q.archive.channel.js");

        String[] authorities = { "D_CHANNEL_LIST", "D_CHANNEL_EDIT", "D_CHANNEL_UPDATE", "D_CHANNEL_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Page<Channel> page = new Page<Channel>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = channelLogic.findAll(page, params);
        for (Channel cl : page) {
            cl.setTypeName(DataDict.getDictName(DataDict.CHANNEL_TYPE, cl.getType()));
        }
        renderJson(page);
    }

    public void edit() {
        model = channelLogic.findById(id);
        dealJson(true, model);
    }

    public void update() {
        Channel cl = channelLogic.findById(model.getChannelId());
        model.setCdate(cl.getCdate());
        model.setCuser(cl.getCuser());
        model.setIsValid(cl.getIsValid());
        model.setMuser(WebUtils.getUserCode());
        model.setMdate(Calendar.getInstance());
        channelLogic.merge(model);
        dealJson(true);
    }

    // 渠道信息
    public void getRel() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<ChannelInfo> rsList = channelInfoLogic.findAll(params);
        renderJson(rsList);
    }

    // 联系人
    public void getRel1() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<Contact> rsList = contactLogic.findAll(params);
        renderJson(rsList);
    }

}