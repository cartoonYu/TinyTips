package com.cartoon.tinytips.bean.table.Operate.imp;

import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Comment;

public interface IOperateComment {

    void add(Comment comment, final IOperateBean<String> operateBean);  //增加评论

    void delete(Comment comment, final IOperateBean<String> operateBean);  //删除评论
}
