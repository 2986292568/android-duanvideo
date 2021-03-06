package com.jqh.duanvideo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jqh.duanvideo.R;
import com.jqh.duanvideo.utils.ImgUtils;
import com.jqh.duanvideo.utils.ViewUtils;

/**
 * Created by user on 2018/4/12.
 */
public class RightToolView extends RelativeLayout {

    private Context mContext ;
    private ImageView mAvatarImageView ;
    private IconNumView mLikeIconNumView ;
    private IconNumView mCommentIconNumView ;
    private IconNumView mSendIconNumView ;

    private int userId;
    private String avater ;

    private OnRightToolItemClickListener mOnRightToolItemClickListener;

    public void setOnRightToolItemClickListener(OnRightToolItemClickListener mOnRightToolItemClick) {
        this.mOnRightToolItemClickListener = mOnRightToolItemClick;
    }

    public interface  OnRightToolItemClickListener{
        void onAvatarClick(int userId,String avater);
        void onLikeClick(boolean islike);
        void onCommentClick();
        void onSendClick();
    }
    public RightToolView(Context context) {
        super(context);
        init(context);
    }

    public RightToolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RightToolView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.mContext = context ;
        LayoutInflater.from(context).inflate(R.layout.view_righttool,this);
        mAvatarImageView = ViewUtils.bindViewId(this,R.id.avatar_iv);
        mLikeIconNumView = ViewUtils.bindViewId(this,R.id.like_iconnumview);
        mCommentIconNumView = ViewUtils.bindViewId(this,R.id.comment_iconnumview);
        mSendIconNumView = ViewUtils.bindViewId(this,R.id.send_iconnumview);
        initEvent();
    }



    private void initEvent(){
        mAvatarImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnRightToolItemClickListener != null){
                    mOnRightToolItemClickListener.onAvatarClick(userId,avater);
                }
            }
        });

        mLikeIconNumView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnRightToolItemClickListener != null){
                    mOnRightToolItemClickListener.onLikeClick(!mLikeIconNumView.isSelected());
                }
            }
        });

        mCommentIconNumView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnRightToolItemClickListener != null){
                    mOnRightToolItemClickListener.onCommentClick();
                }
            }
        });

        mSendIconNumView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnRightToolItemClickListener != null){
                    mOnRightToolItemClickListener.onSendClick();
                }
            }
        });

    }

    public void setSendNum(int num){
        mSendIconNumView.setNum(num);
    }

    public void setCommentNum(int num){
        mCommentIconNumView.setNum(num);
    }

    public void setmLikeNum(int num){
        mLikeIconNumView.setNum(num);
    }

    public void setUserId(int userId){
        this.userId = userId;
    }
    //"http://www.qqzhi.com/uploadpic/2014-09-23/000247589.jpg"
    public void loadAvater(String url){
        avater = url ;
        ImgUtils.loadRound(url,mAvatarImageView);
    }

    public void updateLike(int num, boolean liked){
        if(liked)
            mLikeIconNumView.setSelected();
        else
            mLikeIconNumView.setNormal();
        mLikeIconNumView.setNum(num);
    }
}
