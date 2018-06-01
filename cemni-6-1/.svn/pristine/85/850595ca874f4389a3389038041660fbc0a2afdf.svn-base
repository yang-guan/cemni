package com.huiju.afterservice.rightmaint.entity;

import com.huiju.module.data.BaseState;

public enum RightStatus implements BaseState {
    NOPASS("不通过"), PASS("通过"), NEW("新建"), CONFIRM("提交");

    private String desc;

    @Override
    public String getStateDesc() {
        return desc;
    }

    private RightStatus(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return desc;
    }

    public Integer value() {
        return this.ordinal();
    }

    public String getName() {
        return this.name();
    }

    public int getIndex() {
        return this.ordinal();
    }

    public String toString() {
        return String.valueOf(this.ordinal());
    }

}
