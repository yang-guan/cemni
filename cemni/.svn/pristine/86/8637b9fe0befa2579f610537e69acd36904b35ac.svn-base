package com.huiju.inter.store;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.transaction.UserTransaction;

import org.apache.commons.lang3.StringUtils;

import com.huiju.archive.franchisee.entity.Franchisee;
import com.huiju.archive.franchisee.logic.FranchiseeRemote;
import com.huiju.archive.individcust.eao.IndividCustEaoLocal;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.eao.SqlEaoLocal;
import com.huiju.console.org.entity.Org;
import com.huiju.console.org.logic.OrgRemote;
import com.huiju.console.store.entity.Store;
import com.huiju.console.store.logic.StoreRemote;
import com.huiju.inter.httpclient.HttpClientRemote;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.inter.interLog.logic.InterLogRemote;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.json.Json;
import com.huiju.module.util.CollectionUtils;
import com.huiju.utils.InterJsonParseUtils;
import com.huiju.utils.NeuUtils;

/**
 * 门店
 * 
 * <pre>
 * {
 *     "addr": "武进湖塘武宜中路188号",
 *     "areaName": "尤俊",
 *     "areaNo": "321102198311096338",
 *     "attr": "1",
 *     "bigAreaName": "郭梅",
 *     "bigAreaNo": "321081197812047249",
 *     "city": "3204",
 *     "cityName": "常州市",
 *     "county": "320412",
 *     "countyName": "武进区",
 *     "form": "2",
 *     "fraCode": "001973",
 *     "fraParentCode": "",
 *     "isValid": "1",
 *     "name": "常州吾悦广场专厅",
 *     "province": "32",
 *     "provinceName": "江苏省",
 *     "shopupDate": "2017-04-24",
 *     "storeNo": "T20519030",
 *     "tel": "13360008886"
 * }
 * </pre>
 * 
 * @author：yuhb
 * @date：2016年12月28日 下午2:07:18
 */
@Stateless
@WebService
@SuppressWarnings({ "unchecked", "rawtypes" })
@TransactionManagement(TransactionManagementType.BEAN)
public class StoreWsBean implements StoreWs {
    @Resource
    private UserTransaction ux;
    @EJB
    private StoreRemote storeLogic;
    @EJB
    private OrgRemote orgLogic;
    @EJB
    private InterLogRemote logLogic;
    @EJB
    private FranchiseeRemote franchiseeLogic;
    @EJB
    private IndividCustEaoLocal individCustEao;
    @EJB
    private HttpClientRemote httpLogic;
    @EJB
    private SqlEaoLocal sqlEao;

