package com.huiju.expandbusi.franchiseeprofit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.huiju.archive.franchisee.entity.Franchisee;
import com.huiju.common.DataDict;
import com.huiju.console.store.entity.Store;
import com.huiju.console.store.logic.StoreRemote;
import com.huiju.expandbusi.franchiseeprofit.costsupport.entity.CostSupport;
import com.huiju.expandbusi.franchiseeprofit.costsupport.logic.CostSupportRemote;
import com.huiju.expandbusi.franchiseeprofit.expandcost.entity.ExpandCost;
import com.huiju.expandbusi.franchiseeprofit.expandcost.logic.ExpandCostRemote;
import com.huiju.expandbusi.franchiseeprofit.franchiseeprofit.entity.FranchiseeProfit;
import com.huiju.expandbusi.franchiseeprofit.franchiseeprofit.logic.FranchiseeProfitRemote;
import com.huiju.expandbusi.franchiseeprofit.profit.entity.Profit;
import com.huiju.expandbusi.franchiseeprofit.profit.logic.ProfitRemote;
import com.huiju.expandbusi.franchiseeprofit.revenue.entity.Revenue;
import com.huiju.expandbusi.franchiseeprofit.revenue.logic.RevenueRemote;
import com.huiju.expandbusi.franchiseeprofit.shopcost.entity.ShopCost;
import com.huiju.expandbusi.franchiseeprofit.shopcost.logic.ShopCostRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.Sort.Direction;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

/**
 * 加盟商盈利状况
 * 
 * @author：WangYuanJun
 * @date：2016年12月27日 上午11:32:35
 */
public class FranchiseeProfitAction extends BaseAction<FranchiseeProfit, Long> {
    private static final long serialVersionUID = 1L;

    @EJB(mappedName = "FranchiseeProfitBean")
    private FranchiseeProfitRemote franchiseeProfitLogic;

    @EJB(mappedName = "CostSupportBean")
    private CostSupportRemote costSupportLogic;

    @EJB(mappedName = "ProfitBean")
    private ProfitRemote profitLogic;

    @EJB(mappedName = "ExpandCostBean")
    private ExpandCostRemote expandCostLogic;

    @EJB(mappedName = "ShopCostBean")
    private ShopCostRemote shopCostLogic;

    @EJB(mappedName = "RevenueBean")
    private RevenueRemote revenueLogic;

    @EJB(mappedName = "StoreBean")
    private StoreRemote storeLogic;

