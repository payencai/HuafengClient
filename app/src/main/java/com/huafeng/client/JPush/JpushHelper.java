package com.huafeng.client.JPush;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;


import com.huafeng.client.MyApp;

import java.lang.ref.WeakReference;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;

public class JpushHelper {
    /**
     * 增加
     */
    public static final int ACTION_ADD = 1;
    /**
     * 覆盖
     */
    public static final int ACTION_SET = 2;
    /**
     * 删除部分
     */
    public static final int ACTION_DELETE = 3;
    /**
     * 删除所有
     */
    public static final int ACTION_CLEAN = 4;
    /**
     * 查询
     */
    public static final int ACTION_GET = 5;
    /**
     * 检测
     **/
    public static final int ACTION_CHECK = 6;
    /**
     * 重新设置messageCode
     **/
    private static final int DELAY_SEND_ACTION = 7;
    /**
     * 重设时长间隔
     **/
    private static final long DELAY_TIME = 1000 * 60;//一分钟

    public static int mSequence = 1;
    private static JpushHelper mInstance;
    private SparseArray<TagAliasBean> mTagAliasActionCache = new SparseArray<TagAliasBean>();

    private Handler delaySendHandler = new JpushHandler(JpushHelper.this);
    public static final String TAG = "JpushHelper";

    private JpushHelper() {
    }

    public static JpushHelper getInstance() {
        if (mInstance == null) {
            synchronized (JpushHelper.class) {
                if (mInstance == null) {
                    mInstance = new JpushHelper();
                }
            }
        }
        return mInstance;
    }

    public void put(int sequence, TagAliasBean tagAliasBean) {
        mTagAliasActionCache.put(sequence, tagAliasBean);
    }

    public TagAliasBean get(int sequence) {
        return mTagAliasActionCache.get(sequence);
    }

    public TagAliasBean remove(int sequence) {
        return mTagAliasActionCache.get(sequence);
    }


    /**
     * 处理tag和alias的操作
     **/
    public void handleAction(int sequence, TagAliasBean tagAliasBean) {
        Context context = MyApp.sContext;
        if (tagAliasBean == null) {
            // LogUtil.e(TAG,"=====tagAliasBean was null=====");
            return;
        }
        put(sequence, tagAliasBean);
        if (tagAliasBean.isAliasAction()) {
            //别名的处理(Alias)
            handleAlias(context, sequence, tagAliasBean);
        } else {
            //标签的处理(Tag)
            handleTags(context, sequence, tagAliasBean);
        }
    }

    /**
     * 别名的处理(Alias)
     **/
    private void handleAlias(Context context, int sequence, TagAliasBean tagAliasBean) {
        switch (tagAliasBean.getAction()) {
            case ACTION_GET://获取
                JPushInterface.getAlias(context, sequence);
                break;
            case ACTION_DELETE://删除
                JPushInterface.deleteAlias(context, sequence);
                break;
            case ACTION_SET://设置
                JPushInterface.setAlias(context, sequence, tagAliasBean.getAlias());
                break;
            default:
                //LogUtil.e(TAG,"====unsupport alias action type===");
                break;
        }
    }

    /**
     * 标签的处理(Tag)
     **/
    private void handleTags(Context context, int sequence, TagAliasBean tagAliasBean) {
        switch (tagAliasBean.getAction()) {
            case ACTION_ADD://添加
                JPushInterface.addTags(context, sequence, tagAliasBean.getTags());
                break;
            case ACTION_SET://设置
                JPushInterface.setTags(context, sequence, tagAliasBean.getTags());
                break;
            case ACTION_DELETE://删除
                JPushInterface.deleteTags(context, sequence, tagAliasBean.getTags());
                break;
            case ACTION_CHECK://检查
                //一次只能check一个tag
                String tag = (String) tagAliasBean.getTags().toArray()[0];
                JPushInterface.checkTagBindState(context, sequence, tag);
                break;
            case ACTION_GET://获取
                JPushInterface.getAllTags(context, sequence);
                break;
            case ACTION_CLEAN://清除
                JPushInterface.cleanTags(context, sequence);
                break;
            default:
                //LogUtil.e(JpushHelper.class,"====unsupport tag action type===");
                break;
        }
    }

    /**
     * 是否需要重新设置
     **/
    private boolean retryActionIfNeeded(int errorCode, TagAliasBean tagAliasBean) {
//        if(!NetUtil.isNetworkConnected()){
//            //检测网络链接
//            LogUtil.e(JpushHelper.class,"=======no network=======");
//            return false;
//        }
        //返回的错误码为6002 超时,6014 服务器繁忙,都建议延迟重试
//        if(errorCode == 6002 || errorCode == 6014){
//            LogUtil.e(JpushHelper.class,"=======need retry=======");
//            if(tagAliasBean!=null){
//                Message message = new Message();
//                message.what = DELAY_SEND_ACTION;
//                message.obj = tagAliasBean;
//                delaySendHandler.sendMessageDelayed(message,DELAY_TIME);
//                return true;
//            }
//        }
        return false;
    }

