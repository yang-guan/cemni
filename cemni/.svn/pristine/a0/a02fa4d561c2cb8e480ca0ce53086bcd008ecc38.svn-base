package com.huiju.competitor;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.competitor.entity.AdDeploy;
import com.huiju.competitor.entity.ChannelSurvey;
import com.huiju.competitor.entity.Competitor;
import com.huiju.competitor.entity.CustSurvey;
import com.huiju.competitor.entity.GoodStatus;
import com.huiju.competitor.entity.MarActivitty;
import com.huiju.competitor.entity.PersonnelInfo;
import com.huiju.competitor.entity.SzNewStyles;
import com.huiju.competitor.logic.AdDeployRemote;
import com.huiju.competitor.logic.ChannelSurveyRemote;
import com.huiju.competitor.logic.CompetitorRemote;
import com.huiju.competitor.logic.CustSurveyRemote;
import com.huiju.competitor.logic.GoodStatusRemote;
import com.huiju.competitor.logic.MarActivittyRemote;
import com.huiju.competitor.logic.PersonnelInfoRemote;
import com.huiju.competitor.logic.SzNewStylesRemote;
import com.huiju.module.data.Page;
import com.huiju.module.fs.entity.FileInfo;
import com.huiju.module.fs.logic.FileInfoRemote;
import com.huiju.module.log.annotation.Logging;
import com.huiju.module.log.annotation.Logging.LogType;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

public class CompetitorAction extends BaseAction<Competitor, Long> {
    private static final long serialVersionUID = 1L;

    @EJB(mappedName = "CompetitorBean")
    private CompetitorRemote competitorLogic;

    @EJB(mappedName = "AdDeployBean")
    private AdDeployRemote competitorADLogic;

    @EJB(mappedName = "ChannelSurveyBean")
    private ChannelSurveyRemote competitorCNRLogic;

    @EJB(mappedName = "CustSurveyBean")
    private CustSurveyRemote competitorCURLogic;

    @EJB(mappedName = "PersonnelInfoBean")
    private PersonnelInfoRemote competitorEPLogic;

    @EJB(mappedName = "GoodStatusBean")
    private GoodStatusRemote competitorGSLogic;

    @EJB(mappedName = "SzNewStylesBean")
    private SzNewStylesRemote competitorSZNSLogic;

    @EJB(mappedName = "MarActivittyBean")
    private MarActivittyRemote competitorTMLogic;

    @EJB
    private FileInfoRemote fileInfoLogic;

    @Logging(module = "COMPETITOR", action = "init", message = "竞争对手管理", type = LogType.SYSTEM)
    public String init() throws Exception {
        jsPath.add("/js/competitor/Q.competitor.js");

        String[] authorities = { "D_COMPETITOR_LIST", "D_COMPETITOR_ADD", "D_COMPETITOR_EDIT", "D_COMPETITOR_DELETE", "D_COMPETITOR_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public String getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Page<Competitor> page = new Page<Competitor>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = competitorLogic.findAll(page, params);

        List<Competitor> list = page.getResult();
        for (Competitor cc : list) {
            cc.setCpnameName(DataDict.getDictName(DataDict.COMPETITOR_CPNAME, Integer.parseInt(cc.getCpname())));
            String deptCode = cc.getSurveydept();
            if (deptCode.equals(GlobalConst.DEP_ORGCODE_SP)) {//商品部
                cc.setSurveydeptName(GlobalConst.DEP_ORGName_SP);
            } else if (deptCode.equals(GlobalConst.DEP_ORGCODE_KF)) {//客服部
                cc.setSurveydeptName(GlobalConst.DEP_ORGName_KF);
            } else if (deptCode.equals(GlobalConst.DEP_ORGCODE_PP)) {//品牌部
                cc.setSurveydeptName(GlobalConst.DEP_ORGName_PP);
            } else if (deptCode.equals(GlobalConst.DEP_ORGCODE_LZ1)) {//连锁发展一部
                cc.setSurveydeptName(GlobalConst.DEP_ORGName_LZ1);
            } else if (deptCode.equals(GlobalConst.DEP_ORGCODE_LZ2)) {//连锁发展二部
                cc.setSurveydeptName(GlobalConst.DEP_ORGName_LZ2);
            } else if (deptCode.equals(GlobalConst.DEP_ORGCODE_RZ)) {//人力资源部
                cc.setSurveydeptName(GlobalConst.DEP_ORGName_RZ);
            }
        }
        return renderJson(page);
    }

    public String save() {
        this.saveOneToMany();
        model.setCtime(Calendar.getInstance());
        model.setCuser(WebUtils.getUserCode());
        competitorLogic.persist(model);
        return dealJson(true);
    }

    // 级联删除
    public void delete() {
        for (Long id : ids) {
            competitorLogic.removeById(id);
        }
        dealJson(true);
    }

    public String edit() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        searchParams.put("EQ_cpid", id);
        model = competitorLogic.find(searchParams);
        if (model == null) {
            return dealJson(false, getText("message.notexisted"));
        }
        return dealJson(true, model);
    }