    public String init() throws Exception {
        jsPath.add("/js/expandbusi/franchiseeprofit/Q.expandbusi.franchiseeprofit.js");

        String[] authorities = { "D_FRANCHISEEPROFIT_LIST", "D_FRANCHISEEPROFIT_ADD", "D_FRANCHISEEPROFIT_EDIT", "D_FRANCHISEEPROFIT_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Object LE_cdate = params.get("LE_createDate");
        if (LE_cdate != null && !StringUtils.isEmpty(LE_cdate.toString())) {
            Calendar cl = NeuUtils.parseCalendar(LE_cdate.toString());
            cl.add(Calendar.DATE, 1);
            params.put("LE_createDate", NeuUtils.parseStringFromCalendar(cl));

        }
        Page<FranchiseeProfit> page = new Page<FranchiseeProfit>(start, limit, sort, dir);
        page = franchiseeProfitLogic.findAll(page, params);
        List<FranchiseeProfit> franchiseeProfits = page.getResult();
        for (FranchiseeProfit dt : franchiseeProfits) {
            if (dt.getFranchisee() != null) {
                dt.getFranchisee().setFraTypeName(DataDict.getDictName(DataDict.FRANCHISEE_TYPE, dt.getFranchisee().getFraType()));
                dt.getFranchisee().setSourcesName(DataDict.getDictName(DataDict.FRANCHISEE_SOURCE, dt.getFranchisee().getSources()));
            }
        }
        renderJson(page);
    }

    public void save() {
        this.setOneToManyValue();
        model.setCreateUser(WebUtils.getUserCode());
        model.setCreateDate(Calendar.getInstance());
        model = franchiseeProfitLogic.persist(model);
        dealJson(true);
    }

    public void edit() {
        model = franchiseeProfitLogic.findById(id);
        Franchisee franchisee = model.getFranchisee();
        if (franchisee != null) {
            franchisee.setFraTypeName(DataDict.getDictName(DataDict.FRANCHISEE_TYPE, franchisee.getFraType()));
            franchisee.setSourcesName(DataDict.getDictName(DataDict.FRANCHISEE_SOURCE, franchisee.getSources()));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("IN_storeId", model.getCustomerPattern());
        List<Store> stores = storeLogic.findAll(map);
        List<String> scopeNameList = new ArrayList<String>();
        for (Store store : stores) {
            scopeNameList.add(store.getName());
        }
        model.setStoreName(StringUtils.join(scopeNameList.toArray(), ","));
        dealJson(true, model);
    }

    public void update() {
        FranchiseeProfit franchiseeAudit = franchiseeProfitLogic.findById(model.getFranchiseeProfitId());
        model.setCreateDate(franchiseeAudit.getCreateDate());
        model.setCreateUser(franchiseeAudit.getCreateUser());
        model.setModifyUser(WebUtils.getUserCode());
        model.setModifyDate(Calendar.getInstance());
        if (model.getFranchisee().getFranchiseeId() == null) {
            model.setFranchisee(null);
        }
        this.setOneToManyValue();
        franchiseeProfitLogic.merge(model);
        dealJson(true);
    }

    /**
     * 公司支持成本
     * 
     * 
     * @author：WangYuanJun
     * @date：2016年12月28日 上午10:09:11
     */
    public String getCostSupport() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        String[] sorts = { "costSupportId" + "," + Direction.ASC };
        List<CostSupport> list = costSupportLogic.findAll(params, sorts);
        return renderJson(list);
    }

    /**
     * 盈利
     * 
     * 
     * @author：WangYuanJun
     * @date：2016年12月28日 上午10:09:58
     */
    public String getProfit() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        String[] sorts = { "profitId" + "," + Direction.ASC };
        List<Profit> list = profitLogic.findAll(params, sorts);
        return renderJson(list);
    }

