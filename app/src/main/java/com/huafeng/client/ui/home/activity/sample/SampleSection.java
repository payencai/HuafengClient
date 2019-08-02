package com.huafeng.client.ui.home.activity.sample;

import com.chad.library.adapter.base.entity.SectionEntity;

public class SampleSection extends SectionEntity<SelectProcess.ProcessListBean> {

    public SampleSection(boolean isHeader, String header) {
        super(isHeader, header);
    }
    public SampleSection(SelectProcess.ProcessListBean t) {
        super(t);
    }
}
