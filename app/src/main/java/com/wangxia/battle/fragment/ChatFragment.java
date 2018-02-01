package com.wangxia.battle.fragment;

import android.content.Context;
import android.view.View;

import com.wangxia.battle.R;
import com.wangxia.battle.fragment.base.LazyBaseFragment;

import java.lang.ref.WeakReference;

/**
 * Created by 昝奥博 on 2017/12/1 0001
 * Email:18772833900@163.com
 * Explain：聊天界面
 */
public class ChatFragment extends LazyBaseFragment {

    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeakReference<Context> weakReference = new WeakReference<Context>(context);
        mContext = weakReference.get();
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_chat, null);
        return view;
    }

    @Override
    public void initData() {
        // 最好在会话的主页加上
//        EMClient.getInstance().chatManager().loadAllConversations();
//        EMClient.getInstance().groupManager().loadAllGroups();

    }

    @Override
    public void initListener() {
        //注册一个监听连接状态的listener
//        EMClient.getInstance().addConnectionListener(new MyConnectionListener());

    }


    @Override
    public void recycleMemory() {

    }
    //实现ConnectionListener接口
//    private class MyConnectionListener implements EMConnectionListener {
//        @Override
//        public void onConnected() {
//        }
//        @Override
//        public void onDisconnected(final int error) {
//            getActivity().runOnUiThread(new Runnable() {
//
//                @Override
//                public void run() {
//                    if(error == EMError.USER_REMOVED){
//                        // 显示帐号已经被移除
//                    }else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
//                        // 显示帐号在其他设备登录
//                    } else {
//                        if (NetUtils.hasNetwork(mContext)){
//                            //连接不到聊天服务器
//
//                        }
//                        else{
//
//                            //当前网络不可用，请检查网络设置
//                        }
//                    }
//                }
//            });
//        }
//    }
}
