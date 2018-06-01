package com.huiju.archive.individcust.logic;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.huiju.actment.activity.eao.ActivityEaoLocal;
import com.huiju.actment.activity.entity.Activity;
import com.huiju.archive.groupcust.eao.GroupCustEaoLocal;
import com.huiju.archive.groupcust.entity.GroupCust;
import com.huiju.archive.individcust.eao.AnniversaryEaoLocal;
import com.huiju.archive.individcust.eao.IndividCustEaoLocal;
import com.huiju.archive.individcust.eao.OperationLogEaoLocal;
import com.huiju.archive.individcust.entity.Anniversary;
import com.huiju.archive.individcust.entity.CustStatus;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.entity.OperationLog;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.eao.SqlEaoLocal;
import com.huiju.inter.httpclient.HttpClientRemote;
import com.huiju.inter.interLog.eao.InterLogEaoLocal;
import com.huiju.inter.posorder.eao.PosOrderEaoLocal;
import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.module.data.Page;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.excel.ExcelUtil;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.util.StringUtils;
import com.huiju.utils.NeuUtils;

@Stateless(mappedName = "IndividCustBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IndividCustBean extends GenericLogicImpl<IndividCust, Long> implements IndividCustRemote {
    @EJB
    private IndividCustEaoLocal individCustEao;
    @EJB
    private AnniversaryEaoLocal anniversaryEao;
    @EJB
    private GroupCustEaoLocal groupCustEao;
    @EJB
    private PosOrderEaoLocal posOrderEao;
    @EJB
    private ActivityEaoLocal activityEao;
    @EJB
    private OperationLogEaoLocal operLogEao;
    @EJB
    private SqlEaoLocal sqlEao;
    @EJB
    private InterLogEaoLocal interLogEao;
    @EJB
    private HttpClientRemote httpLogic;

    @Override
    protected GenericEao<IndividCust, Long> getGenericEao() {
        return individCustEao;
    }

    @Override
    public Page qryIndividCust(Page<IndividCust> page, Map<String, Object> searchParam) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select (select f.name from d_area f where f.areacode = t.province) ||");
        jpql.append("       (select f.name from d_area f where f.areacode = t.city) ||    ");
        jpql.append("       (select f.name from d_area f where f.areacode = t.county),    ");
        jpql.append("       (select g.name                                                ");
        jpql.append("          from d_cn_store f, d_data_dict g                           ");
        jpql.append("         where f.attr = g.value                                      ");
        jpql.append("           and g.dictcode = 8400                                     ");
        jpql.append("           and f.storeno = t.belongstoreno)                          ");
        jpql.append("  from d_archive_individcust t                                       ");
        jpql.append(" where t.individcustid = ?1                                          ");

        page = this.findAll(page, searchParam);
        Object[] objArr;
        for (IndividCust dt : page) {
            dt.setCreditStatusName(DataDict.getDictName(DataDict.INTEGRALADJHIS_CREDITSTATUS, dt.getCreditStatus()));
            dt.setLvName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
            dt.setTypeName(DataDict.getDictName(DataDict.INDIVIDCUST_TYPE, dt.getType()));
            dt.setGenderName(DataDict.getDictName(DataDict.FRANCHISEE_SEX, dt.getGender()));
            dt.setEnableName(DataDict.getDictName(DataDict.INDIVIDCUST_ENABLE, dt.getEnable()));
            dt.setActiveName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getActive()));
            dt.setFreshName(DataDict.getDictName(DataDict.INDIVIDCUST_FRESH, dt.getFresh()));

            objArr = individCustEao.executeSQLQueryOne(jpql.toString(), dt.getIndividCustId());
            dt.setFullAddress(objArr[0] != null ? objArr[0].toString() : null);
            dt.setBelongAttrName(objArr[1] != null ? objArr[1].toString() : null);
        }
        return page;
    }

    @Override
    public Map<String, Object> excel(File file, String userName) {
        String msg = "";
        boolean falg = false;
        try {
            List<IndividCust> dataList = new ArrayList<IndividCust>();
            Sheet sheet = ExcelUtil.getFirstSheet(file);
            if (sheet.getLastRowNum() > 1001) {
                msg = "excel导入上限为1000";
            } else {
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        IndividCust dt = new IndividCust();
                        try {
                            dt.setName(NeuUtils.cellFormatString(row.getCell(0)));
                            dt.setMobile(NeuUtils.cellFormatLong(row.getCell(1)));
                            dt.setBirthday(NeuUtils.parseCalendar(NeuUtils.cellFormatString(row.getCell(2))));
                            dt.setBirthMonthday(NeuUtils.parseStringFromCalendar(dt.getBirthday(), "MM-dd"));
                            dt.setAge(this.getIndividCustAge(dt.getBirthday()));
                            dt.setWeddingDay(NeuUtils.parseCalendar(NeuUtils.cellFormatString(row.getCell(3))));
                            dt.setIsSendSms(NeuUtils.cellFormatInteger(row.getCell(4)));
                            dt.setWechat(NeuUtils.cellFormatString(row.getCell(5)));
                            dt.setWechatId(NeuUtils.cellFormatString(row.getCell(6)));
                            dt.setIdcard(NeuUtils.cellFormatString(row.getCell(7)));
                            dt.setGender(null == NeuUtils.cellFormatInteger(row.getCell(8)) ? 2 : NeuUtils.cellFormatInteger(row.getCell(8)));
                            dt.setEmail(NeuUtils.cellFormatString(row.getCell(9)));
                            dt.setPhone(NeuUtils.cellFormatString(row.getCell(10)));
                            dt.setNcNo(NeuUtils.cellFormatString(row.getCell(11)));
                            dt.setGroupCustId(NeuUtils.cellFormatLong(row.getCell(12)));
                            dt.setCountry(NeuUtils.cellFormatInteger(row.getCell(13)));
                            dt.setProvince(NeuUtils.cellFormatInteger(row.getCell(14)));
                            dt.setCity(NeuUtils.cellFormatInteger(row.getCell(15)));
                            dt.setCounty(NeuUtils.cellFormatInteger(row.getCell(16)));
                            dt.setTown(NeuUtils.cellFormatString(row.getCell(17)));
                            dt.setAddress(NeuUtils.cellFormatString(row.getCell(18)));
                            dt.setJob(NeuUtils.cellFormatInteger(row.getCell(19)));
                            dt.setIncome(NeuUtils.cellFormatInteger(row.getCell(20)));
                            dt.setBrandChannel(NeuUtils.cellFormatInteger(row.getCell(21)));
                            dt.setMotives(NeuUtils.cellFormatInteger(row.getCell(22)));
                            dt.setPurchaseFactors(NeuUtils.cellFormatInteger(row.getCell(23)));
                            dt.setBelief(NeuUtils.cellFormatInteger(row.getCell(24)));
                            dt.setAge(NeuUtils.cellFormatInteger(row.getCell(25)));
                            dt.setClub(NeuUtils.cellFormatString(row.getCell(26)));
                            dt.setSubject(NeuUtils.cellFormatString(row.getCell(27)));
                            dt.setCosmeticsBrand(NeuUtils.cellFormatString(row.getCell(28)));
                            dt.setLikeFood(NeuUtils.cellFormatString(row.getCell(29)));
                            dt.setConsumerPlace(NeuUtils.cellFormatString(row.getCell(30)));
                            dt.setHobby(NeuUtils.cellFormatString(row.getCell(31)));
                            dt.setLikeBook(NeuUtils.cellFormatString(row.getCell(32)));
                            dt.setResort(NeuUtils.cellFormatString(row.getCell(33)));
                            dt.setLikeSport(NeuUtils.cellFormatString(row.getCell(34)));
                            dt.setRemark(NeuUtils.cellFormatString(row.getCell(35)));
                            dt.setSources(NeuUtils.cellFormatInteger(row.getCell(36)));
                            dt.setPlanId(NeuUtils.cellFormatLong(row.getCell(37)));
                            dt.setUnionActivitiesId(NeuUtils.cellFormatLong(row.getCell(38)));
                            dt.setReferrer(NeuUtils.cellFormatString(row.getCell(39)));
                            dt.setSrcStoreNo(NeuUtils.cellFormatString(row.getCell(40)));
                            dt.setSrcStoreName(NeuUtils.cellFormatString(row.getCell(41)));
                            dt.setOtherSources(NeuUtils.cellFormatString(row.getCell(42)));
                            dt.setRelationship(NeuUtils.cellFormatInteger(row.getCell(43)));

                            // 默认归属“客服部”
                            if (dt.getSrcStoreNo() == null) {
                                dt.setBelongStoreNo(GlobalConst.DEP_ORGCODE_KF);
                                dt.setBelongStoreName(GlobalConst.DEP_ORGName_KF);
                            } else {
                                dt.setBelongStoreNo(dt.getSrcStoreNo());
                                dt.setBelongStoreName(dt.getSrcStoreName());
                            }

                            dataList.add(dt);
                        } catch (Exception e) {
                            msg += "," + i;
                            e.printStackTrace();
                        }
                    }
                }

                if (StringUtils.isNotBlank(msg)) {
                    msg = "数据异常的行号：" + msg.substring(1);
                } else if (CollectionUtils.isEmpty(dataList)) {
                    msg = "excel文件为空";
                } else {
                    List<Long> mobileList = new ArrayList<Long>();
                    List<Long> illegMobile = new ArrayList<Long>();

                    List<Long> MobileAll = new ArrayList<Long>(); // 所有手机号码
                    List<Long> repeatmobile = new ArrayList<Long>(); // 重复手机号码

                    for (IndividCust dt : dataList) {
                        if (!CollectionUtils.isEmpty(MobileAll) && MobileAll.contains(dt.getMobile())) {
                            repeatmobile.add(dt.getMobile());
                        }
                        MobileAll.add(dt.getMobile());
                        if (dt.getMobile() < 10000000000L || dt.getMobile() > 99999999999L) {
                            illegMobile.add(dt.getMobile());
                        }
                        mobileList.add(dt.getMobile());
                    }

                    if (!CollectionUtils.isEmpty(repeatmobile)) {
                        Set<Long> set = new TreeSet<Long>();
                        set.addAll(repeatmobile);
                        msg = "手机号码在excel中重复：" + StringUtils.join(set.toArray(), ",");

                    } else if (!CollectionUtils.isEmpty(illegMobile)) {
                        msg = "手机号码非11位数字：" + StringUtils.join(illegMobile.toArray(), ",");
                    } else {
                        String jpql = "select distinct t.mobile from d_archive_individcust t where  t.mobile in (" + StringUtils.join(mobileList.toArray(), ",") + ")";
                        List<String> repeatMobile = individCustEao.executeSQLQuery(jpql);
                        if (!CollectionUtils.isEmpty(repeatMobile)) {
                            msg = "手机号码已存在：" + StringUtils.join(repeatMobile.toArray(), ",");
                        } else {
                            // 保存解析结果
                            for (IndividCust dt : dataList) {
                                dt.setType(GlobalConst.CUST_TYPE_CUST);
                                dt.setCardNo(sqlEao.getCnNum(GlobalConst.NUM_INDIVID));
                                dt.setStatus(CustStatus.NEW);
                                dt.setActive(GlobalConst.ACTIVE_POSTIVE);
                                dt.setIsSendSms(GlobalConst.NO);
                                dt.setEnable(1);
                                dt.setCdate(Calendar.getInstance());
                                dt.setFresh(GlobalConst.FRESH_0);
                                dt.setIsImport(GlobalConst.YES);
                                dt.setLv(GlobalConst.CUST_LV_FANS);
                                dt.setCredit(GlobalConst.D_ZERO);
                                dt.setCreditStatus(GlobalConst.I_ONE);
                                dt.setConvertedCredits(GlobalConst.D_ZERO);
                                dt.setJewerlyAmount(GlobalConst.D_ZERO);
                                dt = this.persist(dt);

                                // 核定记录
                                OperationLog operLog = new OperationLog();
                                operLog.setIndividCustId(dt.getIndividCustId());
                                operLog.setResult("导入");
                                operLog.setCuser(userName);
                                operLog.setCdate(Calendar.getInstance());
                                operLog.setType(GlobalConst.OPER_TYPE_1);
                                operLogEao.persist(operLog);

                                this.synIndividCustToEx(dt);
                            }
                            falg = true;
                        }
                    }

                }
            }

        } catch (Exception e) {
            msg = "excel数据错误，请检查！";
            e.printStackTrace();
        }
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("msg", msg);
        rsMap.put("success", falg);
        return rsMap;
    }

    /**
     * CRM发起同步个人档案信息到外系统
     */
    public String synIndividCustToEx(IndividCust dt) {
        String msg = "";
        Object flagObj = null;
        Map rsMap = this.synIndividCust(GlobalConst.SYS_SRC_CRM, GlobalConst.SYS_SRC_NC, dt);
        if (rsMap != null) {
            flagObj = rsMap.get("flag");
            if (flagObj != null && Integer.parseInt(flagObj.toString()) == GlobalConst.FAIL) {
                msg += "NC接口调用失败，";
            }
        } else {
            msg += "NC接口调用返回空字符串，";
        }
        rsMap = this.synIndividCust(GlobalConst.SYS_SRC_CRM, GlobalConst.SYS_SRC_YW, dt);
        if (rsMap != null) {
            flagObj = rsMap.get("flag");
            if (flagObj != null && Integer.parseInt(flagObj.toString()) == GlobalConst.FAIL) {
                msg += "耀我网接口调用失败，";
            }
        } else {
            msg += "耀我网接口调用返回空字符串，";
        }
        rsMap = this.synIndividCust(GlobalConst.SYS_SRC_CRM, GlobalConst.SYS_SRC_WECHAR, dt);
        if (rsMap != null) {
            flagObj = rsMap.get("flag");
            if (flagObj != null && Integer.parseInt(flagObj.toString()) == GlobalConst.FAIL) {
                msg += "微信接口调用失败，";
            }
        } else {
            msg += "微信接口调用返回空字符串，";
        }
        return msg;
    }

    public Map synIndividCust(int from, int to, IndividCust newDt) {
        return this.synIndividCust(from, to, -1, null, null, newDt);
    }

    @Override
    public Map synIndividCust(int from, int to, IndividCust oldDt, IndividCust newDt) {
        return this.synIndividCust(from, to, 1, DataDict.getDictName(DataDict.SYS_SCR, from), oldDt, newDt);
    }

    @Override
    public Map synIndividCust(int from, int to, int flag, String userName, IndividCust oldDt, IndividCust dt) {
        Map rsMap = null;

        // 操作日志
        if (flag == 0) {
            OperationLog operLog = new OperationLog();
            operLog.setIndividCustId(dt.getIndividCustId());
            operLog.setResult("新建");
            operLog.setCuser(userName);
            operLog.setCdate(Calendar.getInstance());
            operLog.setType(GlobalConst.OPER_TYPE_1);
            operLogEao.persist(operLog);
        } else if (flag == 1) {
            this.addCustOperationLog(userName, oldDt, dt);
        }

        Map map = new HashMap();
        map.put("custType", GlobalConst.CUST_TYPE_CUST);
        map.put("cardNo", dt.getCardNo());
        map.put("name", dt.getName());
        map.put("mobile", dt.getMobile());
        map.put("creditStatus", dt.getCreditStatus());
        map.put("credit", dt.getCredit());
        map.put("convertedCredits", dt.getConvertedCredits());
        map.put("lv", dt.getLv());

        if (to == GlobalConst.SYS_SRC_NC) {
            map.put("action", "ncmember");
            map.put("ncNo", dt.getNcNo());
            map.put("province", dt.getProvince());
            map.put("city", dt.getCity());
            map.put("county", dt.getCounty());
            map.put("address", dt.getAddress());
            map.put("gender", dt.getGender());
            map.put("psnId", dt.getIdcard());
            map.put("belongStoreNo", dt.getBelongStoreNo());
            map.put("belongStoreName", dt.getBelongStoreName());
            map.put("wechatno", dt.getWechat());
            map.put("enable", dt.getEnable() != null ? dt.getEnable() : GlobalConst.CUST_ENABLE_1);
            map.put("birthday", NeuUtils.parseStringFromCalendar(dt.getBirthday()));// 生日
            map.put("weddingDay", NeuUtils.parseStringFromCalendar(dt.getWeddingDay()));// 结婚纪念日

            List<Anniversary> annList = anniversaryEao.executeQuery("select s from Anniversary s where s.individCust.individCustId = ?1", dt.getIndividCustId());
            for (Anniversary temp : annList) {
                if (GlobalConst.DAY_SPOUSEBIRTH == temp.getName()) {
                    map.put("spouseBirthDay", NeuUtils.parseStringFromCalendar(temp.getDays()));// 配偶生日
                } else if (GlobalConst.DAY_OTHER == temp.getName()) {
                    map.put("otherDay", NeuUtils.parseStringFromCalendar(temp.getDays()));// 其它纪念日
                }
            }
            rsMap = httpLogic.post(NeuUtils.getProperty("crm2nc"), "IndividCustBean.nc", from, DataUtils.toJson(map));
            // 回填ncNo
            if (StringUtils.isBlank(dt.getNcNo())) {
                Object flagObj = rsMap.get("flag");
                Map rsdata = (Map) rsMap.get("rsdata");
                if (flagObj != null && Integer.parseInt(flagObj.toString()) == GlobalConst.SUCCESS && rsdata != null) {
                    Object ncNo_obj = rsdata.get("ncNo");
                    if (ncNo_obj != null) {
                        dt.setNcNo(ncNo_obj.toString());
                        individCustEao.merge(dt);
                    }
                }
            }
        } else if (to == GlobalConst.SYS_SRC_YW) {
            if (from == GlobalConst.SYS_SRC_WECHAR) {
                map.put("pwd", dt.getPwd());
            }
            rsMap = httpLogic.post(NeuUtils.getProperty("individcust_crm2yw"), "IndividCustBean.yw", from, DataUtils.toJson(map));
        } else if (to == GlobalConst.SYS_SRC_WECHAR) {
            if (from == GlobalConst.SYS_SRC_YW) {
                map.put("pwd", dt.getPwd());
            }
            map.put("cardNo", dt.getCardNo());
            map.put("name", dt.getName());
            map.put("mobile", dt.getMobile());
            map.put("birthday", dt.getBirthday());
            map.put("gender", dt.getGender());
            map.put("weddingDay", dt.getWeddingDay());
            map.put("email", dt.getEmail());
            map.put("job", dt.getJob());
            map.put("income", dt.getIncome());
            map.put("brandChannel", dt.getBrandChannel());
            map.put("motives", dt.getMotives());
            map.put("purchaseFactors", dt.getPurchaseFactors());
            map.put("belief", dt.getBelief());
            map.put("age", dt.getAge());
            map.put("wechatId", dt.getWechatId());
            map.put("wechat", dt.getWechat());

            rsMap = httpLogic.post(NeuUtils.getProperty("individcust_crm2wechar"), "IndividCustBean.wechar", from, DataUtils.toJson(map));
        }
        return rsMap;
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List qryActByIndividCustId(Long individCustId) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select a.activityNo,                            ");
        jpql.append("       (select t.name from d_data_dict t where t.dictcode = 6100 and t.value = a.activitytype),");
        jpql.append("       a.activityTheme,                         ");
        jpql.append("       a.beginTime,                             ");
        jpql.append("       a.endTime,                               ");
        jpql.append("       b.indiPartinId,                          ");
        jpql.append("       (select decode(count(1), 0, 0, 1)        ");
        jpql.append("          from d_pos_order c                    ");
        jpql.append("         where c.individcustid = b.individcustid");
        jpql.append("           and c.posbilldate between a.begintime and a.endtime + 1)");
        jpql.append("  from d_activity a, d_activity_indipartin b    ");
        jpql.append(" where a.activityid = b.activityid              ");
        jpql.append("   and b.individcustid = ?1                     ");
        jpql.append("   and a.begintime > sysdate - 720              ");// 查询两年内的数据
        jpql.append(" order by a.activityid desc                     ");

        List<Object[]> qryList = individCustEao.executeSQLQuery(jpql.toString(), new Object[] { individCustId });

        List rsList = new ArrayList();
        Map map;
        String updJpql = "update d_activity_indipartin t set t.ispartin = ?1 where t.indipartinid = ?2";
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("activityNo", rs[0]);
            map.put("activityTypeName", rs[1]);
            map.put("activityTheme", rs[2]);
            map.put("beginTime", rs[3]);
            map.put("endTime", rs[4]);
            map.put("isPartIn", ((BigDecimal) rs[6]).intValue());

            rsList.add(map);
            individCustEao.executeSQLUpdate(updJpql, new Object[] { rs[6], rs[5] });
        }
        return rsList;
    }

    @Override
    public void updateBelongStore(String isAllSelected, List<Long> individCustIds, Map searchParam, String belongStoreNo, String belongStoreName, String startPage, String endPage) {
        individCustEao.updateBelongStore(isAllSelected, individCustIds, searchParam, belongStoreNo, belongStoreName, startPage, endPage);
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 增加“客户信息”操作历史
     * 
     * @author：yuhb
     * @date：2017年3月22日 下午9:16:35
     */
    public void addCustOperationLog(String userName, IndividCust cust, IndividCust dt) {
        String custStrs = this.getUpdCustStr(cust);
        String dtStrs = this.getUpdCustStr(dt);

        if (!custStrs.equals(dtStrs)) {
            String logStr = "";

            String[] custArr = custStrs.replaceAll("null", "").split("#");
            String[] dtArr = dtStrs.replaceAll("null", "").split("#");
            String[] custChildArr;
            String[] dtChildArr;
            String custVal;
            String dtVal;
            for (String custStr : custArr) {
                custChildArr = custStr.split("=");
                custVal = (custChildArr.length == 2 ? custChildArr[1] : "");

                for (String dtStr : dtArr) {
                    dtChildArr = dtStr.split("=");
                    if (custChildArr[0].equals(dtChildArr[0])) {
                        dtVal = (dtChildArr.length == 2 ? dtChildArr[1] : "");
                        if (!custVal.equals(dtVal)) {
                            logStr += "<br/>" + custChildArr[0] + "：" + custVal + "-->" + dtVal;
                        }
                    }
                }
            }
            // 操作日志
            if (StringUtils.isNotBlank(logStr)) {
                OperationLog operLog = new OperationLog();
                operLog.setIndividCustId(dt.getIndividCustId());
                operLog.setRemark(logStr.substring(5));
                operLog.setCuser(userName);
                operLog.setCdate(Calendar.getInstance());
                operLog.setType(GlobalConst.OPER_TYPE_2);
                operLogEao.persist(operLog);
            }
        }
    }

    private String getUpdCustStr(IndividCust dt) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#客户名称=").append(dt.getName());
        buffer.append("#手机号码=").append(dt.getMobile());
        buffer.append("#生日=").append(NeuUtils.parseStringFromCalendar(dt.getBirthday()));
        buffer.append("#会员活跃状态=").append(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getActive()));
        buffer.append("#会员等级=").append(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
        buffer.append("#会员积分=").append(dt.getCredit());
        buffer.append("#已兑换积分=").append(dt.getConvertedCredits());
        buffer.append("#积分状态=").append(DataDict.getDictName(DataDict.INTEGRALADJHIS_CREDITSTATUS, dt.getCreditStatus()));
        buffer.append("#新老会员状态=").append(DataDict.getDictName(DataDict.INDIVIDCUST_FRESH, dt.getFresh()));
        buffer.append("#新老会员变更时间=").append(NeuUtils.parseStringFromCalendar(dt.getFreshChgTime()));
        buffer.append("#性别=").append(DataDict.getDictName(DataDict.FRANCHISEE_SEX, dt.getGender()));
        buffer.append("#固定电话=").append(dt.getPhone());
        buffer.append("#身份证号=").append(dt.getIdcard());
        // 所属团体
        String groupName = "";
        if (dt.getGroupCustId() != null) {
            GroupCust dt2 = groupCustEao.findById(dt.getGroupCustId());
            if (dt2 != null) {// 导入时可能信息不存在
                groupName = dt2.getGroupName();
            }
        }
        buffer.append("#所属团体=").append(groupName);
        buffer.append("#是否发送短信=").append(DataDict.getDictName(DataDict.YES_OR_NOT, dt.getIsSendSms()));
        buffer.append("#结婚纪念日=").append(dt.getWeddingDay());
        buffer.append("#电子邮箱=").append(dt.getEmail());
        buffer.append("#国家=").append(dt.getCountry());
        buffer.append("#省=").append(dt.getProvince());
        buffer.append("#市=").append(dt.getCity());
        buffer.append("#区/县=").append(dt.getCounty());
        buffer.append("#乡镇=").append(dt.getTown());
        buffer.append("#地址=").append(dt.getAddress());
        buffer.append("#收货地址=").append(dt.getShippingAddr());
        buffer.append("#职业=").append(DataDict.getDictName(DataDict.CUST_JOB, dt.getJob()));
        buffer.append("#收入=").append(DataDict.getDictName(DataDict.CUST_INCOME, dt.getIncome()));
        buffer.append("#品牌获知渠道=").append(DataDict.getDictName(DataDict.CUST_BRANK_CHANNEL, dt.getBrandChannel()));
        buffer.append("#购买目的=").append(DataDict.getDictName(DataDict.CUST_MOTIVES, dt.getMotives()));
        buffer.append("#喜欢千年的理由=").append(DataDict.getDictName(DataDict.CUST_FACTOR, dt.getPurchaseFactors()));
        buffer.append("#信仰=").append(DataDict.getDictName(DataDict.CUST_BELIEF, dt.getBelief()));
        buffer.append("#年龄段=").append(DataDict.getDictName(DataDict.CUST_AGE, dt.getAge()));
        buffer.append("#微信号=").append(dt.getWechat());
        buffer.append("#微信ID=").append(dt.getWechatId());
        buffer.append("#所属私人俱乐部=").append(dt.getClub());
        buffer.append("#对什么主题有兴趣=").append(dt.getSubject());
        buffer.append("#喜欢的化妆品品牌=").append(dt.getCosmeticsBrand());
        buffer.append("#喜欢的菜式=").append(dt.getLikeFood());
        buffer.append("#喜欢的消费场所=").append(dt.getConsumerPlace());
        buffer.append("#嗜好与娱乐=").append(dt.getHobby());
        buffer.append("#喜欢读什么书=").append(dt.getLikeBook());
        buffer.append("#喜欢的度假方式=").append(dt.getResort());
        buffer.append("#喜欢观赏的运动=").append(dt.getLikeSport());
        buffer.append("#备注=").append(dt.getRemark());
        buffer.append("#来源渠道=").append(DataDict.getDictName(DataDict.CUST_SRC_CHANNEL, dt.getSources()));
        buffer.append("#来源门店=").append(dt.getSrcStoreName());
        // 活动方案
        String planName = "";
        if (dt.getPlanId() != null) {
            Activity dt2 = activityEao.findById(dt.getPlanId());
            if (dt2 != null) {// 导入时可能信息不存在
                planName = dt2.getActivityTheme();
            }
        }
        buffer.append("#活动方案=").append(planName);

        // 异业联盟活动
        String unionActivitiesName = "";
        if (dt.getUnionActivitiesId() != null) {
            Activity dt2 = activityEao.findById(dt.getUnionActivitiesId());
            if (dt2 != null) {// 导入时可能信息不存在
                unionActivitiesName = dt2.getActivityTheme();
            }
        }
        buffer.append("#异业联盟活动=").append(unionActivitiesName);
        buffer.append("#推荐人=").append(dt.getReferrer());
        buffer.append("#与推荐人的关系=").append(DataDict.getDictName(DataDict.CUST_REF_REL, dt.getRelationship()));
        buffer.append("#其他来源=").append(dt.getOtherSources());
        return buffer.toString();
    }

    @Override
    public Page<IndividCust> findAllExcludeBolb(Page<IndividCust> custPage, Map<String, Object> paramsMap) {
        return individCustEao.findAllExcludeBolb(custPage, paramsMap);
    }

    @Override
    public Page<IndividCust> findCustPage(Page<IndividCust> custPage, List<Map<String, Object>> paramsList) {
        return individCustEao.findCustPage(custPage, paramsList);
    }

    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List getValContrib(Map searchParam) {
        Map rsMap = new HashMap();
        IndividCust dt = this.find(searchParam);

        // 去重的pos单数量
        BigDecimal disPosCntObj = posOrderEao.executeSQLQueryOne("select count(distinct trunc(t.posbilldate)) from d_pos_order t where t.actualsaleamount >= 0 and t.individcustid = ?1", dt.getIndividCustId());
        int posNoCnt = disPosCntObj.intValue();

        DecimalFormat df = new DecimalFormat("#.##");
        double discountAmount = 0;
        double saleAmount = 0;

        // pos单列表
        List<PosOrder> posList = posOrderEao.executeQuery("select s from PosOrder s where s.individCust.individCustId = ?1", dt.getIndividCustId());
        if (!CollectionUtils.isEmpty(posList)) {
            int posCnt = 0;// pos单件数
            for (int i = posList.size() - 1; i >= 0; i--) {
                PosOrder pos = posList.get(i);
                if (pos.getActualSaleAmount() != null && pos.getActualSaleAmount() != 0) {
                    discountAmount += (pos.getJewelDiscountAmount() == null ? 0 : pos.getJewelDiscountAmount());
                    saleAmount += pos.getActualSaleAmount();
                }
                if (pos.getFlargess() == GlobalConst.NO) {
                    posCnt++;
                }
            }
            rsMap.put("avgprice", Double.parseDouble(df.format(discountAmount / posNoCnt)));// 折算额的平均客单价
            rsMap.put("avgsaleprice", Double.parseDouble(df.format(saleAmount / posNoCnt)));// 消费额平均客单价
            rsMap.put("posCnt", posCnt);
            rsMap.put("avgSinglePrice", posCnt == 0 ? 0 : Double.parseDouble(df.format(saleAmount / posCnt)));// 平均单件金额
            rsMap.put("firstFranchisee", this.getFranchiseeByStore(posList.get(0).getStoreNo()));// 首次消费门店所属加盟商
            rsMap.put("lastFranchisee", this.getFranchiseeByStore(posList.get(posList.size() - 1).getStoreNo()));// 末次消费门店所属加盟商
        }
        rsMap.put("posnum", posNoCnt);// 消费次数
        rsMap.put("sumactualSaleAmount", Double.parseDouble(df.format(saleAmount)));// 累计消费额
        rsMap.put("sumjewelDiscountAmount", Double.parseDouble(df.format(discountAmount)));// 累计折算额

        // 忠粉勋章
        StringBuffer jpql = new StringBuffer();
        jpql.append("select count(1)                               ");
        jpql.append("  from (select b.refcrmcardno,                ");
        jpql.append("               row_number() over(partition by b.individcustid order by b.posbilldate asc) rn");
        jpql.append("          from d_pos_order b                  ");
        jpql.append("         where exists (select 1               ");
        jpql.append("                  from d_pos_order a          ");
        jpql.append("                 where a.posid = b.posid      ");
        jpql.append("                   and a.refcrmcardno = ?1)) c");
        jpql.append(" where rn = 1                                 ");
        jpql.append("   and c.refcrmcardno = ?1                    ");
        BigDecimal loyaltyMedal = (BigDecimal) individCustEao.executeSQLQueryOne(jpql.toString(), dt.getCardNo());
        rsMap.put("loyaltyMedal", loyaltyMedal.intValue());

        rsMap.put("fristBuyTime", dt.getFristBuyTime());
        rsMap.put("fristStoreName", dt.getFristStoreName());
        rsMap.put("lastBuyTime", dt.getLastBuyTime());
        rsMap.put("lastStoreName", dt.getLastStoreName());
        rsMap.put("lvName", DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
        rsMap.put("activeName", DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getActive()));
        rsMap.put("credit", dt.getCredit());
        rsMap.put("convertedCredits", dt.getConvertedCredits());
        rsMap.put("creditStatusName", DataDict.getDictName(DataDict.INTEGRALADJHIS_CREDITSTATUS, dt.getCreditStatus()));
        rsMap.put("freshName", DataDict.getDictName(DataDict.INDIVIDCUST_FRESH, dt.getFresh()));
        rsMap.put("freshChgTime", dt.getFreshChgTime());

        List rsList = new ArrayList();
        rsList.add(rsMap);
        return rsList;
    }

    /**
     * 根据消费门店获取对应的加盟商名称
     */
    private String getFranchiseeByStore(String storeNo) {
        String jpql = "select t.franame from d_franchisee t, d_cn_store f where t.franchiseeid = f.franchiseeid and f.storeno = ?1";
        return individCustEao.executeSQLQueryOne(jpql, storeNo);
    }

    @Override
    public List qryRefInfo(Long individCustId) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select a.name,                                       ");
        jpql.append("       a.cardno,                                     ");
        jpql.append("       to_char(a.fristbuytime, 'yyyy-mm-dd hh24:mi:ss'),");
        jpql.append("       to_char(a.lastbuytime, 'yyyy-mm-dd hh24:mi:ss'), ");
        jpql.append("       (select sum(d.actualsaleamount) from d_pos_order d where d.individcustid = a.individcustid),");
        jpql.append("       a.jewerlyamount,                              ");
        jpql.append("       (select count(1) from d_pos_order d where d.individcustid = a.individcustid),");
        jpql.append("       (select count(distinct d.posno) from d_pos_order d where d.individcustid = a.individcustid) ");
        jpql.append("  from d_archive_individcust a                       ");
        jpql.append(" where exists (select 1                              ");
        jpql.append("          from d_pos_order b, d_archive_individcust c");
        jpql.append("         where b.individcustid = a.individcustid     ");
        jpql.append("           and b.refcrmcardno = c.cardno             ");
        jpql.append("           and c.individcustid = ?1)                 ");

        List<Object[]> qryList = individCustEao.executeSQLQuery(jpql.toString(), individCustId);
        List rsList = new ArrayList();
        for (Object[] rs : qryList) {
            Map map = new HashMap();
            map.put("name", rs[0]);
            map.put("cardNo", rs[1]);
            map.put("fristBuyTime", rs[2]);
            map.put("lastBuyTime", rs[3]);
            map.put("actualSaleAmount", ((BigDecimal) rs[4]).doubleValue());
            map.put("jewerlyAmount", ((BigDecimal) rs[5]).doubleValue());
            map.put("posCnt", ((BigDecimal) rs[6]).intValue());
            map.put("posNoCnt", ((BigDecimal) rs[7]).intValue());
            rsList.add(map);
        }
        return rsList;
    }

    @Override
    public int getIndividCustAge(Calendar birthday) {
        Integer age = 6;
        int birthYear = Integer.parseInt(NeuUtils.parseStringFromCalendar(birthday, "yyyy"));
        int curYear = Integer.parseInt(NeuUtils.parseStringFromCalendar(Calendar.getInstance(), "yyyy"));
        int interval = curYear - birthYear;
        if (interval <= 20) {
            age = 1;
        } else if (interval > 20 && interval <= 25) {
            age = 2;
        } else if (interval > 25 && interval <= 30) {
            age = 3;
        } else if (interval > 30 && interval <= 40) {
            age = 4;
        } else if (interval > 40 && interval <= 50) {
            age = 5;
        }
        return age;
    }

}