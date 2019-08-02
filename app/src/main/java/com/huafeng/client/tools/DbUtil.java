package com.huafeng.client.tools;

import com.huafeng.client.model.Account;

import org.litepal.LitePal;

import java.util.List;

public class DbUtil {
    public static boolean isShouldAddToDb(Account account){
        boolean isAdd=true;
        List<Account> accounts= LitePal.findAll(Account.class);
        for (int i = 0; i <accounts.size() ; i++) {
            Account account1=accounts.get(i);
            if(account.getUsername().equals(account1.getUsername())){
                 isAdd=false;
                 break;
            }
        }
        return isAdd;
    }
}