    @Override
    @WebMethod
    @WebResult(name = "message")
    public String nc2crm(@WebParam(name = "message") String message) {
        String respContent = null;
        Calendar reqTime = Calendar.getInstance();

        Integer flag = GlobalConst.FAIL;
        String msg = null;

        try {
            Map errMap = new HashMap();

            Map json = Json.parseMap(message);
            String storeNo = InterJsonParseUtils.parseString(json, "storeNo", "门店编码", errMap, false);
            String name = InterJsonParseUtils.parseString(json, "name", "门店名称", errMap, false);
            Integer attr = InterJsonParseUtils.parseInteger(json, "attr", "门店属性", errMap, false);
            Integer form = InterJsonParseUtils.parseInteger(json, "form", "门店形态", errMap, false);
            Calendar shopupDate = InterJsonParseUtils.parseCalendar(json, "shopupDate", "开店日期", "yyyy-MM-dd", errMap, false);
            String tel = InterJsonParseUtils.parseString(json, "tel", "电话号码", errMap, false);
            Integer isValid = InterJsonParseUtils.parseInteger(json, "isValid", errMap);
            String bigAreaNo = InterJsonParseUtils.parseString(json, "bigAreaNo", "大区编码", errMap, false);
            String bigAreaName = InterJsonParseUtils.parseString(json, "bigAreaName", "大区名称", errMap, false);
            String areaNo = InterJsonParseUtils.parseString(json, "areaNo", "区域编码", errMap, false);
            String areaName = InterJsonParseUtils.parseString(json, "areaName", "区域名称", errMap, false);
            String fraCode = InterJsonParseUtils.parseString(json, "fraCode", "所属加盟商", errMap, attr == 1 ? false : true);// 加盟店时“加盟商”必填
            Integer province = InterJsonParseUtils.parseInteger(json, "province", errMap);
            String provinceName = InterJsonParseUtils.parseString(json, "provinceName", errMap);
            Integer city = InterJsonParseUtils.parseInteger(json, "city", errMap);
            String cityName = InterJsonParseUtils.parseString(json, "cityName", errMap);
            Integer county = InterJsonParseUtils.parseInteger(json, "county", errMap);
            String countyName = InterJsonParseUtils.parseString(json, "countyName", errMap);
            String addr = InterJsonParseUtils.parseString(json, "addr", errMap);

            String oldStoreName = null;
            String oldAreaNo = null;

            if (CollectionUtils.isEmpty(errMap)) {
                ux.begin();

                Map searchParam = new HashMap();
                searchParam.put("EQ_storeNo", storeNo);
                Store dt = storeLogic.find(searchParam);
                if (dt == null) {
                    dt = new Store();
                } else {
                    oldStoreName = dt.getName();
                    oldAreaNo = dt.getAreaNo();
                }
                dt.setStoreNo(storeNo);
                dt.setName(name);
                dt.setAttr(attr);
                dt.setForm(form);
                dt.setShopupDate(shopupDate);
                dt.setTel(tel);
                dt.setIsValid(isValid);
                dt.setAreaNo(areaNo);
                dt.setAreaName(areaName);
                dt.setProvince(province);
                dt.setProvinceName(provinceName);
                dt.setCity(city);
                dt.setCityName(cityName);
                dt.setCounty(county);
                dt.setCountyName(countyName);
                dt.setAddr(addr);

                // 大区：编码及名称-特殊处理
                Map bigAreaParam = new HashMap();
                bigAreaParam.put("EQ_type", GlobalConst.ORG_TYPE_3);
                bigAreaParam.put("EQ_responsor", bigAreaName + "（" + bigAreaNo + "）");
                Org bigAreaOrg = orgLogic.find(bigAreaParam);
                Long bigAreaId = bigAreaOrg.getOrgId();
                dt.setBigAreaId(bigAreaId);
                dt.setBigAreaNo(bigAreaOrg.getOrgCode());
                dt.setBigAreaName(bigAreaOrg.getName());// 更改名称为：运营一部、运营二部

                // 区域
                Map areaParam = new HashMap();
                bigAreaParam.put("EQ_type", GlobalConst.ORG_TYPE_4);
                areaParam.put("EQ_orgCode", areaNo);
                Org areaOrg = orgLogic.find(areaParam);
                if (areaOrg != null) {
                    areaOrg.setName(areaName);
                    areaOrg.setResponsor(areaName + "（" + areaNo + "）");
                    areaOrg.setMdate(reqTime);
                    orgLogic.merge(areaOrg);
                } else {
                    areaOrg = new Org();
                    areaOrg.setOrgCode(areaNo);
                    areaOrg.setName(areaName);
                    areaOrg.setType(GlobalConst.ORG_TYPE_4);
                    areaOrg.setParentId(bigAreaId);
                    areaOrg.setIsValid(GlobalConst.YES);
                    areaOrg.setResponsor(areaName + "（" + areaNo + "）");
                    areaOrg.setCdate(reqTime);
                    areaOrg = orgLogic.persist(areaOrg);
                }
                // 删除没有门店的区域
                if (oldAreaNo != null && !oldAreaNo.equals(areaNo)) {
                    String jpql = "delete from d_cn_org t where t.orgcode = ?1 and not exists (select 1 from d_cn_org f where f.parentid = t.orgid)";
                    sqlEao.executeSQLUpdate(jpql, oldAreaNo);
                }
                Long areaId = areaOrg.getOrgId();
                dt.setAreaId(areaId);

                // 加盟商
                if (StringUtils.isNotBlank(fraCode)) {
                    Map fraParam = new HashMap();
                    fraParam.put("EQ_fraCode", fraCode);
                    Franchisee franchisee = franchiseeLogic.find(fraParam);
                    dt.setFranchisee(franchisee);
                }

                // 门店：新增或修改
                if (dt.getStoreId() == null) {
                    dt.setCdate(reqTime);
                    dt = storeLogic.persist(dt);
                } else {
                    dt.setMdate(reqTime);
                    storeLogic.merge(dt);
                    // 同步修改个人客户中的门店名称信息
                    if (!oldStoreName.equals(name)) {
                        individCustEao.executeUpdate("update IndividCust t set t.fristStoreName = ?1 where t.fristStoreNo = ?2", new Object[] { name, dt.getStoreNo() });
                        individCustEao.executeUpdate("update IndividCust t set t.lastStoreName = ?1 where t.lastStoreNo = ?2", new Object[] { name, dt.getStoreNo() });
                        individCustEao.executeUpdate("update IndividCust t set t.belongStoreName = ?1 where t.belongStoreNo = ?2", new Object[] { name, dt.getStoreNo() });
                    }
                }

                // 同步修改组织机构树
                Map orgParam = new HashMap();
                orgParam.put("EQ_store_storeId", dt.getStoreId());
                Org org = orgLogic.find(orgParam);
                if (org != null) {
                    org.setName(name);
                    org.setParentId(areaId);
                    org.setIsValid(GlobalConst.YES);
                    org.setMdate(reqTime);
                    orgLogic.merge(org);
                } else {
                    org = new Org();
                    org.setStore(dt);
                    org.setOrgCode(storeNo);
                    org.setName(name);
                    org.setType(GlobalConst.ORG_TYPE_5);
                    org.setParentId(areaId);
                    org.setIsValid(GlobalConst.YES);
                    org.setCdate(reqTime);
                    orgLogic.persist(org);
                }
                // 删除没有门店的区域
                if (oldAreaNo != null && !oldAreaNo.equals(areaNo)) {
                    String jpql = "delete from d_cn_org t where t.orgcode = ?1 and not exists (select 1 from d_cn_org f where f.parentid = t.orgid)";
                    sqlEao.executeSQLUpdate(jpql, oldAreaNo);
                }

                // 门店：crm->微信
                json.remove("bigAreaNo");
                json.remove("bigAreaName");
                json.remove("areaNo");
                json.remove("areaName");
                json.remove("fraParentCode");
                json.remove("fraCode");
                httpLogic.post(NeuUtils.getProperty("store_crm2wechar"), "StoreWsBean.crm2wechar", GlobalConst.SYS_SRC_NC, DataUtils.toJson(json));

                ux.commit();
                flag = GlobalConst.SUCCESS;
                msg = GlobalConst.TIP_SUCCESS;
            } else {
                msg = DataUtils.toJson(errMap);
            }
        } catch (Exception e) {
            try {
                ux.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            msg = NeuUtils.getStackTraceStr(e);
        } finally {
            // 拼接返回报文
            Map data = new HashMap();
            data.put("flag", flag);
            data.put("msg", msg);
            respContent = DataUtils.toJson(data);

            // 接口日志
            InterLog interLog = new InterLog();
            interLog.setCrmClassMethod("StoreWsBean.nc2crm");
            interLog.setReqTime(reqTime);
            interLog.setReqContent(message);
            interLog.setSrc(GlobalConst.SYS_SRC_NC);
            interLog.setStatus(flag);
            interLog.setRespTime(Calendar.getInstance());
            interLog.setRespContent(respContent);
            logLogic.persist(interLog);
        }
        return respContent;
    }

}