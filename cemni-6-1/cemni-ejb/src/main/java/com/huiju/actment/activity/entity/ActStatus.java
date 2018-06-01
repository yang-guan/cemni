package com.huiju.actment.activity.entity;

import com.huiju.module.data.BaseState;

public enum ActStatus implements BaseState {
    NEW("新建"), CONFIRM("提交"), PASS("通过"), NOPASS("驳回");

    private String desc;

    @Override
    public String getStateDesc() {
        return desc;
    }

    private ActStatus(String desc) {
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