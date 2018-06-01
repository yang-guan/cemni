package com.huiju.actment.activity.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.actment.activity.entity.JudgeAct;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "JudgeActEaoBean")
public class JudgeActEaoBean extends GenericEaoImpl<JudgeAct, Long> implements JudgeActEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}