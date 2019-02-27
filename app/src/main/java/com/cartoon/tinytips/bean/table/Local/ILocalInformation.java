package com.cartoon.tinytips.bean.table.Local;

import com.cartoon.tinytips.bean.table.Information;

public interface ILocalInformation {

    boolean add(Information information);   //将个人信息写入本地文件

    boolean update(Information information);   //更新个人信息

    Information query();     //查询个人信息
}
