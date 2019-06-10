package com.dhcc.data;

import com.dhcc.DbUtil.UserDBHelper;
import com.dhcc.Entity.UserInfoEntity;
import com.dhcc.data.model.LoggedInUser;

import java.io.IOException;
import java.util.List;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(UserDBHelper mHelper ,String bank_card, String password) {
    //登录逻辑判断
        try {
            // TODO: handle loggedInUser authentication
            List<UserInfoEntity> fakeUsers = mHelper.queryByBankCard(bank_card);
            if(fakeUsers.size()<0){
                return new Result.Error(new IOException("卡号不存在"));
            }else{
                for (UserInfoEntity us:fakeUsers) {
                    if(us.get_password().equals(password)){
                        return new Result.Success<>(us);
                    }
                }
                return new Result.Error(new IOException("密码不正确"));
            }

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
