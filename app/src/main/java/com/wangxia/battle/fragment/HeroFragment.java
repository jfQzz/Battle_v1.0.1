package com.wangxia.battle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.TabWithPagerActivity;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.base.BaseFragment;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.ArmBean;
import com.wangxia.battle.model.bean.CurseBean;
import com.wangxia.battle.model.bean.HeroBean;
import com.wangxia.battle.presenter.impPresenter.ArmListPresenter;
import com.wangxia.battle.presenter.impPresenter.CurseListPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.ImageUtil;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.NetUtil;
import com.wangxia.battle.util.SpUtil;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by 昝奥博 on 2018/1/8 0008
 * Email:18772833900@163.com
 * Explain：
 */
public class HeroFragment extends BaseFragment implements View.OnClickListener,ISuccessCallbackData{
    @BindView(R.id.video_player)
    JZVideoPlayerStandard videoPlayer;
    @BindView(R.id.tv_hero_name)
    TextView tvHeroName;
    @BindView(R.id.tv_hero_nick)
    TextView tvHeroNick;
    @BindView(R.id.value_linear_Layout)
    LinearLayout valueLinearLayout;
    @BindView(R.id.pg_out)
    ProgressBar pgOut;
    @BindView(R.id.pg_quick)
    ProgressBar pgQuick;
    @BindView(R.id.pg_control)
    ProgressBar pgControl;
    @BindView(R.id.pg_increase)
    ProgressBar pgIncrease;
    @BindView(R.id.pg_live)
    ProgressBar pgLive;
    @BindView(R.id.progress_bar_linear_layout)
    LinearLayout progressBarLinearLayout;
    @BindView(R.id.rb_diff_star)
    RatingBar rbDiffStar;
    @BindView(R.id.tv_hero_desc)
    TextView tvHeroDesc;
    @BindView(R.id.iv_skill_default)
    SimpleDraweeView ivSkillDefault;
    @BindView(R.id.iv_skill_one)
    SimpleDraweeView ivSkillOne;
    @BindView(R.id.iv_skill_two)
    SimpleDraweeView ivSkillTwo;
    @BindView(R.id.iv_skill_three)
    SimpleDraweeView ivSkillThree;
    @BindView(R.id.iv_skill_fore)
    SimpleDraweeView ivSkillFore;
    @BindView(R.id.tv_skill_name)
    TextView tvSkillName;
    @BindView(R.id.tv_skill_delay)
    TextView tvSkillDelay;
    @BindView(R.id.tv_skill_desc)
    TextView tvSkillDesc;
    @BindView(R.id.tv_skill_add_advice)
    TextView tvSkillAddAdvice;
    @BindView(R.id.iv_skill_main)
    SimpleDraweeView ivSkillMain;
    @BindView(R.id.tv_skill_main_name)
    TextView tvSkillMainName;
    @BindView(R.id.tv_skill_main)
    TextView tvSkillMain;
    @BindView(R.id.iv_skill_next)
    SimpleDraweeView ivSkillNext;
    @BindView(R.id.tv_skill_next_name)
    TextView tvSkillNextName;
    @BindView(R.id.tv_skill_next)
    TextView tvSkillNext;
    @BindView(R.id.tv_re_add_point)
    TextView tvReAddPoint;
    @BindView(R.id.ll_curse_one)
    LinearLayout llCurseOne;
    @BindView(R.id.iv_curse_one)
    SimpleDraweeView ivCurseOne;
    @BindView(R.id.tv_curse_one)
    TextView tvCurseOne;
    @BindView(R.id.ll_curse_two)
    LinearLayout llCurseTwo;
    @BindView(R.id.iv_curse_two)
    SimpleDraweeView ivCurseTwo;
    @BindView(R.id.tv_curse_two)
    TextView tvCurseTwo;
    @BindView(R.id.tv_curse_desc)
    TextView tvCurseDesc;
    @BindView(R.id.tv_re_arm_one)
    TextView tvReArmOne;
    @BindView(R.id.tv_re_arm_two)
    TextView tvReArmTwo;
    @BindView(R.id.tv_re_arm_three)
    TextView tvReArmThree;
    @BindView(R.id.iv_arm_one)
    SimpleDraweeView ivArmOne;
    @BindView(R.id.iv_arm_two)
    SimpleDraweeView ivArmTwo;
    @BindView(R.id.iv_arm_three)
    SimpleDraweeView ivArmThree;
    @BindView(R.id.iv_arm_fore)
    SimpleDraweeView ivArmFore;
    @BindView(R.id.iv_arm_five)
    SimpleDraweeView ivArmFive;
    @BindView(R.id.iv_arm_six)
    SimpleDraweeView ivArmSix;
    @BindView(R.id.tv_arm_one)
    TextView tvArmOne;
    @BindView(R.id.tv_arm_two)
    TextView tvArmTwo;
    @BindView(R.id.tv_arm_three)
    TextView tvArmThree;
    @BindView(R.id.tv_arm_fore)
    TextView tvArmFore;
    @BindView(R.id.tv_arm_five)
    TextView tvArmFive;
    @BindView(R.id.tv_arm_six)
    TextView tvArmSix;
    private Unbinder mBind;
    private String mLabel;
    private int mPosition;
    private HeroBean.DataBean.U5f0fu795eu6280u80fdBean.U5929u751fu88abu52a8Bean mSkillDefault;
    private HeroBean.DataBean.U5f0fu795eu6280u80fdBean.U4e00u6280u80fdBean mSkillOne;
    private HeroBean.DataBean.U5f0fu795eu6280u80fdBean.U4e8cu6280u80fdBean mSkillTwo;
    private HeroBean.DataBean.U5f0fu795eu6280u80fdBean.U4e09u6280u80fdBean mSkillThree;
    private HeroBean.DataBean.U5f0fu795eu6280u80fdBean.U56dbu6280u80fdBean mSkillFore;
    private CurseListPresenter mCurseListPresenter;
    private int curseOneId;
    private int curseTwoId;
    private HeroBean.DataBean.U63a8u8350u88c5u5907Bean mArms;
    private ArmListPresenter mArmListPresenter;


