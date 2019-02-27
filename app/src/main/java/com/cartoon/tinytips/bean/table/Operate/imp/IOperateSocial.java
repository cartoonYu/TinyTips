package com.cartoon.tinytips.bean.table.Operate.imp;

import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Social;

import java.util.List;

public interface IOperateSocial {

    void add(Social social,IOperateBean<String> operateBean);

    void delete(Social condition,IOperateBean<String> operateBean);

    void query(Social condition,IOperateBean<List<Social>> operateBean);

}
