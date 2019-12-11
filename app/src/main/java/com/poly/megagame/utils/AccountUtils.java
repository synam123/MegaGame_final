package com.poly.megagame.utils;

import com.google.gson.Gson;
import com.poly.megagame.model.Account;

public class AccountUtils {
    public static void setAccount(Account account){
        SharedPresUtils.getInstance().put("Account",account.toString());
    }

    public static Account getAccount(){
        String accountJson = SharedPresUtils.getInstance().get("Account",String.class);

        return new Gson().fromJson(accountJson,Account.class);
    }
}