    public static HeroFragment newInstance(String key, int position) {
        Bundle args = new Bundle();
        args.putString(Constant.string.ARG_ONE, key);
        args.putInt(Constant.string.ARG_TWO, position);
        HeroFragment fragment = new HeroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mLabel = arguments.getString(Constant.string.ARG_ONE);
        mPosition = arguments.getInt(Constant.string.ARG_TWO);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_hero, null);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        HeroBean.DataBean dataBeen = App.heroMap.get(mLabel).get(mPosition);
        videoPlayer.setUp("https://moba.v.netease.com/ssl/idle/"+dataBeen.getU5f0fu795eID()+".mp4", JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, dataBeen.getU5f0fu795eu540du79f0());
//        videoPlayer.thumbImageView.setImageURI(Uri.parse("https://moba.res.netease.com/m/zt/20171216180055/data/"+dataBeen.getU5f0fu795eID()+".png"));
        ImageUtil.loadBitmap(getActivity(),"https://moba.res.netease.com/m/zt/20171216180055/data/"+dataBeen.getU5f0fu795eID()+".png",videoPlayer.thumbImageView);
        tvHeroName.setText(TxtFormatUtil.HtmlFormat(dataBeen.getU5f0fu795eu540du79f0()));
        tvHeroNick.setText("日文CV:" + dataBeen.getCvu540du5b57().get(0) + "\n中文CV：" + dataBeen.getCvu540du5b57().get(1) + "\n特长：" + dataBeen.getU5f0fu795eu6807u7b7e());
        tvHeroDesc.setText(dataBeen.getU5f0fu795eu653bu7565().replaceAll("#r", "\n"));
        HeroBean.DataBean.U8bc4u5206Bean u = dataBeen.getU8bc4u5206();
        pgOut.setProgress(u.getU8f93u51fa());
        pgLive.setProgress(u.getU751fu5b58());
        pgControl.setProgress(u.getU63a7u5236());
        pgQuick.setProgress(u.getU654fu6377());
        pgIncrease.setProgress(u.getU589eu76ca());
        rbDiffStar.setRating(u.getU96beu5ea6());
        HeroBean.DataBean.U5f0fu795eu6280u80fdBean s = dataBeen.getU5f0fu795eu6280u80fd();
        mSkillDefault = s.getU5929u751fu88abu52a8();
        mSkillOne = s.getU4e00u6280u80fd();
        mSkillTwo = s.getU4e8cu6280u80fd();
        mSkillThree =  s.getU4e09u6280u80fd();
        mSkillFore =  s.getU56dbu6280u80fd();
        setMainAndNextSkill(dataBeen);
        ivSkillDefault.setImageURI(TxtFormatUtil.getPic(mSkillDefault.getU56feu6807u8defu5f84()));
        ivSkillOne.setImageURI(TxtFormatUtil.getPic(mSkillOne.getU56feu6807u8defu5f84()));
        ivSkillTwo.setImageURI(TxtFormatUtil.getPic(mSkillTwo.getU56feu6807u8defu5f84()));
        ivSkillThree.setImageURI(TxtFormatUtil.getPic(mSkillThree.getU56feu6807u8defu5f84()));
        ivSkillFore.setImageURI(TxtFormatUtil.getPic(mSkillFore.getU56feu6807u8defu5f84()));
        setSkill(mSkillDefault.getU6280u80fdu540du79f0(), null,null, mSkillDefault.getU6280u80fdu63cfu8ff0());
        List<Integer> u6 = dataBeen.getU63a8u8350u7075u5492();
        curseOneId = u6.get(Constant.number.ZERO);
        curseTwoId = u6.get(Constant.number.ONE);
        String curseData = SpUtil.getString(mContext, Constant.saveTag.CURSE_SAVE, null);
        if(!TextUtils.isEmpty(curseData))
            setCurse(GsonUtil.getGson().fromJson(curseData, CurseBean.class));
        else {
            mCurseListPresenter = new CurseListPresenter(this);
            mCurseListPresenter.queryList(Constant.number.ZERO, null, Constant.number.ZERO);
        }
        mArms = dataBeen.getU63a8u8350u88c5u5907();
        //判断是否已经获取了arm
        String oldArmTime = SpUtil.getString(mContext, Constant.saveTag.ARM_EFFECTIVE_TIME, null);
        String armData = SpUtil.getString(mContext, Constant.saveTag.ARM_SAVE, null);
        if (!NetUtil.isNetAvailable(mContext) || !TextUtils.isEmpty(oldArmTime) && !TextUtils.isEmpty(armData) && !(Mytime.isDelay(Constant.number.SEVEN, oldArmTime))) {
            initArm(GsonUtil.getGson().fromJson(armData, ArmBean.class));
        } else {
            mArmListPresenter = new ArmListPresenter(this);
            mArmListPresenter.queryList(Constant.number.ONE, null, Constant.number.ZERO);
        }
    }



    @Override
    public void initListener() {
        ivSkillDefault.setOnClickListener(this);
        ivSkillOne.setOnClickListener(this);
        ivSkillTwo.setOnClickListener(this);
        ivSkillThree.setOnClickListener(this);
        ivSkillFore.setOnClickListener(this);
        llCurseOne.setOnClickListener(this);
        llCurseTwo.setOnClickListener(this);
        tvReArmOne.setOnClickListener(this);
        tvReArmTwo.setOnClickListener(this);
        tvReArmThree.setOnClickListener(this);
        ivArmOne.setOnClickListener(this);
        ivArmTwo.setOnClickListener(this);
        ivArmThree.setOnClickListener(this);
        ivArmFore.setOnClickListener(this);
        ivArmFive.setOnClickListener(this);
        ivArmSix.setOnClickListener(this);

    }

    @Override
    public void recycleMemory() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_skill_default:
                setSkill(mSkillDefault.getU6280u80fdu540du79f0(), null,null, mSkillDefault.getU6280u80fdu63cfu8ff0());
                break;
            case R.id.iv_skill_one:
                setSkill(mSkillOne.getU6280u80fdu540du79f0(), mSkillOne.getU6280u80fdu6210u957f().getU51b7u5374().toArray(new Integer[mSkillOne.getU6280u80fdu6210u957f().getU51b7u5374().size()]),mSkillOne.getU6280u80fdu6210u957f().getU6d88u8017().toArray(new Integer[mSkillOne.getU6280u80fdu6210u957f().getU6d88u8017().size()]), mSkillOne.getU6280u80fdu63cfu8ff0());
                break;
            case R.id.iv_skill_two:
                setSkill(mSkillTwo.getU6280u80fdu540du79f0(), mSkillTwo.getU6280u80fdu6210u957f().getU51b7u5374().toArray(new Integer[mSkillTwo.getU6280u80fdu6210u957f().getU51b7u5374().size()]),mSkillTwo.getU6280u80fdu6210u957f().getU6d88u8017().toArray(new Integer[mSkillTwo.getU6280u80fdu6210u957f().getU6d88u8017().size()]), mSkillTwo.getU6280u80fdu63cfu8ff0());
                break;
            case R.id.iv_skill_three:
                setSkill(mSkillThree.getU6280u80fdu540du79f0(), mSkillThree.getU6280u80fdu6210u957f().getU51b7u5374().toArray(new Integer[mSkillThree.getU6280u80fdu6210u957f().getU51b7u5374().size()]),mSkillThree.getU6280u80fdu6210u957f().getU6d88u8017().toArray(new Integer[mSkillThree.getU6280u80fdu6210u957f().getU6d88u8017().size()]), mSkillThree.getU6280u80fdu63cfu8ff0());
                break;
            case R.id.iv_skill_fore:
                setSkill(mSkillFore.getU6280u80fdu540du79f0(), mSkillFore.getU6280u80fdu6210u957f().getU51b7u5374().toArray(new Integer[mSkillFore.getU6280u80fdu6210u957f().getU51b7u5374().size()]),mSkillFore.getU6280u80fdu6210u957f().getU6d88u8017().toArray(new Integer[mSkillFore.getU6280u80fdu6210u957f().getU6d88u8017().size()]), mSkillFore.getU6280u80fdu63cfu8ff0());
                break;
            case R.id.ll_curse_one:
                tvCurseDesc.setText(TxtFormatUtil.formatColor(v.getTag(R.id.tag_first).toString()));
                break;
            case R.id.ll_curse_two:
                tvCurseDesc.setText(TxtFormatUtil.formatColor(v.getTag(R.id.tag_first).toString()));
                break;
            case R.id.tv_re_arm_one:
                initArm(mArms.getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481().toArray(new String[mArms.getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481().size()]));
                break;
            case R.id.tv_re_arm_two:
                initArm(mArms.getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482().toArray(new String[mArms.getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481().size()]));
                break;
            case R.id.tv_re_arm_three:
                initArm(mArms.getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483().toArray(new String[mArms.getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481().size()]));
                break;
            case R.id.iv_arm_one:
                TabWithPagerActivity.toTabWithPagerActivity(mContext, Constant.number.FOURTEEN, v.getTag(R.id.tag_first).toString(), Constant.number.ZERO);
                break;
            case R.id.iv_arm_two:
                TabWithPagerActivity.toTabWithPagerActivity(mContext, Constant.number.FOURTEEN, v.getTag(R.id.tag_first).toString(), Constant.number.ZERO);

                break;
            case R.id.iv_arm_three:
                TabWithPagerActivity.toTabWithPagerActivity(mContext, Constant.number.FOURTEEN, v.getTag(R.id.tag_first).toString(), Constant.number.ZERO);

                break;
            case R.id.iv_arm_fore:
                TabWithPagerActivity.toTabWithPagerActivity(mContext, Constant.number.FOURTEEN, v.getTag(R.id.tag_first).toString(), Constant.number.ZERO);

                break;
            case R.id.iv_arm_five:
                TabWithPagerActivity.toTabWithPagerActivity(mContext, Constant.number.FOURTEEN, v.getTag(R.id.tag_first).toString(), Constant.number.ZERO);

                break;
            case R.id.iv_arm_six:
                TabWithPagerActivity.toTabWithPagerActivity(mContext, Constant.number.FOURTEEN, v.getTag(R.id.tag_first).toString(), Constant.number.ZERO);
                break;
        }

    }

    @Override
    public void getResult(Object o, int type) {
        if(null == o || ((AppCompatActivity)mContext).isFinishing()){
            return;
        }
        switch (type){
            case Constant.number.ZERO:
                setCurse((CurseBean)o);
                break;
            case Constant.number.ONE:
                SpUtil.putString(mContext, Constant.saveTag.ARM_EFFECTIVE_TIME, Mytime.getStringToday());
                SpUtil.putString(mContext, Constant.saveTag.ARM_SAVE, GsonUtil.getGson().toJson(o));
                initArm((ArmBean)o);
                break;
        }

    }

    @Override
    public void failRequest() {

    }

    @Override
    public void errorRequest() {

    }

    private void setCurse(CurseBean curse) {
        if(null == curse || null == curse.getData() || curse.getData().isEmpty()){
            return;
        }
        Map<Integer,CurseBean.DataBean> curseMap = new HashMap<>(curse.getData().size());
        for (CurseBean.DataBean bean : curse.getData()) {
            curseMap.put(bean.getU7075u5492ID(),bean);
        }
        App.curseMap = curseMap;
        CurseBean.DataBean dataBean = curseMap.get(curseOneId);
        CurseBean.DataBean dataBeanNext = curseMap.get(curseTwoId);
        if(null == dataBean && null == dataBeanNext){
            return;
        }
        ivCurseOne.setImageURI(TxtFormatUtil.getPic(dataBean.getU56feu6807u8defu5f84()));
        llCurseOne.setTag(R.id.tag_first,dataBean.getU7075u5492u6280u80fdu63cfu8ff0());
        ivCurseTwo.setImageURI(TxtFormatUtil.getPic(dataBeanNext.getU56feu6807u8defu5f84()));
        llCurseTwo.setTag(R.id.tag_first,dataBeanNext.getU7075u5492u6280u80fdu63cfu8ff0());
        tvCurseOne.setText(dataBean.getU7075u5492u540du79f0());
        tvCurseTwo.setText(dataBeanNext.getU7075u5492u540du79f0());
        tvCurseDesc.setText(TxtFormatUtil.formatColor(dataBean.getU7075u5492u6280u80fdu63cfu8ff0()));
    }

    private void initArm(String[] arms) {
        List<SimpleDraweeView> armImg = new ArrayList<>(arms.length);
        List<TextView> armName = new ArrayList<>(arms.length);
        armImg.add(ivArmOne);
        armImg.add(ivArmTwo);
        armImg.add(ivArmThree);
        armImg.add(ivArmFore);
        armImg.add(ivArmFive);
        armImg.add(ivArmSix);
        armName.add(tvArmOne);
        armName.add(tvArmTwo);
        armName.add(tvArmThree);
        armName.add(tvArmFore);
        armName.add(tvArmFive);
        armName.add(tvArmSix);
        for (int i = 0,count = arms.length; i < count; i++) {
            setArm(arms[i],armImg.get(i),armName.get(i));

        }
    }

    private void setArm(String arm, SimpleDraweeView simpleDraweeView,TextView tvName) {
        ArmBean.DataBean dataBean = App.armNameMap.get(arm);
        simpleDraweeView.setImageURI(TxtFormatUtil.getPic(dataBean.getU56feu6807u8defu5f84()));
        simpleDraweeView.setTag(R.id.tag_first,arm);
        tvName.setText(arm);
    }


    private void setMainAndNextSkill(HeroBean.DataBean dataBeen) {
        List<Integer> u6 = dataBeen.getU63a8u8350u52a0u70b9u987au5e8f();
        Integer[] skills = u6.toArray(new Integer[u6.size()]);
        String[] main = getNameAndPic(skills[Constant.number.ZERO]);
        String[] next = getNameAndPic(skills[Constant.number.ONE]);
        String s = Arrays.toString(skills);
        tvReAddPoint.setText(getString(R.string.re_add_point)+":"+s.substring(Constant.number.ONE,s.length()-Constant.number.ONE).replaceAll(",","-").trim());
        ivSkillMain.setImageURI(TxtFormatUtil.getPic(main[Constant.number.ZERO]));
        ivSkillNext.setImageURI(TxtFormatUtil.getPic(next[Constant.number.ZERO]));
        tvSkillMainName.setText(main[Constant.number.TWO]);
        tvSkillNextName.setText(next[Constant.number.TWO]);
        tvSkillMain.setText(String.valueOf(getString(R.string.skill_main)+"-"+main[Constant.number.ONE]));
        tvSkillNext.setText(String.valueOf(getString(R.string.skill_next)+"-"+next[Constant.number.ONE]));
    }

    private String[] getNameAndPic(int nextSkill) {
        String[] result = new String[Constant.number.THREE];
        switch (nextSkill){
            case Constant.number.ONE:
                result[Constant.number.ZERO] = mSkillOne.getU56feu6807u8defu5f84();
                result[Constant.number.ONE] = mSkillOne.getU6280u80fdu540du79f0();
                result[Constant.number.TWO] = getString(R.string.skill_one);
                break;
            case Constant.number.TWO:
                result[Constant.number.ZERO] = mSkillTwo.getU56feu6807u8defu5f84();
                result[Constant.number.ONE] = mSkillTwo.getU6280u80fdu540du79f0();
                result[Constant.number.TWO] =  getString(R.string.skill_two);
                break;
            case Constant.number.THREE:
                result[Constant.number.ZERO] = mSkillThree.getU56feu6807u8defu5f84();
                result[Constant.number.ONE] = mSkillThree.getU6280u80fdu540du79f0();
                result[Constant.number.TWO] = getString(R.string.skill_three);
                break;
            case Constant.number.FORE:
                result[Constant.number.ZERO] = mSkillFore.getU56feu6807u8defu5f84();
                result[Constant.number.ONE] = mSkillFore.getU6280u80fdu540du79f0();
                result[Constant.number.TWO] =  getString(R.string.skill_fore);
                break;
        }
        return result;
    }

    private void setSkill(String name, Integer[] c,  Integer[] s, String desc) {
        tvSkillName.setText(name);
        tvSkillDesc.setText(TxtFormatUtil.formatColor(desc));
        if(null == c && null == s ){
            tvSkillDelay.setText("");
            return;
        }
        StringBuilder cool = new StringBuilder();
        if(null != c&& Constant.number.ZERO != c.length && null != c[0]){
            cool.append("冷却值：");
            for (int i = 0; i < c .length; i++) {
                if(i != c .length-1)
                    cool.append(c [i]).append(" - ");
                else cool.append(c [i]);
            }
        }
        if(null != s && Constant.number.ZERO != s.length&& null != s[0]){
            cool.append("\t消耗值：");
            for (int j = 0; j < s.length; j++) {
                if(j!= s.length-1)
                    cool.append(s[j]).append(" - ");
                else cool.append(s[j]);
            }
        }
        tvSkillDelay.setText(cool.toString());
    }

    private void initArm(ArmBean dataBen) {
        if (null == dataBen || null == dataBen.getData() || dataBen.getData().isEmpty()) {
            return;
        }
        List<String> labels = new ArrayList<>();
        labels.add(getString(R.string.all));
        Map<String, Map<String, List<ArmBean.DataBean>>> armMap = new HashMap<>(dataBen.getData().size());
        Map<String, ArmBean.DataBean> armNameMap = new HashMap<>();
        Map<Integer, ArmBean.DataBean> armIdMap = new HashMap<>();
        for (ArmBean.DataBean bean : dataBen.getData()) {
            if(TextUtils.isEmpty(bean.getU56feu6807u8defu5f84())) continue;
            armNameMap.put(bean.getU88c5u5907u540du79f0(),bean);
            armIdMap.put(bean.getU88c5u5907ID(),bean);
            String armType = bean.getU88c5u5907u7c7bu578b();
            String armRank = bean.getU88c5u5907u7b49u7ea7();
            if (armMap.containsKey(armType)) {
                if (armMap.get(armType).containsKey(armRank))
                    armMap.get(armType).get(armRank).add(bean);
                else {
                    List<ArmBean.DataBean> armList = new ArrayList<>();
                    armList.add(bean);
                    Map<String, List<ArmBean.DataBean>> listMap = armMap.get(armType);
                    if (null == listMap) {
                        listMap = new HashMap<>();
                        listMap.put(armRank, armList);
                        armMap.put(armType, listMap);
                    } else
                        armMap.get(armType).put(armRank, armList);
                }
            } else {
                labels.add(armType);
                Map<String, List<ArmBean.DataBean>> armListMap = new HashMap<>();
                List<ArmBean.DataBean> armList = new ArrayList<>();
                armList.add(bean);
                armListMap.put(armRank, armList);
                armMap.put(armType, armListMap);
            }
            App.armNameMap =armNameMap;
            App.armIdMap = armIdMap;
            App.armMap = armMap;
        }
        initArm(mArms.getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481().toArray(new String[mArms.getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481().size()]));
    }
}
