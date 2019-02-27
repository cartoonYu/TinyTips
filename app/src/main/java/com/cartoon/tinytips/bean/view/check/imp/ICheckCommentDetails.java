package com.cartoon.tinytips.bean.view.check.imp;

import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.view.CommentDetails;

import java.util.List;

public interface ICheckCommentDetails {

    void query(CommentDetails details,IOperateBean<List<CommentDetails>> operateBean);

}