    public String update() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        searchParams.put("EQ_cpid", model.getCpid());
        Competitor competitor = competitorLogic.find(searchParams);

        if (competitor == null) {
            return dealJson(false, getText("message.notexisted"));
        }
        competitor.setCpname(model.getCpname());
        competitor.setSurveytime(model.getSurveytime());
        competitor.setSurveycity(model.getSurveycity());
        competitor.setSurveydept(model.getSurveydept());
        competitor.setBrandgoal(model.getBrandgoal());
        competitor.setCommercialmode(model.getCommercialmode());
        competitor.setTypicalprocon(model.getTypicalprocon());
        competitor.setCuser(WebUtils.getUserCode());

        this.saveOneToMany();
        competitor.setCustsurveyList(model.getCustsurveyList());
        competitor.setPerinfolist(model.getPerinfolist());
        competitor.setChansurveyList(model.getChansurveyList());
        competitor.setMaractivityList(model.getMaractivityList());
        competitor.setAdDeployList(model.getAdDeployList());
        competitor.setGoodStatusList(model.getGoodStatusList());
        competitor.setNewStyleList(model.getNewStyleList());
        competitorLogic.merge(competitor);
        return dealJson(true);
    }

    public void saveOneToMany() {
        if (null != this.model.getCustsurveyList()) {
            for (CustSurvey as : this.model.getCustsurveyList()) {
                as.setCompetitor(model);
            }
        } // 客户调研

        if (this.model.getPerinfolist() != null) {
            for (PersonnelInfo as : this.model.getPerinfolist()) {
                as.setCompetitor(model);
            }
        } // 认识信息

        if (this.model.getChansurveyList() != null) {
            for (ChannelSurvey as : this.model.getChansurveyList()) {
                as.setCompetitor(model);
            }
        } // 渠道调研

        if (this.model.getMaractivityList() != null) {
            for (MarActivitty as : this.model.getMaractivityList()) {
                as.setCompetitor(model);
            }
        } // 市场活动

        if (this.model.getAdDeployList() != null) {
            for (AdDeploy as : this.model.getAdDeployList()) {
                as.setCompetitor(model);
            }
        } // 广告投放

        if (this.model.getGoodStatusList() != null) {
            for (GoodStatus as : this.model.getGoodStatusList()) {
                as.setCompetitor(model);
            }
        } // 商品动态

        if (this.model.getNewStyleList() != null) {
            for (SzNewStyles as : this.model.getNewStyleList()) {
                as.setCompetitor(model);
            }
        } // 款式动态
    }

    // 客户调研
    public String getCustSurvey() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Map<String, Object> searchparams = new HashMap<String, Object>();

        List<CustSurvey> list = competitorCURLogic.findAll(params);
        for (CustSurvey cust : list) {
            searchparams.put("EQ_fileGroup_fileGroupId", cust.getUploadFileGroupId());
            List<FileInfo> filelist = fileInfoLogic.findAll(searchparams);
            if (!CollectionUtils.isEmpty(filelist)) {
                String[] arr = { filelist.get(0).getFileName(), filelist.get(0).getFileCode() };
                cust.setUploadFile4View(arr);
            }
        }
        return renderJson(list);
    }

    // 人事信息
    public String getPerInfo() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Map<String, Object> searchparams = new HashMap<String, Object>();
        List<PersonnelInfo> list = competitorEPLogic.findAll(params);

        for (PersonnelInfo cust : list) {
            searchparams.put("EQ_fileGroup_fileGroupId", cust.getUploadFileGroupId());
            List<FileInfo> filelist = fileInfoLogic.findAll(searchparams);
            if (!CollectionUtils.isEmpty(filelist)) {
                String[] arr = { filelist.get(0).getFileName(), filelist.get(0).getFileCode() };
                cust.setUploadFile4View(arr);
            }
        }
        return renderJson(list);
    }

    // 渠道调研
    public String getChanSurvey() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<ChannelSurvey> list = competitorCNRLogic.findAll(params);

        Map<String, Object> searchparams = new HashMap<String, Object>();
        for (ChannelSurvey cust : list) {
            searchparams.put("EQ_fileGroup_fileGroupId", cust.getUploadFileGroupId());
            List<FileInfo> filelist = fileInfoLogic.findAll(searchparams);
            if (!CollectionUtils.isEmpty(filelist)) {
                String[] arr = { filelist.get(0).getFileName(), filelist.get(0).getFileCode() };
                cust.setUploadFile4View(arr);
            }
        }
        return renderJson(list);
    }

    // 市场活动
    public String getMarActivity() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<MarActivitty> list = competitorTMLogic.findAll(params);

        Map<String, Object> searchparams = new HashMap<String, Object>();
        for (MarActivitty cust : list) {
            searchparams.put("EQ_fileGroup_fileGroupId", cust.getUploadFileGroupId());
            List<FileInfo> filelist = fileInfoLogic.findAll(searchparams);
            if (!CollectionUtils.isEmpty(filelist)) {
                String[] arr = { filelist.get(0).getFileName(), filelist.get(0).getFileCode() };
                cust.setUploadFile4View(arr);
            }
        }
        return renderJson(list);
    }

    // 广告投放
    public String getadDeploy() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<AdDeploy> list = competitorADLogic.findAll(params);

        Map<String, Object> searchparams = new HashMap<String, Object>();
        for (AdDeploy cust : list) {
            searchparams.put("EQ_fileGroup_fileGroupId", cust.getUploadFileGroupId());
            List<FileInfo> filelist = fileInfoLogic.findAll(searchparams);
            if (!CollectionUtils.isEmpty(filelist)) {
                String[] arr = { filelist.get(0).getFileName(), filelist.get(0).getFileCode() };
                cust.setUploadFile4View(arr);
            }
        }
        return renderJson(list);
    }

    // 商品动态
    public String getStatus() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<GoodStatus> list = competitorGSLogic.findAll(params);

        Map<String, Object> searchparams = new HashMap<String, Object>();
        for (GoodStatus cust : list) {
            searchparams.put("EQ_fileGroup_fileGroupId", cust.getUploadFileGroupId());
            List<FileInfo> filelist = fileInfoLogic.findAll(searchparams);
            if (!CollectionUtils.isEmpty(filelist)) {
                String[] arr = { filelist.get(0).getFileName(), filelist.get(0).getFileCode() };
                cust.setUploadFile4View(arr);
            }
        }
        return renderJson(list);
    }

    // 新的款式动态
    public String getnewStyle() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<SzNewStyles> list = competitorSZNSLogic.findAll(params);

        Map<String, Object> searchparams = new HashMap<String, Object>();
        for (SzNewStyles cust : list) {
            searchparams.put("EQ_fileGroup_fileGroupId", cust.getUploadFileGroupId());
            List<FileInfo> filelist = fileInfoLogic.findAll(searchparams);
            if (!CollectionUtils.isEmpty(filelist)) {
                String[] arr = { filelist.get(0).getFileName(), filelist.get(0).getFileCode() };
                cust.setUploadFile4View(arr);
            }
        }
        return renderJson(list);
    }
}