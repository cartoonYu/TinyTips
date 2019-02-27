package com.cartoon.tinytips.bean.table.Operate.imp;

import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Information;

import java.util.List;

public interface IOperateInformation {

    void add(Information information, IOperateBean<String> operateBean);  //增加个人信息

    void delete(Information condition,IOperateBean<String> operateBean);  //删除个人信息

    void query(Information condition,IOperateBean<List<Information>> operateBean);  //查询个人信息

    void update(Information oldInformation,Information newInformation,IOperateBean<String> operateBean);  //修改个人信息

}
