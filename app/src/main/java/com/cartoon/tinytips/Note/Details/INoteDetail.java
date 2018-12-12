package com.cartoon.tinytips.Note.Details;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Social;

import java.nio.file.SecureDirectoryStream;
import java.util.Map;

interface INoteDetail {

    interface View{
        void setTitle();
        void setDetails();
        void setDate();
        void setLike(int num);
        void setComment(int num);
        void setForward(int num);
        void setCollect(int num);
        void setLike(boolean isClick);
        void setComment(boolean isClick);
        void setForward(boolean isClick);
        void setCollect(boolean isClick);
    }

    interface Presenter{
        void initSocial(Note note);
    }

    interface Model{
        void getSocial(Note note, ValueCallBack<Map<String,Integer>> numCallBack, ValueCallBack<Map<String,Boolean>> isClickCallBack);   //初始化社交

    }
}
