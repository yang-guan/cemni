package com.huiju.competitor.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.competitor.eao.CompetitorEaoLocal;
import com.huiju.competitor.entity.AdDeploy;
import com.huiju.competitor.entity.ChannelSurvey;
import com.huiju.competitor.entity.Competitor;
import com.huiju.competitor.entity.CustSurvey;
import com.huiju.competitor.entity.GoodStatus;
import com.huiju.competitor.entity.MarActivitty;
import com.huiju.competitor.entity.PersonnelInfo;
import com.huiju.competitor.entity.SzNewStyles;

@Stateless(mappedName = "CompetitorBean")
public class CompetitorBean extends GenericLogicImpl<Competitor, Long> implements CompetitorRemote {
    @EJB(mappedName = "CompetitorEaoBean")
    private CompetitorEaoLocal competitorEao;

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
    
    @Override
    protected GenericEao<Competitor, Long> getGenericEao() {
        return competitorEao;
    }

	@Override
	public void delete(Map<String, Object> searchParams,Long id) {
		List<CustSurvey> custsurveyList =competitorCURLogic.findAll(searchParams);
		for(CustSurvey cs:custsurveyList){
			competitorCURLogic.remove(cs);
		}
		
		List<PersonnelInfo> perinfolist =competitorEPLogic.findAll(searchParams);
		for(PersonnelInfo pi:perinfolist){
			competitorEPLogic.remove(pi);
		}
		
		List<ChannelSurvey> chansurveyList =competitorCNRLogic.findAll(searchParams);
		for(ChannelSurvey pi:chansurveyList){
			competitorCNRLogic.remove(pi);
		}
		
		List<MarActivitty> maractivityList=competitorTMLogic.findAll(searchParams);
		for(MarActivitty ma:maractivityList){
			competitorTMLogic.remove(ma);
		}
		
		List<AdDeploy> adDeployList=competitorADLogic.findAll(searchParams);
		for(AdDeploy ad:adDeployList){
			competitorADLogic.remove(ad);
		}
		
		List<GoodStatus> goodStatusList=competitorGSLogic.findAll(searchParams);
		for(GoodStatus gs:goodStatusList){
			competitorGSLogic.remove(gs);
		}
		
		List<SzNewStyles> 	newStyleList=competitorSZNSLogic.findAll(searchParams);
		for(SzNewStyles ss:newStyleList){
			competitorSZNSLogic.remove(ss);
		}
		competitorEao.removeById(id);;
	}
}