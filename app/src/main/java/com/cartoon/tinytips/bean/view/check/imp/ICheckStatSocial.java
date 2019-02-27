package com.cartoon.tinytips.bean.view.check.imp;

import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.view.StatSocial;

import java.util.List;

public interface ICheckStatSocial {

    void query(final StatSocial social, final IOperateBean<List<StatSocial>> operateBean);

}
