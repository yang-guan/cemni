package com.huiju.competitor.logic;

import java.util.Map;

import javax.ejb.Remote;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.competitor.entity.Competitor;

@Remote
public interface CompetitorRemote extends GenericLogic<Competitor, Long> {
	public void delete(Map<String, Object> searchParams,Long id);
}