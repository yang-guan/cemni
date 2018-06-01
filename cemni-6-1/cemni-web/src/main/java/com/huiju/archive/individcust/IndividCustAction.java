package com.huiju.archive.individcust;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.EJB;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.huiju.actment.activity.entity.Activity;
import com.huiju.actment.activity.logic.ActivityRemote;
import com.huiju.actment.activity.logic.IndiPartInRemote;
import com.huiju.afterservice.busiregist.entity.BusiRegist;
import com.huiju.afterservice.busiregist.logic.BusiRegistRemote;
import com.huiju.afterservice.callregist.logic.CallRegistRemote;
import com.huiju.afterservice.rightmaint.entity.RightMaint;
import com.huiju.afterservice.rightmaint.logic.RightMaintRemote;
import com.huiju.archive.groupcust.entity.GroupCust;
import com.huiju.archive.groupcust.logic.GroupCustRemote;
import com.huiju.archive.individcust.entity.ActiveStatus;
import com.huiju.archive.individcust.entity.Anniversary;
import com.huiju.archive.individcust.entity.CustStatus;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.entity.IndividCustFamily;
import com.huiju.archive.individcust.entity.IndividCustImg;
import com.huiju.archive.individcust.entity.OperationLog;
import com.huiju.archive.individcust.entity.SalesRecord;
import com.huiju.archive.individcust.logic.ActiveStatusRemote;
import com.huiju.archive.individcust.logic.AnniversaryRemote;
import com.huiju.archive.individcust.logic.IndividCustFamilyRemote;
import com.huiju.archive.individcust.logic.IndividCustImgRemote;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.archive.individcust.logic.OperationLogRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.area.logic.AreaRemote;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.org.logic.OrgRemote;
import com.huiju.console.store.logic.StoreRemote;
import com.huiju.console.user2org.logic.User2orgRemote;
import com.huiju.integral.gradeadj.entity.GradeAdjHis;
import com.huiju.integral.gradeadj.logic.GradeAdjHisRemote;
import com.huiju.integral.graderule.entity.GradeRule;
import com.huiju.integral.graderule.logic.GradeRuleRemote;
import com.huiju.integral.integraladj.entity.IntegralAdjHis;
import com.huiju.integral.integraladj.logic.IntegralAdjHisRemote;
import com.huiju.inter.afterserv.logic.AfterServRemote;
import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.inter.posorder.logic.PosOrderRemote;
import com.huiju.inter.sms.YunpianSmsRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.fs.entity.FileInfo;
import com.huiju.module.fs.logic.FileInfoRemote;
import com.huiju.module.notify.jms.NotifySenderRemote;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.module.weixin.common.util.StringUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;
import com.huiju.sms.sms.logic.SmsRemote;
import com.huiju.sms.smslog.entity.SmsLog;
import com.huiju.sms.smslog.logic.SmsLogRemote;
import com.huiju.utils.NeuUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class IndividCustAction extends BaseAction<IndividCust, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private UserAuthGroupRemote userAuthGroupLogic;
    @EJB
    private IndividCustRemote individCustLogic;
    @EJB
    private IndividCustImgRemote imgLogic;
    @EJB
    private User2orgRemote user2orgLogic;
    @EJB
    private SmsRemote smsLogic;
    @EJB
    private AreaRemote areaLogic;
    @EJB
    private GroupCustRemote groupCustLogic;
    @EJB
    private ActivityRemote activityLogic;
    @EJB
    private IndividCustFamilyRemote individCustFamilyLogic;
    @EJB
    private AnniversaryRemote anniversaryLogic;
    @EJB
    private OperationLogRemote operLogLogic;
    @EJB
    private PosOrderRemote posOrderLogic;
    @EJB
    private ActiveStatusRemote activeStatusLogic;
    @EJB
    private AfterServRemote afterServLogic;
    @EJB
    private IntegralAdjHisRemote integralAdjHisLogic;
    @EJB
    private GradeAdjHisRemote gradeAdjHisLogic;
    @EJB
    private SqlRemote sqlLogic;
    @EJB
    private PosOrderRemote poslogic;
    @EJB
    private GradeAdjHisRemote gradeadjhislogic;
    @EJB
    private IntegralAdjHisRemote integraladjhislogic;
    @EJB
    private StoreRemote storelogic;
    @EJB
    private IndiPartInRemote indiPartInlogic;
    @EJB
    private SmsLogRemote smsLogLogic;

    @EJB(mappedName = "YunpianSms")
    private YunpianSmsRemote yunpianSms;
    @EJB
    private RightMaintRemote rightMaintlogic;
    @EJB
    private BusiRegistRemote busiregistlogic;
    @EJB
    private CallRegistRemote callregistLogic;
    @EJB
    private FileInfoRemote fileInfoLogic;

    @EJB(mappedName = "NotifySender")
    protected NotifySenderRemote notifySender;
    @EJB
    private GradeRuleRemote gradeRuleLogic;
    @EJB
    private OrgRemote appLogic;

    private File file;
    private String fileName;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String init() {
        List<String> jsArr = new ArrayList<String>();
        jsArr.add("/js/archive/individcust/Q.archive.custBatchSearchWin.js");
        jsArr.add("/js/archive/individcust/Q.archive.custSearchWin.js");
        jsArr.add("/js/archive/individcust/Q.batch.belongStoreWin.js");
        jsArr.add("/js/archive/individcust/Q.archive.chooseIndiWin.js");
        jsArr.add("/js/actment/activity/Q.actment.chooseActWin.js");
        jsArr.add("/js/archive/individcust/Q.archive.chooseGroupWin.js");
        jsArr.add("/js/console/store/Q.store.chooseStoreWin.js");
        jsArr.add("/js/common/Q.excel.uploadWin.js");
        jsArr.add("/js/common/Q.img.uploadWin.js");
        jsArr.add("/js/console/org/Q.org.chooseOrgWin.js");
        jsArr.add("/js/afterservice/busiregist/Q.afterservice.chooseOrgStoreWin.js");
        jsArr.add("/js/archive/individcust/Q.archive.ncNoHisWin.js");
        jsArr.add("/js/archive/individcust/Q.archive.individcust.js");
        jsPath.addAll(jsArr);

        String[] authorities = { "D_INDIVIDCUST_LIST", "D_INDIVIDCUST_ADD", "D_INDIVIDCUST_EDIT", "D_INDIVIDCUST_SEARCH", "D_INDIVIDCUST_IMPORT", "D_INDIVIDCUST_BATCHSEARCH", "D_INDIVIDCUST_CHANGECODE", "D_INDIVIDCUST_CHANGECODE2", "D_INDIVIDCUST_EXPORT", "D_INDIVIDCUST_DOWNLOAD", "D_INDIVIDCUST_SENDSMG", "D_INDIVIDCUST_BELONGSTORE", "D_INDIVIDCUST_NCNOHIS" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    // 个人客户页面getJson
    public void getJson() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.put("DISTINCT", true);
        Object obj = searchParam.get("cid");

        // 批量查询：“belongStoreNo”的值取资源组与页面查询条件相同的内容的交集
        if (null == obj) {
            Map<String, Object> authMap = userAuthGroupLogic.buildAuthFieldParams(WebUtils.getClientCode(), WebUtils.getUserCode(), IndividCust.class);

            Object req_belongStoreNo = searchParam.get("IN_belongStoreNo");// 在权限填充之前先获取到对应的值
            searchParam.putAll(authMap);

            String rs_belongStoreNo = "";
            if (null != req_belongStoreNo && !"".equals(req_belongStoreNo)) {
                String[] belongStoreNo = req_belongStoreNo.toString().split(",");

                Object auth_in = authMap.get("IN_belongStoreNo");
                if (auth_in != null && !"".equals(auth_in)) {
                    String[] auth_belongStoreNo_arr = auth_in.toString().split(",");
                    for (String str : belongStoreNo) {
                        for (String auth : auth_belongStoreNo_arr) {
                            if (str.equals(auth)) {
                                rs_belongStoreNo += "," + str;
                            }
                        }
                    }
                    searchParam.put("IN_belongStoreNo", (StringUtils.isNotBlank(rs_belongStoreNo) ? rs_belongStoreNo.substring(1) : GlobalConst.SQL_PARAM_VAL_NULL));
                }
                Object auth_eq = authMap.get("EQ_belongStoreNo");
                if (auth_eq != null && !"".equals(auth_eq)) {
                    String auth_str = auth_eq.toString();
                    for (String str : belongStoreNo) {
                        if (str.equals(auth_str)) {
                            rs_belongStoreNo += "," + str;
                        }
                    }
                    searchParam.remove("EQ_belongStoreNo");
                    searchParam.put("IN_belongStoreNo", (StringUtils.isNotBlank(rs_belongStoreNo) ? rs_belongStoreNo.substring(1) : GlobalConst.SQL_PARAM_VAL_NULL));
                }
            }

            Object req_LE_lastBuyTime = searchParam.get("LE_lastBuyTime");
            if (req_LE_lastBuyTime != null) {
                Calendar LE_lastBuyTime = NeuUtils.parseCalendar(req_LE_lastBuyTime.toString());
                LE_lastBuyTime.add(Calendar.DAY_OF_MONTH, 1);
                searchParam.put("LE_lastBuyTime", NeuUtils.parseStringFromCalendar(LE_lastBuyTime));
            }
            Object req_LE_cdate = searchParam.get("LE_cdate");
            if (req_LE_cdate != null) {
                Calendar LE_cdate = NeuUtils.parseCalendar(req_LE_cdate.toString());
                LE_cdate.add(Calendar.DAY_OF_MONTH, 1);
                searchParam.put("LE_cdate", NeuUtils.parseStringFromCalendar(LE_cdate));
            }

            // 特殊处理
            Object IN_posOrderList_scoreSegment = searchParam.get("IN_posOrderList_scoreSegment");
            if (IN_posOrderList_scoreSegment != null) {
                searchParam.put("IN_posOrderList_stockType", "10,12,14,15".split(","));
            }
        } else if (obj.equals("1")) {// 精确查询
            searchParam.remove("cid");
        }
        Object statusObj = searchParam.get("IN_status");
        if (null != statusObj) {
            List<CustStatus> statusList = new ArrayList<CustStatus>();
            String[] IN_status_arr = statusObj.toString().split(",");
            for (String str : IN_status_arr) {
                switch (Integer.parseInt(str)) {
                case 0:
                    statusList.add(CustStatus.NEW);
                    break;
                case 1:
                    statusList.add(CustStatus.CONFIRM);
                    break;
                case 2:
                    statusList.add(CustStatus.PASS);
                    break;
                case 3:
                    statusList.add(CustStatus.NOPASS);
                    break;
                }
            }
            searchParam.put("IN_status", statusList);
        }
        if (limit == 0) {
            limit = 20;
        }

        // 是否需要关联pos
        boolean posFlag = false;
        for (String key : searchParam.keySet()) {
            if (key.contains("posOrderList")) {
                posFlag = true;
                break;
            }
        }
        if (posFlag) {
            searchParam.put("JOIN", "posOrderList_INNER");
        }

        Page<IndividCust> page = new Page<IndividCust>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = individCustLogic.qryIndividCust(page, searchParam);
        renderJson(page);
    }

    // 其他功能所用到的弹出框的getJson
    public void getJsonAll() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.put("DISTINCT", true);
        Object statusObj = searchParam.get("IN_status");
        if (null != statusObj) {
            List<CustStatus> statusList = new ArrayList<CustStatus>();
            String[] IN_status_arr = statusObj.toString().split(",");
            for (String str : IN_status_arr) {
                switch (Integer.parseInt(str)) {
                case 0:
                    statusList.add(CustStatus.NEW);
                    break;
                case 1:
                    statusList.add(CustStatus.CONFIRM);
                    break;
                case 2:
                    statusList.add(CustStatus.PASS);
                    break;
                case 3:
                    statusList.add(CustStatus.NOPASS);
                    break;
                }
            }
            searchParam.put("IN_status", statusList);
        }
        if (limit == 0) {
            limit = 20;
        }

        // 是否需要关联pos
        boolean posFlag = false;
        for (String key : searchParam.keySet()) {
            if (key.contains("posOrderList")) {
                posFlag = true;
                break;
            }
        }
        if (posFlag) {
            searchParam.put("JOIN", "posOrderList_INNER");
        }
        Page<IndividCust> page = new Page<IndividCust>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = individCustLogic.findAll(page, searchParam);

        for (IndividCust dt : page) {
            dt.setCreditStatusName(DataDict.getDictName(DataDict.INTEGRALADJHIS_CREDITSTATUS, dt.getCreditStatus()));
            dt.setLvName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
            dt.setTypeName(DataDict.getDictName(DataDict.INDIVIDCUST_TYPE, dt.getType()));
            dt.setGenderName(DataDict.getDictName(DataDict.FRANCHISEE_SEX, dt.getGender()));
            dt.setEnableName(DataDict.getDictName(DataDict.INDIVIDCUST_ENABLE, dt.getEnable()));
            dt.setActiveName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getActive()));
            dt.setFreshName(DataDict.getDictName(DataDict.INDIVIDCUST_FRESH, dt.getFresh()));
        }
        renderJson(page);
    }

    public void save() {
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put("EQ_mobile", model.getMobile());
        List<IndividCust> rsList = individCustLogic.findAll(searchParam);
        if (rsList.size() > 0) {
            dealJson(false, "该手机号码在系统中已存在，不能重复使用！");
            return;
        }
        if (null != this.model.getIndividCustFamily()) {
            for (IndividCustFamily icf : this.model.getIndividCustFamily()) {
                icf.setIndividCust(model);
            }
        }
        if (null != this.model.getAnniversary()) {
            for (Anniversary ann : this.model.getAnniversary()) {
                ann.setIndividCust(model);
            }
        }
        if (null != this.model.getSalesRecord()) {
            for (SalesRecord sr : this.model.getSalesRecord()) {
                sr.setIndividCust(model);
            }
        }
        if (null != this.model.getActiveStatus()) {
            for (ActiveStatus as : this.model.getActiveStatus()) {
                as.setIndividCust(model);
            }
        }

        // 默认归属“客服部”
        if (model.getSrcStoreNo() == null) {
            model.setBelongStoreNo(GlobalConst.DEP_ORGCODE_KF);
            model.setBelongStoreName(GlobalConst.DEP_ORGName_KF);
        } else {
            model.setBelongStoreNo(model.getSrcStoreNo());
            model.setBelongStoreName(model.getSrcStoreName());
        }
        model.setStatus(CustStatus.NEW);
        model.setActive(1);// 活跃
        model.setEnable(1);// 启用
        model.setCuser(WebUtils.getUserName());
        model.setCdate(Calendar.getInstance());
        model.setFresh(GlobalConst.FRESH_0);
        model.setIsImport(GlobalConst.NO);
        model.setLv(GlobalConst.CUST_LV_FANS);
        model.setCredit(0D);
        model.setCreditStatus(1);
        model.setConvertedCredits(0D);
        model.setJewerlyAmount(0D);
        model.setCardNo(sqlLogic.getCnNum(GlobalConst.NUM_INDIVID));
        model.setBirthMonthday(NeuUtils.parseStringFromCalendar(model.getBirthday(), "MM-dd"));
        model.setAge(individCustLogic.getIndividCustAge(model.getBirthday()));
        model = individCustLogic.persist(model);

        // 保存头像
        String imgPath = model.getImgPath();
        if (StringUtils.isNotBlank(imgPath)) {
            IndividCustImg img = new IndividCustImg();
            img.setIndividCustId(model.getIndividCustId());

            InputStream in = null;
            try {
                in = new FileInputStream(imgPath);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = in.read(bytes)) > 0) {
                    out.write(bytes, 0, len);
                }
                out.toByteArray();
                img.setImage(out.toByteArray());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            imgLogic.persist(img);
        }

        dealJson(true, GlobalConst.TIP_SUCCESS);

        // 核定记录
        OperationLog operLog = new OperationLog();
        operLog.setIndividCustId(model.getIndividCustId());
        operLog.setResult("新建");
        operLog.setCuser(WebUtils.getUserName());
        operLog.setCdate(Calendar.getInstance());
        operLog.setType(GlobalConst.OPER_TYPE_1);
        operLogLogic.persist(operLog);

        // 入会提醒-短信
        smsLogic.immediateSendSms(GlobalConst.SMS_TYPE_5, GlobalConst.SYS_SRC_CRM, model.getMobile());

        // crm发起同步个人档案信息到外系统
        String msg = individCustLogic.synIndividCustToEx(model);
        if (StringUtils.isNotBlank(msg)) {
            dealJson(false, msg + GlobalConst.TIP_LINK_ADMIN);
        } else {
            dealJson(true, GlobalConst.TIP_SUCCESS);
        }
    }

    public void edit() {
        model = individCustLogic.findById(id);
        // 所属团体
        Long groupCustId = model.getGroupCustId();
        if (groupCustId != null) {
            GroupCust dt = groupCustLogic.findById(groupCustId);
            if (dt != null) {// 导入时可能信息不存在
                model.setGroupCustName(dt.getGroupName());
            }
        }
        // 活动方案
        Long planId = model.getPlanId();
        if (planId != null) {
            Activity dt = activityLogic.findById(planId);
            if (dt != null) {// 导入时可能信息不存在
                model.setPlanName(dt.getActivityTheme());
            }
        }
        // 异业联盟活动
        Long unionActivitiesId = model.getUnionActivitiesId();
        if (unionActivitiesId != null) {
            Activity dt = activityLogic.findById(unionActivitiesId);
            if (dt != null) {// 导入时可能信息不存在
                model.setUnionActivitiesName(dt.getActivityTheme());
            }
        }
        dealJson(true, model);
    }

    public void update() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.put("EQ_mobile", model.getMobile());
        IndividCust dt = individCustLogic.find(searchParam);
        if (dt != null && dt.getIndividCustId() != model.getIndividCustId().longValue()) {
            dealJson(false, "该手机号码在系统中已存在，不能重复使用！");
            return;
        }

        IndividCust cust = individCustLogic.findById(model.getIndividCustId());
        if (model.getSrcStoreNo() == null) {
            model.setBelongStoreNo(GlobalConst.DEP_ORGCODE_KF);
            model.setBelongStoreName(GlobalConst.DEP_ORGName_KF);
        } else if (cust.getLastStoreNo()== null){
            model.setBelongStoreNo(model.getSrcStoreNo());
            model.setBelongStoreName(model.getSrcStoreName());
        }else {
            model.setBelongStoreNo(cust.getLastStoreNo());
            model.setBelongStoreName(cust.getLastStoreName());
        }
        model.setCuser(cust.getCuser());
        model.setLv(cust.getLv());
        model.setCredit(cust.getCredit());
        model.setCreditStatus(cust.getCreditStatus());
        model.setConvertedCredits(cust.getConvertedCredits());
        model.setStatus(cust.getStatus());
        model.setEnable(cust.getEnable());
        model.setActive(cust.getActive());
        model.setJewerlyAmount(cust.getJewerlyAmount());
        model.setFresh(cust.getFresh());
        model.setFreshChgTime(cust.getFreshChgTime());
        model.setIsImport(cust.getIsImport());
        model.setCdate(cust.getCdate());
        model.setFristBuyTime(cust.getFristBuyTime());
        model.setFristStoreNo(cust.getFristStoreNo());
        model.setFristStoreName(cust.getFristStoreName());
        model.setLastBuyTime(cust.getLastBuyTime());
        model.setLastStoreNo(cust.getLastStoreNo());
        model.setLastStoreName(cust.getLastStoreName());
        model.setBirthMonthday(NeuUtils.parseStringFromCalendar(model.getBirthday(), "MM-dd"));
        model.setAge(individCustLogic.getIndividCustAge(model.getBirthday()));
        model = individCustLogic.merge(model);
        individCustLogic.addCustOperationLog(WebUtils.getUserName(), cust, model);

        // 修改头像
        String imgPath = model.getImgPath();
        if (StringUtils.isNotBlank(imgPath) && !imgPath.equals(cust.getImgPath())) {
            byte[] image = null;
            InputStream in = null;
            try {
                in = new FileInputStream(imgPath);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = in.read(bytes)) > 0) {
                    out.write(bytes, 0, len);
                }
                out.toByteArray();
                image = out.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            Map imgParam = new HashMap();
            imgParam.put("EQ_individCustId", model.getIndividCustId());
            IndividCustImg img = imgLogic.find(imgParam);
            if (img == null) {
                img = new IndividCustImg();
                img.setIndividCustId(model.getIndividCustId());
                img.setImage(image);
                imgLogic.persist(img);
            } else {
                img.setImage(image);
                imgLogic.merge(img);
            }
        }

        // crm发起同步个人档案信息到外系统
        String msg = individCustLogic.synIndividCustToEx(model);
        if (StringUtils.isNotBlank(msg)) {
            dealJson(false, msg + GlobalConst.TIP_LINK_ADMIN);
        } else {
            dealJson(true, GlobalConst.TIP_SUCCESS);
        }
    }

    public void excel() {
        Map<String, Object> rsMap = null;
        try {
            FileInfo fileInfo = fileInfoLogic.upload(fileName, file);
            rsMap = individCustLogic.excel(fileInfoLogic.convert(fileInfo).getFile(), WebUtils.getUserName());
        } catch (Exception e) {
            rsMap = new HashMap<String, Object>();
            rsMap.put("success", false);
            rsMap.put("msg", e.getMessage());
        }
        renderHtml(DataUtils.toJson(rsMap));
    }

    public void export() {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=IndividCust.xlsx");

        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.remove("cid");
        Object statusObj = searchParam.get("IN_status");
        if (null != statusObj) {
            List<CustStatus> statusList = new ArrayList<CustStatus>();
            String[] IN_status_arr = statusObj.toString().split(",");
            for (String str : IN_status_arr) {
                switch (Integer.parseInt(str)) {
                case 0:
                    statusList.add(CustStatus.NEW);
                    break;
                case 1:
                    statusList.add(CustStatus.CONFIRM);
                    break;
                case 2:
                    statusList.add(CustStatus.PASS);
                    break;
                case 3:
                    statusList.add(CustStatus.NOPASS);
                    break;
                }
            }
            searchParam.put("IN_status", statusList);
        }

        String[] sorts = { "individCustId,desc" };
        List<IndividCust> rsList = null;
        if (searchParam.size() > 0) {
            rsList = individCustLogic.findAll(searchParam, sorts);
        } else {
            rsList = individCustLogic.findAll(sorts);
        }

        SXSSFWorkbook wb = null;
        try {
            wb = new SXSSFWorkbook(100);
            SXSSFSheet sheet = wb.createSheet("个人客户档案");
            sheet.createFreezePane(0, 1);// 冻结首行
            sheet.setDefaultColumnWidth(13);// 默认宽度

            CellStyle timeCellStyle = wb.createCellStyle();
            timeCellStyle.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd"));

            sheet.setDefaultColumnStyle(12, timeCellStyle);
            sheet.setColumnWidth(12, 13 * 256);
            sheet.setColumnWidth(3, 6 * 256);
            sheet.setColumnWidth(10, 25 * 256);
            sheet.setColumnWidth(11, 25 * 256);

            SXSSFRow headRow = sheet.createRow(0);
            headRow.createCell(0).setCellValue("会员卡号");
            headRow.createCell(1).setCellValue("客户名称");
            headRow.createCell(2).setCellValue("手机号码");
            headRow.createCell(3).setCellValue("性别");
            headRow.createCell(4).setCellValue("会员等级");
            headRow.createCell(5).setCellValue("当前可用积分");
            headRow.createCell(6).setCellValue("审核状态");
            headRow.createCell(7).setCellValue("使用状态");
            headRow.createCell(8).setCellValue("活跃状态");
            headRow.createCell(9).setCellValue("新老会员");
            headRow.createCell(10).setCellValue("末次消费门店");
            headRow.createCell(11).setCellValue("归属门店");
            headRow.createCell(12).setCellValue("创建日期");

            IndividCust dt;
            SXSSFRow row;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < rsList.size(); i++) {
                dt = rsList.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(dt.getCardNo());
                row.createCell(1).setCellValue(dt.getName());
                row.createCell(2).setCellValue(dt.getMobile().toString());
                row.createCell(3).setCellValue(DataDict.getDictName(DataDict.FRANCHISEE_SEX, dt.getGender()));
                row.createCell(4).setCellValue(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
                row.createCell(6).setCellValue(DataDict.getDictName(DataDict.CUST_AUDIT_STATUS, dt.getStatus().getIndex()));
                row.createCell(7).setCellValue(DataDict.getDictName(DataDict.INDIVIDCUST_ENABLE, dt.getEnable()));
                row.createCell(8).setCellValue(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getActive()));
                row.createCell(9).setCellValue(DataDict.getDictName(DataDict.INDIVIDCUST_FRESH, dt.getFresh()));
                row.createCell(10).setCellValue(dt.getLastStoreName());
                row.createCell(11).setCellValue(dt.getBelongStoreName());

                if (dt.getCredit() != null) {
                    row.createCell(5).setCellValue(dt.getCredit());
                }

                if (dt.getCdate() != null) {
                    row.createCell(12).setCellValue(simpleDateFormat.format(dt.getCdate().getTime()));
                }
            }
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 冻结/解冻
    public void chgEnable() {
        model = individCustLogic.findById(id);
        Integer enable = (model.getEnable() == null ? 1 : model.getEnable());

        StringBuffer remark = new StringBuffer();
        remark.append("使用状态：" + (enable == 1 ? "启用" : "冻结") + "-->" + (enable == 1 ? "冻结" : "启用"));
        if (enable == GlobalConst.CUST_ENABLE_1 && model.getCreditStatus() == GlobalConst.CREDIT_STATUS_1) {
            remark.append("<br/>积分状态：正常-->冻结");
            model.setCreditStatus(GlobalConst.CREDIT_STATUS_2);
        }
        model.setEnable(model.getEnable() == GlobalConst.CUST_ENABLE_1 ? GlobalConst.CUST_ENABLE_2 : GlobalConst.CUST_ENABLE_1);
        individCustLogic.merge(model);

        // 记录日志
        OperationLog operLog = new OperationLog();
        operLog.setIndividCustId(id);
        operLog.setRemark(remark.toString());
        operLog.setCuser(WebUtils.getUserName());
        operLog.setCdate(Calendar.getInstance());
        operLog.setType(GlobalConst.OPER_TYPE_2);
        operLogLogic.persist(operLog);

        // 同步信息-->nc
        individCustLogic.synIndividCust(GlobalConst.SYS_SRC_CRM, GlobalConst.SYS_SRC_NC, model);
        dealJson(true);
    }

    // 失效
    public void custInvalid() {
        model = individCustLogic.findById(id);
        int oldActive = model.getActive();

        model.setActive(GlobalConst.ACTIVE_INVALID);
        individCustLogic.merge(model);

        Calendar cl = Calendar.getInstance();

        OperationLog operLog = new OperationLog();
        operLog.setIndividCustId(id);
        operLog.setRemark("活跃状态：" + (model.getActive() == 1 ? "活跃" : model.getActive() == 2 ? "历史" : "沉睡") + "-->无效");
        operLog.setCuser(WebUtils.getUserName());
        operLog.setCdate(cl);
        operLog.setType(GlobalConst.OPER_TYPE_2);
        operLogLogic.persist(operLog);

        ActiveStatus as = new ActiveStatus();
        as.setIndividCust(model);
        as.setBeforeStatus(oldActive);
        as.setAfterStatus(GlobalConst.ACTIVE_INVALID);
        as.setReason("手动变更");
        as.setMuser(WebUtils.getUserName());
        as.setMdate(cl);
        activeStatusLogic.persist(as);

        dealJson(true);
    }

    /**
     * 批量客户的归属门店：全选按钮优先于单选按钮（全选时按查询条件筛选）
     * 
     * @author：yuhb
     * @date：2017年3月15日 上午3:06:29
     */
    public void updateBelongStore() {
        String isAllSelected = request.getParameter("isAllSelected");
        String belongStoreNo = request.getParameter("belongStoreNo");
        String belongStoreName = request.getParameter("belongStoreName");
        String startPage = request.getParameter("startPage");
        String endPage = request.getParameter("endPage");
        Map searchParam = WebUtils.getParametersStartingWith(request);

        individCustLogic.updateBelongStore(isAllSelected, ids, searchParam, belongStoreNo, belongStoreName, startPage, endPage);
        dealJson(true);
    }

    // ///////////////////////////////////////////////////细单//////////////////////////////////////////////////////////

    // 家庭成员
    public void getRel() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        renderJson(individCustFamilyLogic.findAll(searchParam));
    }

    // 重要纪念日
    public void getRel2() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        renderJson(anniversaryLogic.findAll(searchParam));
    }

    // 价值贡献
    public void getValue4() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        renderJson(individCustLogic.getValContrib(searchParam));
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // pos单
    public void getRel4() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.put("EQ_custType", GlobalConst.CUST_TYPE_CUST);
        List<PosOrder> rsList = posOrderLogic.findAll(searchParam);

        List<GradeRule> ruleList = gradeRuleLogic.findAll();
        for (GradeRule rule : ruleList) {
            rule.setLvName(DataDict.getDictName(DataDict.LV_TYPE, rule.getLv()));
        }
        for (PosOrder dt : rsList) {
            dt.setStockTypeName(DataDict.getDictName(DataDict.STOCK_TYPE, dt.getStockType()));
            dt.setIntegralSrcName(DataDict.getDictName(DataDict.SYS_SCR, dt.getIntegralSrc()));
            dt.setScoreSegmentName(DataDict.getDictName(DataDict.SCORE_SEGMENT, dt.getScoreSegment()));
            dt.setBillTypeName(DataDict.getDictName(DataDict.POS_BILLTYPE, dt.getBillType()));
            // 等级规则
            if (StringUtils.isNotBlank(dt.getLvName())) {
                for (GradeRule rule : ruleList) {
                    if (rule.getLvName().equals(dt.getLvName())) {
                        dt.setExchangeChance(rule.getExchangeChance());
                        dt.setRestructureChance(rule.getRestructureChance());
                        break;
                    }
                }
            }
        }
        renderJson(rsList);
    }

    // 状态变化
    public void getRel5() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        List<ActiveStatus> rsList = activeStatusLogic.findAll(searchParam);
        for (ActiveStatus dt : rsList) {
            dt.setBeforeStatusName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getBeforeStatus()));
            dt.setAfterStatusName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getAfterStatus()));
        }
        renderJson(rsList);
    }

    // 售后服务
    public void getRel6() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        renderJson(afterServLogic.findAll(searchParam));
    }

    // 核定记录、操作日志
    public void getRel7() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        renderJson(operLogLogic.findAll(searchParam));
    }

    // 积分变化
    public void getRel9() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        List<IntegralAdjHis> rsList = integralAdjHisLogic.findAll(searchParam);

        for (IntegralAdjHis dt : rsList) {
            dt.setCreditOriginName(DataDict.getDictName(DataDict.SYS_SCR, dt.getCreditOrigin()));
            dt.setCreditStatusName(DataDict.getDictName(DataDict.INTEGRALADJHIS_CREDITSTATUS, dt.getCreditStatus()));
            dt.setModTypeName(DataDict.getDictName(DataDict.INTEGRALADJHIS_MODTYPE, dt.getModType()));
        }
        renderJson(rsList);
    }

    // 等级变化
    public void getRel10() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        List<GradeAdjHis> rsList = gradeAdjHisLogic.findAll(searchParam);

        for (GradeAdjHis dt : rsList) {
            dt.setLvAfterName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLvAfter()));
            dt.setLvBeforeName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLvBefore()));
            dt.setModTypeName(DataDict.getDictName(DataDict.INTEGRALADJHIS_MODTYPE, dt.getModType()));
        }
        renderJson(rsList);
    }

    // 短信维系
    public void getRel11() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        IndividCust ic = individCustLogic.find(searchParam);

        Map params = new HashMap();
        params.put("EQ_mobile", ic.getMobile());
        List<SmsLog> rsList = smsLogLogic.findAll(params);
        for (SmsLog dt : rsList) {
            dt.setTypeName(DataDict.getDictName(DataDict.SMS_TYPE, dt.getType()));
        }
        renderJson(rsList);
    }

    // 参与活动
    public void getRel16() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        IndividCust cust = individCustLogic.find(searchParam);
        renderJson(individCustLogic.qryActByIndividCustId(cust.getIndividCustId()));
    }

    // 权益信息
    public void getRightMain() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        List<RightMaint> rsList = rightMaintlogic.findAll(searchParam);

        for (RightMaint dt : rsList) {
            dt.setHandleStateName(DataDict.getDictName(DataDict.RIGHTMAINT_HANDLESTATE, dt.getHandleState()));
            dt.setComplaintTypeName(DataDict.getDictName(DataDict.RIGHTMAINT_COMPLAINTTYPE, dt.getComplaintType()));
            dt.setUrgencyLevelName(DataDict.getDictName(DataDict.RIGHTMAINT_URGENCYLEVEL, dt.getUrgencyLevel()));
            dt.setComplaintLevelName(DataDict.getDictName(DataDict.RIGHTMAINT_COMPLAINTLEVEL, dt.getComplaintLevel()));
            dt.setReviewStateName(DataDict.getDictName(DataDict.RIGHTMAINT_REVIEWSTATE, dt.getReviewState()));
            dt.setProbTypeName(DataDict.getDictName(DataDict.RIGHTMAINT_PROBTYPE, dt.getProbType()));
            dt.setOrgStoreName(dt.getStore() != null ? dt.getStore().getName() : dt.getOrg().getName());
        }
        String[] excludes = { "store.franchisee", "store.org", "posOrder.individCust" };
        renderJson(rsList, excludes);
    }

    // 咨询记录
    public void getBusiRegist() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        List<BusiRegist> rsList = busiregistlogic.findAll(searchParam);
        for (BusiRegist dt : rsList) {
            dt.setAcceptStateName(DataDict.getDictName(DataDict.BUSIREGIST_ACCEPTSTATE, dt.getAcceptState()));
            dt.setBusinessTypeName(DataDict.getDictName(DataDict.BUSIREGIST_BUSINESSTYPE, dt.getBusinessType()));
            dt.setOrgStoreName(dt.getStore() != null ? dt.getStore().getName() : dt.getOrg().getName());
        }
        String[] excludes = { "store.franchisee", "store.org" };
        renderJson(rsList, excludes);
    }

    // 推荐明细
    public void qryRefInfo() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        IndividCust dt = individCustLogic.find(searchParam);
        renderJson(individCustLogic.qryRefInfo(dt.getIndividCustId()));
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 发送验证码
    public void sendMsg() {
        IndividCust dt = individCustLogic.findById(id);

        if (dt != null && dt.getMobile() != null) {
            int x = new Random().nextInt(899999) + 100000;
            Integer retSms = yunpianSms.singleSends(null, GlobalConst.SMS_TYPE_13, dt.getMobile(), "您的验证码是" + x + "。如非本人操作，请忽略本短信");
            if (retSms == GlobalConst.SUCCESS) {
                session.setAttribute(dt.getMobile().toString(), x);
                dealJson(true, "验证码发送成功！");
            } else {
                dealJson(false, "验证码发送失败！");
            }
        } else {
            dealJson(false, "该手机号码不存在！");
        }
    }

    // 验证码-校验
    public void checkCode() {
        String code = request.getParameter("code");
        String mobile = request.getParameter("mobile");
        String sessionCode = session.getAttribute(mobile).toString();

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("EQ_mobile", mobile);
        IndividCust dt = individCustLogic.find(searchMap);
        if (dt == null) {
            dealJson(false, "该手机号码不存在！");
        } else {
            String msg = null;
            if (code.equals(sessionCode)) {
                dt.setStatus(CustStatus.PASS);
                dealJson(true, "验证通过！");
                msg = "验证码审核通过";
            } else {
                dt.setStatus(CustStatus.NOPASS);
                dealJson(false, "验证码错误！");
                msg = "验证码驳回";
            }
            individCustLogic.merge(dt);
            // 核定记录
            OperationLog operLog = new OperationLog();
            operLog.setIndividCustId(dt.getIndividCustId());
            operLog.setResult(msg);
            operLog.setCuser(WebUtils.getUserName());
            operLog.setCdate(Calendar.getInstance());
            operLog.setType(GlobalConst.OPER_TYPE_1);
            operLogLogic.persist(operLog);

            session.removeAttribute(mobile);// 验证通过后清除该验证码信息（减少session信息的资源占用）
        }
    }

    public void getEvents() {
        String s_authorities = "|" + WebUtils.getAuthorities() + "|";
        String[] events4Authorities = { "D_CONFIRM", "D_PASS", "D_NOPASS" };
        StringBuffer buf = new StringBuffer();
        for (String auth : events4Authorities) {
            if (s_authorities.indexOf("|" + auth + "|") > -1) {
                if (buf.length() > 0) {
                    buf.append(",");
                }
                buf.append("'" + auth + "'");
            }
        }
        String eventAuth = buf.toString();
        buf = new StringBuffer("[");

        List<String> events = new ArrayList<String>();
        IndividCust dt = individCustLogic.findById(id);
        switch (dt.getStatus()) {
        case NEW:
            events.add(CustStatus.CONFIRM.name());
            break;
        case CONFIRM:
            events.add(CustStatus.PASS.name());
            events.add(CustStatus.NOPASS.name());
            break;
        default:
            break;
        }
        for (String event : events) {
            if (event != null) {
                if (eventAuth.indexOf("'D_" + event.toUpperCase() + "'") > -1) {
                    buf.append("'" + event + "',");
                }
            }
        }
        if (buf.length() > 1) {
            buf.append("'@'");
        }
        buf.append("]");
        renderJson(buf.toString());

        dealJson(true);
    }

    /**
     * 处理流程状态
     */
    public void dealStatus() {
        model = individCustLogic.findById(id);
        CustStatus oldStatus = model.getStatus();

        switch (CustStatus.valueOf(billState)) {
        case NEW:
            break;
        case CONFIRM:
            if (oldStatus.equals(CustStatus.NEW)) {
                model.setStatus(CustStatus.CONFIRM);
            }
            break;
        case PASS:
            if (oldStatus.equals(CustStatus.CONFIRM)) {
                model.setStatus(CustStatus.PASS);
            }
            break;
        case NOPASS:
            if (oldStatus.equals(CustStatus.CONFIRM)) {
                model.setStatus(CustStatus.NOPASS);
            }
            break;
        default:
            break;
        }
        model = individCustLogic.merge(model);

        // 核定记录
        OperationLog operLog = new OperationLog();
        operLog.setIndividCustId(model.getIndividCustId());
        operLog.setResult(model.getStatus().getStateDesc());
        operLog.setRemark(message);
        operLog.setCuser(WebUtils.getUserName());
        operLog.setCdate(Calendar.getInstance());
        operLog.setType(GlobalConst.OPER_TYPE_1);
        operLogLogic.persist(operLog);

        dealJson(true);
    }

    // /////////////////////////////////////////////////////头像//////////////////////////////////////////////////////////

    public void uploadImage() {
        String individcust_img_dir = NeuUtils.getProperty("individcust_img_dir");
        String imageFullPath = individcust_img_dir + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."), fileName.length());

        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new FileOutputStream(imageFullPath);

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("success", true);
        rsMap.put("imageFullPath", imageFullPath);
        renderHtml(DataUtils.toJson(rsMap));
    }

    public void showImage() {
        String custId = request.getParameter("custId");

        FileInputStream in = null;
        OutputStream out = null;
        try {
            if (StringUtils.isNotBlank(custId)) {
                Map searchParam = new HashMap();
                searchParam.put("EQ_individCustId", custId);
                IndividCustImg img = imgLogic.find(searchParam);
                if (img != null) {
                    out = response.getOutputStream();
                    out.write(img.getImage());
                }
            } else {
                in = new FileInputStream(request.getParameter("imageFullPath"));
                out = response.getOutputStream();

                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = in.read(bytes)) > 0) {
                    out.write(bytes, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 根据登录人查询归属的组织机构：客服部（优先）、门店
    public void getOneOrgByUserId() {
        List<Map> rsList = user2orgLogic.qryOrgByUserId(WebUtils.getUserId());
        if (CollectionUtils.isEmpty(rsList)) {
            dealJson(false);
        } else {
            Map retMap = null;
            for (Map map : rsList) {
                if (GlobalConst.DEP_ORGCODE_KF.equals(map.get("orgcode"))) {
                    retMap = map;
                    break;
                }
            }
            if (retMap == null) {
                retMap = rsList.get(0);
            }
            dealJson(true, retMap);
        }
    }

    // 根据登录人查询归属的组织机构
    public void getOrgByUserId() {
        List<Map> rsList = user2orgLogic.qryOrgByUserId(WebUtils.getUserId());
        dealJson(true, rsList);
    }

    /**
     * 修改NC历史卡号
     * 
     * @author：WangYuanJun
     * @date：2017年4月17日 下午2:01:17
     */
    public void editNcNoHis() {
        IndividCust dt = individCustLogic.findById(model.getIndividCustId());
        dt.setNcNo(model.getNcNo());
        dt.setNcNoInvalid(model.getNcNoInvalid());
        individCustLogic.merge(dt);
        dealJson(true);
    }
}