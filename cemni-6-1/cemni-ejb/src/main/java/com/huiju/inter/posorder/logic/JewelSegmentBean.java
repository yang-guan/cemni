package com.huiju.inter.posorder.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.inter.posorder.eao.JewelSegmentEaoLocal;
import com.huiju.inter.posorder.entity.JewelSegment;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "JewelSegmentBean")
public class JewelSegmentBean extends GenericLogicImpl<JewelSegment, Long> implements JewelSegmentRemote {
    @EJB(mappedName = "JewelSegmentEaoBean")
    private JewelSegmentEaoLocal jewelsegmentEao;

    @Override
    protected GenericEao<JewelSegment, Long> getGenericEao() {
        return jewelsegmentEao;
    }
}