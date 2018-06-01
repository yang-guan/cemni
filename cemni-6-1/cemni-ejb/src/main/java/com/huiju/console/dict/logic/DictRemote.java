package com.huiju.console.dict.logic;

import javax.ejb.Remote;

import com.huiju.console.dict.entity.Dict;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface DictRemote extends GenericLogic<Dict, Long> {

    void loadDict();

}