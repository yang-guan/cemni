package com.huiju.sms.sms.logic;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.common.GlobalConst;
import com.huiju.console.dict.entity.Dict;
import com.huiju.inter.interLog.eao.InterLogEaoLocal;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.inter.sms.YunpianSmsRemote;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.excel.ExcelUtil;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.util.StringUtils;
import com.huiju.sms.sms.eao.SmsEaoLocal;
import com.huiju.sms.sms.entity.Sms;
import com.huiju.sms.sms.entity.SmsParamVar;
import com.huiju.sms.sms.entity.TypeCond;
import com.huiju.utils.NeuUtils;

/**
 * 短信管理
 * 
 * @author：yuhb
 * @date：2017年2月21日 下午2:02:39
 */
@Stateless(mappedName = "SmsBean")
@SuppressWarnings({ "rawtypes" })
public class SmsBean extends GenericLogicImpl<Sms, Long> implements SmsRemote {
    @EJB
    private SmsEaoLocal smsEao;
    @EJB
    private InterLogEaoLocal interLogEao;

    @EJB(mappedName = "YunpianSms")
    private YunpianSmsRemote yunpianSms;

    @Override
    protected GenericEao<Sms, Long> getGenericEao() {
        return smsEao;
    }

    // 短信类型-下拉框：新增时只展示可以增加的短信类型
    @Override
    public List<Dict> selSmsTypeStore() {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select s                      ");
        jpql.append("  from Dict s                 ");
        jpql.append(" where s.dictCode = 8000      ");
        jpql.append("   and s.value not in (13, 14)");
        jpql.append("   and (s.value in (2, 3, 7, 10, 12) or not exists (select 1 from Sms b where b.type = s.value))");
        jpql.append("  order by s.orderNo          ");

        return smsEao.executeQuery(jpql.toString());
    }

    // 可变变量-下拉框
    @Override
    public List<SmsParamVar> selParamVar(Integer type) {
        return smsEao.executeQuery("select s from SmsParamVar s where s.type = ?1 order by s.orderNo", new Object[] { type });
    }

    // 对象条件-比较字段-下拉框
    @Override
    public List<TypeCond> selTypeCond(Integer type) {
        return smsEao.executeQuery("select s from TypeCond s where s.type = ?1", new Object[] { type });
    }

    // 发送对象
    @Override
    public List<IndividCust> qryObjCust(Long smsId) {
        return smsEao.qryObjCust(smsId);
    }