    static class JpushHandler extends Handler {
        //弱引用(引用外部类)
        WeakReference<JpushHelper> mCls;

        JpushHandler(JpushHelper cls) {
            //构造弱引用
            mCls = new WeakReference<JpushHelper>(cls);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //通过弱引用获取外部类.
            JpushHelper cls = mCls.get();
            //进行非空再操作
            if (cls != null) {
                switch (msg.what) {
                    case DELAY_SEND_ACTION:
                        if (msg.obj != null && msg.obj instanceof TagAliasBean) {
                            //LogUtil.e(TAG, "===on delay time===");
                            JpushHelper.mSequence++;
                            TagAliasBean tagAliasBean = (TagAliasBean) msg.obj;
                            cls.put(JpushHelper.mSequence, tagAliasBean);
                            cls.handleAction(JpushHelper.mSequence, tagAliasBean);
                        } else {
                            //LogUtil.e(TAG, "====unexcepted - msg obj was incorrect===");
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * tag增删查改的操作会在此方法中回调结果
     **/
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        int sequence = jPushMessage.getSequence();
        //LogUtil.e(TAG, "action - onTagOperatorResult, sequence:" + sequence + ",tags:" + jPushMessage.getTags());
        //LogUtil.e(TAG, "tags size:" + jPushMessage.getTags().size());
        //根据sequence从之前操作缓存中获取缓存记录
        TagAliasBean tagAliasBean = get(sequence);
        if (tagAliasBean == null) {
           // LogUtil.e(TAG, "获取缓存记录失败");
            return;
        }
        if (jPushMessage.getErrorCode() == 0) {
            //LogUtil.e(TAG, "action - modify tag Success,sequence:" + sequence);
            remove(sequence);
        } else {
            String logs = null;
            if (jPushMessage.getErrorCode() == 6018) {
                //tag数量超过限制,需要先清除一部分再add
                logs = "tag数量超过限制,需要先清除一部分再add";
            }
            logs += ", errorCode:" + jPushMessage.getErrorCode();
            if (!retryActionIfNeeded(jPushMessage.getErrorCode(), tagAliasBean)) {
                //LogUtil.e(TAG, logs);
            }
        }
    }

    ;

    /**
     * 查询某个tag与当前用户的绑定状态的操作会在此方法中回调结果
     **/
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        int sequence = jPushMessage.getSequence();
        //LogUtil.e(TAG, "action - onCheckTagOperatorResult, sequence:" + sequence + ",checktag:" + jPushMessage.getCheckTag());
        //根据sequence从之前操作缓存中获取缓存记录
        TagAliasBean tagAliasBean = get(sequence);
        if (tagAliasBean == null) {
            //LogUtil.e(TAG, "===onCheckTagOperatorResult==获取缓存记录失败==");
            return;
        }
        if (jPushMessage.getErrorCode() == 0) {
           // LogUtil.e(TAG, "tagBean:" + tagAliasBean);
            remove(sequence);
        } else {
            if (!retryActionIfNeeded(jPushMessage.getErrorCode(), tagAliasBean)) {
               // LogUtil.e(TAG, "=====" + jPushMessage.getErrorCode() + "====");
            }
        }
    }

    /**
     * alias相关的操作会在此方法中回调结果
     **/
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        int sequence = jPushMessage.getSequence();
        //LogUtil.e(TAG, "action - onAliasOperatorResult, sequence:" + sequence + ",alias:" + jPushMessage.getAlias());
        //根据sequence从之前操作缓存中获取缓存记录
        TagAliasBean tagAliasBean = get(sequence);
        if (tagAliasBean == null) {
            //LogUtil.e(TAG, "===onAliasOperatorResult获取缓存记录失败===");
            return;
        }
        if (jPushMessage.getErrorCode() == 0) {
            //LogUtil.e(TAG, "action - modify alias Success,sequence:" + sequence);
            remove(sequence);
        } else {
            if (!retryActionIfNeeded(jPushMessage.getErrorCode(), tagAliasBean)) {
                //LogUtil.e(TAG, "=====alias, errorCode:" + jPushMessage.getErrorCode() + "====");
            }
        }
    }

//    作者：奔跑的佩恩
//    链接：https://www.jianshu.com/p/1b1dd62b2d13
//    來源：简书
//    简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
}
