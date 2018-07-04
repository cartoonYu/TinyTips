package com.cartoon.tinytips.Personal.Security;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.PersonalInformation;

/**
 * Created by cartoon on 2018/2/6.
 */

interface ISecurity {
    interface View{
        void handleClickBack();
        void showToast(String code);
        PersonalInformation getOldPassword();
        String getNewPassword();
        String getConfirmPassword();
    }
    interface Presenter{
        void revampPassword();
    }
    interface Model{
        void setOldPassword(PersonalInformation information);
        void setNewPassword(String newPassword);
        void revampPassword(ValueCallBack<String> callBack);
    }
}