    @Override
    public Map uploadExcel(File file) throws Exception {
        List<IndividCust> dataList = new ArrayList<IndividCust>();
        String msg = "";

        try {
            Sheet sheet = ExcelUtil.getFirstSheet(file);
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    IndividCust dt = new IndividCust();
                    try {
                        dt.setCardNo(NeuUtils.cellFormatString(row.getCell(0)));
                        dt.setMobile(NeuUtils.cellFormatLong(row.getCell(1)));

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
                String jpql = "select s from IndividCust s where s.cardNo = ?1 or s.mobile = ?2";

                List<IndividCust> tempCustList;
                IndividCust tempCust;
                IndividCust dt;
                String cardNo;
                Long mobile;
                for (int i = 0; i < dataList.size(); i++) {
                    dt = dataList.get(i);
                    cardNo = dt.getCardNo();
                    mobile = dt.getMobile();
                    if (StringUtils.isEmpty(cardNo) && mobile == null) {
                        msg += "," + i;
                    } else {
                        tempCustList = smsEao.executeQuery(jpql, new Object[] { cardNo, mobile });
                        if (CollectionUtils.isEmpty(tempCustList)) {
                            msg += "," + i;
                        } else {
                            tempCust = tempCustList.get(0);
                            dt.setIndividCustId(tempCust.getIndividCustId());
                            dt.setCardNo(tempCust.getCardNo());
                            dt.setName(tempCust.getName());
                        }
                    }
                }
                msg = "".equals(msg) ? msg : "数据异常的行号：" + msg.substring(1);
            }
        } catch (Exception e) {
            msg = e.toString();
            e.printStackTrace();
        }
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("dataList", dataList);
        rsMap.put("msg", msg);
        rsMap.put("success", "".equals(msg) ? true : false);
        return rsMap;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void scanSms() {
        Calendar reqTime = Calendar.getInstance();
        Integer flag = GlobalConst.FAIL;
        String msg = null;

        try {
            this.scanSmsTpl();
            this.businessManBirth();

            flag = GlobalConst.SUCCESS;
            msg = GlobalConst.TIP_SUCCESS;
        } catch (Exception e) {
            msg = NeuUtils.getStackTraceStr(e);
        } finally {
            InterLog interLog = new InterLog();
            interLog.setCrmClassMethod("SmsBean.scanSms");
            interLog.setReqTime(reqTime);
            interLog.setReqContent("扫描短信模版发短信");
            interLog.setSrc(GlobalConst.SYS_SRC_SMS);
            interLog.setStatus(flag);
            interLog.setRespTime(Calendar.getInstance());
            interLog.setRespContent(msg);
            interLogEao.persist(interLog);
        }
    }

    /**
     * 扫描短信模版：1生日短信、2节日问候、3客情维护、7保养短信、营销短信
     */
    private void scanSmsTpl() {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select a.smsId, a.name, a.type, a.content, a.cycleType, a.activityid");
        jpql.append("  from d_sms a                                        ");
        jpql.append(" where a.isvalid = 1                                  ");
        jpql.append("   and a.sendtype = 2                                 ");
        jpql.append("   and a.type <> 11                                   ");// 过滤：客商生日
        jpql.append("   and (a.timedsend is null or to_char(a.timedsend,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd'))");
        jpql.append("union                                                 ");
        jpql.append("select a.smsId, a.name, a.type, a.content, a.cycleType, a.activityid");
        jpql.append("  from d_sms a                                        ");
        jpql.append(" where a.isvalid = 1                                  ");
        jpql.append("   and a.sendtype = 3                                 ");
        jpql.append("   and ((a.type not in (3, 7) and (a.cycletype = 8 or a.cycletype = to_char(sysdate, 'D')))");
        jpql.append("       or (a.type = 3 and trunc(sysdate) = decode(a.cycletype, 1, trunc(sysdate, 'mm'), 2, to_date(to_char(sysdate, 'yyyymm') || '15', 'yyyymmdd'), trunc(last_day(sysdate))))");
        jpql.append("       or a.type = 7)                                 ");

        List<Object[]> rsList = smsEao.executeSQLQuery(jpql.toString());

        if (!CollectionUtils.isEmpty(rsList)) {
            List<Sms> smsList = new ArrayList<Sms>();
            for (Object[] rs : rsList) {
                Sms sms = new Sms();
                sms.setSmsId(((BigDecimal) rs[0]).longValue());
                sms.setName(rs[1].toString());
                sms.setType(((BigDecimal) rs[2]).intValue());
                sms.setContent(rs[3].toString());
                sms.setCycleType(rs[4] != null ? ((BigDecimal) rs[4]).intValue() : null);
                sms.setActivityId(rs[5] != null ? ((BigDecimal) rs[5]).longValue() : null);
                smsList.add(sms);
            }

            // 遍历短信模版
            for (Sms sms : smsList) {
                Long smsId = sms.getSmsId();
                Integer type = sms.getType();
                List sqlValueList = null;

                if (type == GlobalConst.SMS_TYPE_1 || type == GlobalConst.SMS_TYPE_2 || type == GlobalConst.SMS_TYPE_3) {
                    List<String> mobileList = smsEao.qrySms_ObjCond(smsId, type);
                    if (CollectionUtils.isEmpty(mobileList)) {
                        continue;
                    }
                    String vsql = smsEao.executeSQLQueryOne("select t.vsql from d_sms_type_sql t where t.type = ?1", new Object[] { type });
                    sqlValueList = smsEao.executeSQLQuery(vsql + " and t.mobile in (" + StringUtils.join(mobileList.toArray(), ",") + ")");
                } else if (type == GlobalConst.SMS_TYPE_12) {// 营销短信
                    StringBuffer sql = new StringBuffer();
                    sql.append("select c.mobile                                        ");
                    sql.append("  from d_sms_obj_individcust b, d_archive_individcust c");
                    sql.append(" where b.individcustid = c.individcustid               ");
                    sql.append("   and b.smsid = ?2                                    ");
                    sql.append("   and c.enable = 1                                    ");
                    sql.append("   and c.active <> 4                                   ");
                    sql.append("   and c.issendsms = 1                                 ");
                    sql.append("union                                                  ");
                    sql.append("select c.mobile                                        ");
                    sql.append("  from d_activity_indipartin b, d_archive_individcust c");
                    sql.append(" where b.individcustid = c.individcustid               ");
                    sql.append("   and b.activityid = ?1                               ");
                    sql.append("   and c.enable = 1                                    ");
                    sql.append("   and c.active <> 4                                   ");
                    sql.append("   and c.issendsms = 1                                 ");
                    sql.append("union                                                  ");
                    sql.append("select c.mobile                                        ");
                    sql.append("  from d_activity_parpartin b, d_contact c             ");
                    sql.append(" where b.partnerid = c.partnerid                       ");
                    sql.append("   and b.activityid = ?1                               ");
                    sql.append("   and c.issms = 1                                     ");
                    sql.append("   and length(c.mobile) = 11                           ");
                    sql.append("union                                                  ");
                    sql.append("select c.mobile                                        ");
                    sql.append("  from d_activity_frapartin b, d_contact c             ");
                    sql.append(" where b.franchiseeid = c.franchiseeid                 ");
                    sql.append("   and b.activityid = ?1                               ");
                    sql.append("   and c.issms = 1                                     ");
                    sql.append("   and length(c.mobile) = 11                           ");

                    sqlValueList = smsEao.executeSQLQuery(sql.toString(), new Object[] { sms.getActivityId(), sms.getSmsId() });
                } else if (type == GlobalConst.SMS_TYPE_7) {// 保养短信
                    String vsql = smsEao.executeSQLQueryOne("select t.vsql from d_sms_type_sql t where t.type = 7");
                    sqlValueList = smsEao.executeSQLQuery(vsql + " and mod(trunc(sysdate - t.posbilldate), 30 * ?1) = 0", sms.getCycleType());
                }

                if (!CollectionUtils.isEmpty(sqlValueList)) {
                    List<Long> mobile = new ArrayList<Long>();
                    List<String> text = new ArrayList<String>();

                    List<Object[]> varList = smsEao.executeSQLQuery("select b.colIndex, b.name from d_sms_var a, d_sms_param_var b where a.paramvarid = b.paramvarid and a.smsid = ?1", smsId);
                    // 是否设置“可选变量”
                    if (!CollectionUtils.isEmpty(varList)) {
                        Object[] rs;
                        for (int i = 0; i < sqlValueList.size(); i++) {
                            rs = (Object[]) sqlValueList.get(i);
                            // 获取短信内容
                            String content = sms.getContent();
                            for (Object[] var : varList) {
                                content = content.replaceAll("#" + var[1].toString() + "#", NeuUtils.parseString(rs[Integer.parseInt(var[0].toString())]));
                            }
                            mobile.add(((BigDecimal) rs[0]).longValue());
                            text.add(content);
                        }
                    } else {
                        for (Object rs : sqlValueList) {
                            if (rs instanceof BigDecimal) {
                                mobile.add(((BigDecimal) rs).longValue());
                            } else {
                                mobile.add(((BigDecimal) ((Object[]) rs)[0]).longValue());
                            }
                            text.add(sms.getContent());
                        }
                    }
                    this.batchSendSms(sms, mobile, text);
                }
            }
        }
    }

    /**
     * 分批次调用短信接口：per1000
     */
    private void batchSendSms(Sms sms, List<Long> mobile, List<String> text) {
        if (!CollectionUtils.isEmpty(mobile)) {
            int rsCnt = mobile.size();
            int sendCnt = (int) Math.ceil((double) rsCnt / GlobalConst.SMS_MAX_PER_SIZE);
            for (int i = 0; i < sendCnt; i++) {
                int curMax = rsCnt < (i + 1) * GlobalConst.SMS_MAX_PER_SIZE ? rsCnt : (i + 1) * GlobalConst.SMS_MAX_PER_SIZE;

                List<Long> subMobile = mobile.subList(i * GlobalConst.SMS_MAX_PER_SIZE, curMax);
                List<String> subText = text.subList(i * GlobalConst.SMS_MAX_PER_SIZE, curMax);
                yunpianSms.multiSends(sms.getSmsId(), sms.getType(), subMobile, subText);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 客商生日
     */
    private void businessManBirth() {
        Sms sms = smsEao.executeQueryOne("select s from Sms s where s.type = 11 and s.isValid = 1");
        if (sms != null) {
            StringBuffer jpql = new StringBuffer();
            jpql.append("select t.mobile,                 ");
            jpql.append("       t.name,                   ");
            jpql.append("       to_char(t.birthday, 'mm'),");
            jpql.append("       to_char(t.birthday, 'dd') ");
            jpql.append("  from d_contact t               ");
            jpql.append(" where t.issms = 1               ");
            jpql.append("   and length(t.mobile) = 11     ");
            jpql.append("   and t.name is not null        ");
            jpql.append("   and t.birthday is not null    ");
            jpql.append("   and to_char(t.birthday, 'mm-dd') = to_char(sysdate, 'mm-dd')");

            List<Object[]> rsList = smsEao.executeSQLQuery(jpql.toString());

            if (!CollectionUtils.isEmpty(rsList)) {
                String[] colNameArr = { "手机号码", "客户名称", "出生月", "出生日" };

                List<Long> mobile = new ArrayList<Long>();
                List<String> text = new ArrayList<String>();

                for (Object[] rs : rsList) {
                    // 获取短信内容
                    String content = sms.getContent();
                    for (int i = 0; i < colNameArr.length; i++) {
                        content = content.replaceAll("#" + colNameArr[i] + "#", NeuUtils.parseString(rs[i]));
                    }
                    text.add(content);
                    mobile.add(((BigDecimal) rs[0]).longValue());
                }
                this.batchSendSms(sms, mobile, text);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 5入会提醒、8等级变化、9积分变化====6消费感谢
     * 
     * @author：yuhb
     * @date：2017年1月4日 上午10:58:40
     */
    @Override
    public void immediateSendSms(Integer type, int src, Object mobileOrPosNo) {
        try {
            Sms sms = smsEao.executeQueryOne("select s from Sms s where s.type = ?1 and s.isValid = 1", type);
            if (sms != null) {
                // type->vsql
                String vsql = smsEao.executeSQLQueryOne("select s.vsql from d_sms_type_sql s where s.type = ?1", type);

                // 5入会提醒、8等级变化、9积分变化
                if (type == GlobalConst.SMS_TYPE_5 || type == GlobalConst.SMS_TYPE_8 || type == GlobalConst.SMS_TYPE_9) {
                    vsql += " and t.mobile = ?1";
                } else {
                    vsql += " and t.posNo = ?1";// 6消费感谢
                }
                List<Object[]> sqlValueList = smsEao.executeSQLQuery(vsql, mobileOrPosNo);
                if (!CollectionUtils.isEmpty(sqlValueList)) {
                    // 获取短信内容
                    String content = sms.getContent();
                    List<Object[]> varList = smsEao.executeSQLQuery("select b.colIndex, b.name from d_sms_var a, d_sms_param_var b where a.paramvarid = b.paramvarid and a.smsid = ?1", sms.getSmsId());
                    // 是否设置“可选变量”
                    if (!CollectionUtils.isEmpty(varList)) {
                        for (Object[] rs : sqlValueList) {
                            for (Object[] var : varList) {
                                content = content.replaceAll("#" + var[1].toString() + "#", NeuUtils.parseString(rs[Integer.parseInt(var[0].toString())]));
                            }
                        }
                    }
                    Long mobile = ((BigDecimal) sqlValueList.get(0)[0]).longValue();
                    yunpianSms.singleSends(sms.getSmsId(), type, mobile, content);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();// 确保短信发送失败时不影响操作
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 大件销售：立即发送
     * 
     * @author：yuhb
     * @date：2017年2月2日 下午2:53:01
     */
    @Override
    public void bigSaleSendSms(String posNo) {
        try {
            List<Sms> smsList = smsEao.executeQuery("select s from Sms s where s.type = 10 and s.isValid = 1");
            for (Sms sms : smsList) {
                if (this.checkBigSale(sms.getSmsId(), posNo).intValue() > 0) {
                    String vsql = smsEao.executeSQLQueryOne("select s.vsql from d_sms_type_sql s where s.type = 10");
                    Object[] rsPos = smsEao.executeSQLQueryOne(vsql + " and t.posNo = ?1", posNo);

                    // 获取短信内容
                    String content = sms.getContent();
                    List<Object[]> varList = smsEao.executeSQLQuery("select b.colIndex, b.name from d_sms_var a, d_sms_param_var b where a.paramvarid = b.paramvarid and a.smsid = ?1", sms.getSmsId());
                    // 是否设置“可选变量”
                    if (!CollectionUtils.isEmpty(varList)) {
                        for (Object[] var : varList) {
                            content = content.replaceAll("#" + var[1].toString() + "#", NeuUtils.parseString(rsPos[Integer.parseInt(var[0].toString())]));
                        }
                    }

                    List<Long> mobile = new ArrayList<Long>();
                    List<String> text = new ArrayList<String>();
                    // 给“发送对象”发送相同的内容
                    List<IndividCust> objCustList = smsEao.qryObjCust(sms.getSmsId());
                    for (IndividCust dt : objCustList) {
                        mobile.add(dt.getMobile());
                        text.add(content);
                    }
                    yunpianSms.multiSends(sms.getSmsId(), sms.getType(), mobile, text);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();// 确保短信发送失败时不影响操作
        }
    }

    /**
     * 校验pos单是否属于“大件销售”
     */
    private BigDecimal checkBigSale(Long smsId, String posNo) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select c.comptable, c.compcolumn, b.compsymbol, b.compval");
        jpql.append("  from d_sms_obj_condition b, d_sms_type_cond c");
        jpql.append(" where b.typeCondId = c.typeCondId             ");
        jpql.append("   and b.smsid = ?1                            ");
        jpql.append(" order by c.comptable                          ");// 必须排序，方便在循环时“where or 并集”处理

        List<Object[]> condList = smsEao.executeSQLQuery(jpql.toString(), new Object[] { smsId });
        StringBuffer textJpql = new StringBuffer();
        String lastTable = null;
        for (Object[] rs : condList) {
            String compTable = rs[0].toString();
            String whereStr = rs[1].toString() + rs[2].toString() + rs[3].toString();

            if (lastTable == null) {
                lastTable = compTable;
                textJpql.append("select count(1) as cnt from (select * from d_pos_order t where t.posno = ?1) where " + whereStr);
            } else if (!compTable.equals(lastTable)) {
                lastTable = compTable;
                textJpql.append(" union select count(1) as cnt from (select * from d_pos_order t where t.posno = ?1) where " + whereStr);
            } else {
                textJpql.append(" or " + whereStr);
            }
        }
        return smsEao.executeSQLQueryOne("select sum(cnt) from (" + textJpql.toString() + ")", new Object[] { posNo });
    }

}