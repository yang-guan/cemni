package com.huiju.salesment.scaletarget.logic;

import java.io.File;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.module.data.logic.GenericLogic;
import com.huiju.salesment.scaletarget.entity.ScaleTarget;

@Remote
public interface ScaleTargetRemote extends GenericLogic<ScaleTarget, Long> {

    @SuppressWarnings("rawtypes")
	Map excel(File file);
}