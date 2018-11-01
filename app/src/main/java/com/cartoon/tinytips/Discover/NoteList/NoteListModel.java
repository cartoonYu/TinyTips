package com.cartoon.tinytips.Discover.NoteList;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;

import java.io.File;
import java.util.List;

public class NoteListModel  implements INoteList.Model{
    private Note note;
    private File userImage;
    private List<String> contents;
    private String temp = "HttpRequestException encountered解决方法\n\n"+
            "问题原因是Github 禁用了TLS v1.0 and v1.1，必须更新Windows的git凭证管理器。\n"+
            "通过此网址https://github.com/Microsoft/Git-Credential-Manager-for-Windows/releases/\n"+
            "下载网站中最新的GCMW最新版，安装然后重启git即可";

    private Context mContext;
    @Override
    public void getUserInformation(ValueCallBack<Information> callBack) {
        userImage = new File("./nav_icon");
        Information usr = new Information();
        usr.setNickName("cartoon");
        if (userImage != null){
            usr.setHeadPortrait(userImage);
        }
        callBack.onSuccess(usr);
    }

    @Override
    public void getNote(ValueCallBack<Note> callBack) {
        note = new Note();
        note.setTitle("git常用命令及HttpRequestException encountered解决方法");
        contents.add(temp);
        note.setWordDetails(contents);
        callBack.onSuccess(note);
    }
}
