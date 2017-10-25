package com.wangxia.battle.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by 昝奥博 on 2017/9/14 0014
 * Email:18772833900@163.com
 * Explain：
 */
public class BannerAdapter implements CBViewHolderCreator<BannerAdapter.Holder> {

    @Override
    public Holder createHolder() {
        return new Holder();
    }

    public class Holder implements com.bigkoo.convenientbanner.holder.Holder<String> {
        private SimpleDraweeView imageView;

        @Override
        public View createView(Context context) {
            imageView = new SimpleDraweeView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String url) {
            imageView.setImageURI(url);
        }
    }

}