    /**
     * 拓展成本
     * 
     * 
     * @author：WangYuanJun
     * @date：2016年12月28日 上午10:10:06
     */
    public String getExpandCost() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        String[] sorts = { "expandCostId" + "," + Direction.ASC };
        List<ExpandCost> list = expandCostLogic.findAll(params, sorts);
        return renderJson(list);
    }

    /**
     * 开店成本
     * 
     * 
     * @author：WangYuanJun
     * @date：2016年12月28日 上午10:10:24
     */
    public String getShopCost() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        String[] sorts = { "shopCostId" + "," + Direction.ASC };
        List<ShopCost> list = shopCostLogic.findAll(params, sorts);
        return renderJson(list);
    }

    /**
     * 收入
     * 
     * 
     * @author：WangYuanJun
     * @date：2016年12月28日 上午10:10:34
     */
    public String getRevenue() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        String[] sorts = { "revenueId" + "," + Direction.ASC };
        List<Revenue> list = revenueLogic.findAll(params, sorts);
        return renderJson(list);
    }

    private void setOneToManyValue() {
        if (null != this.model.getCostSupport()) {
            for (CostSupport costSupport : this.model.getCostSupport()) {
                costSupport.setFranchiseeProfit(model);
                costSupport.setCreateUser(WebUtils.getUserCode());
                costSupport.setCreateDate(Calendar.getInstance());
                double personSupport = costSupport.getPersonSupport() == null ? 0 : costSupport.getPersonSupport();
                double activitySupport = costSupport.getActivitySupport() == null ? 0 : costSupport.getActivitySupport();
                double propSupport = costSupport.getPropSupport() == null ? 0 : costSupport.getPropSupport();
                costSupport.setTotal(personSupport + activitySupport + propSupport);
            }
        }
        if (null != this.model.getExpandCost()) {
            for (ExpandCost expandCost : this.model.getExpandCost()) {
                expandCost.setFranchiseeProfit(model);
                expandCost.setCreateUser(WebUtils.getUserCode());
                expandCost.setCreateDate(Calendar.getInstance());
                double person = expandCost.getPerson() == null ? 0 : expandCost.getPerson();
                double publicRelations = expandCost.getPublicRelations() == null ? 0 : expandCost.getPublicRelations();
                double activity = expandCost.getActivity() == null ? 0 : expandCost.getActivity();
                expandCost.setTotal(person + publicRelations + activity);
            }
        }
        if (null != this.model.getShopCost()) {
            for (ShopCost shopCost : this.model.getShopCost()) {
                shopCost.setFranchiseeProfit(model);
                shopCost.setCreateUser(WebUtils.getUserCode());
                shopCost.setCreateDate(Calendar.getInstance());
                double joinfee = shopCost.getJoinfee() == null ? 0 : shopCost.getJoinfee();
                double bond = shopCost.getBond() == null ? 0 : shopCost.getBond();
                double decorationCost = shopCost.getDecorationCost() == null ? 0 : shopCost.getDecorationCost();
                double infoCost = shopCost.getInfoCost() == null ? 0 : shopCost.getInfoCost();
                double propCost = shopCost.getPropCost() == null ? 0 : shopCost.getPropCost();
                double materielCost = shopCost.getMaterielCost() == null ? 0 : shopCost.getMaterielCost();
                double rent = shopCost.getRent() == null ? 0 : shopCost.getRent();
                double wages = shopCost.getWages() == null ? 0 : shopCost.getWages();
                double activity = shopCost.getActivity() == null ? 0 : shopCost.getActivity();
                double firstTakeGoods = shopCost.getFirstTakeGoods() == null ? 0 : shopCost.getFirstTakeGoods();
                double cumulativeTakeGoods = shopCost.getCumulativeTakeGoods() == null ? 0 : shopCost.getCumulativeTakeGoods();
                shopCost.setTotal(joinfee + bond + decorationCost + infoCost + propCost + materielCost + rent + wages + activity + firstTakeGoods
                        + cumulativeTakeGoods);
            }
        }
        if (null != this.model.getRevenue()) {
            for (Revenue revenue : this.model.getRevenue()) {
                revenue.setFranchiseeProfit(model);
                revenue.setCreateUser(WebUtils.getUserCode());
                revenue.setCreateDate(Calendar.getInstance());
                double sale = revenue.getSale() == null ? 0 : revenue.getSale();
                double rebate = revenue.getRebate() == null ? 0 : revenue.getRebate();
                revenue.setTotal(sale + rebate);
            }
        }
        // 得到备注
        List<String> listRemake = new ArrayList<String>();
        if (null != this.model.getProfit()) {
            for (Profit profit : this.model.getProfit()) {
                listRemake.add(profit.getRemake());
            }
        }
        model.setProfit(null);
        if (model.getRevenue() != null && model.getShopCost() != null && model.getExpandCost() != null) {
            // 收入
            int revenue = model.getRevenue().size();
            // 开店成本
            int shopCost = model.getShopCost().size();
            // 拓展成本
            int expandCost = model.getExpandCost().size();
            int[] arr = new int[] { revenue, shopCost, expandCost };
            int min = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < min) {
                    min = arr[i];
                }
            }
            List<Profit> profits = new ArrayList<Profit>();
            // 得到最小数min-交集的行数
            for (int i = 0; i < min; i++) {
                Profit profit = new Profit();
                profit.setFranchiseeProfit(model);
                profit.setCreateUser(WebUtils.getUserCode());
                profit.setCreateDate(Calendar.getInstance());
                double revenueTotal = model.getRevenue().get(i).getTotal(), shopCostTotal = model.getShopCost().get(i).getTotal(), expandCostTotal = model
                        .getExpandCost().get(i).getTotal();
                // 盈利中的盈利
                double profitProfit = revenueTotal - shopCostTotal - expandCostTotal;
                profit.setProfit(profitProfit);
                // 盈利中的roi
                if (shopCostTotal + expandCostTotal == 0) {
                    profit.setRoi(null);
                } else {
                    BigDecimal bg = new BigDecimal(profitProfit / (shopCostTotal + expandCostTotal)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    profit.setRoi(bg.doubleValue());
                }
                if (!CollectionUtils.isEmpty(listRemake) && i <= listRemake.size() - 1) {
                    profit.setRemake(listRemake.get(i));
                }
                profits.add(profit);
            }
            model.setProfit(profits);
        }
    }

}