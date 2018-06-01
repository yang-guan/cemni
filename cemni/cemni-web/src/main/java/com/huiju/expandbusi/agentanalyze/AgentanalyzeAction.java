package com.huiju.expandbusi.agentanalyze;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.expandbusi.agentanalyze.entity.Agentanalyze;
import com.huiju.expandbusi.agentanalyze.entity.Detailed;
import com.huiju.expandbusi.agentanalyze.logic.AgentanalyzeRemote;
import com.huiju.expandbusi.agentanalyze.logic.DetailedRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

// 加盟商区域分解
public class AgentanalyzeAction extends BaseAction<Agentanalyze, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private AgentanalyzeRemote agentanalyzeLogic;
    @EJB
    private DetailedRemote detailedLogic;
    @EJB
    private SqlRemote sqlLogic;

    public String init() throws Exception {
        jsPath.add("/js/expandbusi/agentanalyze/Q.expandbusi.agentanalyze.js");

        String[] authorities = { "D_AGENTANALYZE_LIST", "D_AGENTANALYZE_ADD", "D_AGENTANALYZE_DELETE", "D_AGENTANALYZE_EDIT", "D_AGENTANALYZE_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public String getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Page<Agentanalyze> page = new Page<Agentanalyze>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = agentanalyzeLogic.findAll(page, params);
        for (Agentanalyze al : page) {
            al.setTypeName(DataDict.getDictName(DataDict.AGENTANALYZE_TYPE, al.getType()));
        }
        return renderJson(page);
    }

    public void save() {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("EQ_type", model.getType());
        searchParams.put("EQ_year", model.getYear());
        long cnt = agentanalyzeLogic.count(searchParams);
        if (cnt > 0) {
            dealJson(false, "“指标类别、指标年份”信息已存在！");
            return;
        }
        if (null != this.model.getDetailed()) {
            for (Detailed de : this.model.getDetailed()) {
                de.setAgentanalyze(model);
            }
        }
        model.setCuser(WebUtils.getUserName());
        String Agentanalyzeno = sqlLogic.getCnNum(GlobalConst.NUM_AGENTANALYZE);
        model.setAgentanalyzeno(Agentanalyzeno);
        model.setCdate(Calendar.getInstance());
        model = agentanalyzeLogic.persist(model);
        dealJson(true);
    }

    public void edit() {
        model = agentanalyzeLogic.findById(id);
        dealJson(true, model);
    }

    public void update() {
        Agentanalyze al = agentanalyzeLogic.findById(model.getAgentanalyzeid());
        model.setCdate(al.getCdate());
        model.setCuser(al.getCuser());
        model.setMuser(WebUtils.getUserName());
        model.setMdate(Calendar.getInstance());
        agentanalyzeLogic.merge(model);
        dealJson(true);
    }

    // 级联删除
    public void delete() {
        for (Long id : ids) {
            agentanalyzeLogic.removeById(id);
        }
        dealJson(true);
    }

    public void getRel() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<Detailed> rsList = detailedLogic.findAll(params);
        renderJson(rsList);
    }

    /**
     * 
     * @param partYear
     *            数据库年份索引
     * @param partYearIndex
     *            当前年份所属索引
     * @return
     */
    public Integer getPartYear(Integer partYear, Integer partYearIndex) {
        Calendar c = Calendar.getInstance();
        Integer year = c.get(Calendar.YEAR);
        return year + (partYear - partYearIndex);
    }

}