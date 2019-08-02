package com.huafeng.client.ui.home.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.ProduceMana;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class ProduceManaAdapter extends BaseQuickAdapter<ProduceMana,BaseViewHolder> {
    public ProduceManaAdapter(int layoutResId, @Nullable List<ProduceMana> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProduceMana item) {
        ImageView iv_img=helper.getView(R.id.iv_img);
        TextView tv_locate=helper.getView(R.id.tv_locate);
        TextView tv_time=helper.getView(R.id.tv_time);
        TextView tv_sampleno=helper.getView(R.id.tv_sampleno);
        TextView tv_num=helper.getView(R.id.tv_num);
        TextView tv_start=helper.getView(R.id.tv_start);
        TextView tv_end=helper.getView(R.id.tv_end);
        TextView tv_status=helper.getView(R.id.tv_status);
        tv_sampleno.setText(item.getSampleNo());
        tv_time.setText(item.getMonth());
        tv_locate.setText(item.getProductOrderNumber());
        tv_num.setText("裁片数量："+item.getQuantity());
        tv_start.setText("开始时间："+item.getGmtCreate());
        if(item.getStatus()==2){
            tv_end.setText("结束时间："+item.getFinishTime());
        }else if(item.getStatus()==1){
            tv_end.setText("预计时间："+item.getEstimatedTimeOfFinishment());
        }else{
            tv_end.setText("终止时间："+item.getStopTime());
        }
        if (!TextUtils.isEmpty(item.getImageUrl()))
            Glide.with(mContext).load(item.getImageUrl()).into(iv_img);
        else
            iv_img.setImageResource(R.mipmap.image_load_err);
        tv_status.setText(item.getStageName());
        if(TextUtils.isEmpty(item.getStageName())){
              return;
        }
        switch (item.getStageName()){
            case "车间":
                tv_status.setTextColor(mContext.getResources().getColor(R.color.produce_sew));
                break;
            case "洗水":
                tv_status.setTextColor(mContext.getResources().getColor(R.color.produce_wash));
                break;
            case "后整":
                tv_status.setTextColor(mContext.getResources().getColor(R.color.produce_after));
                break;
            case "完成":
            case "完成（已入库）":
                tv_status.setTextColor(mContext.getResources().getColor(R.color.produce_finsh));
                break;
            case "终止":
                tv_status.setTextColor(mContext.getResources().getColor(R.color.produce_stop));
                break;

        }
    }
}
