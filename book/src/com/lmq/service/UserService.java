package com.lmq.service;

import com.lmq.dao.UserDao;
import com.lmq.dao.imp.UserDaoimpl;
import com.lmq.pojo.User;
import com.lmq.utils.BaseDAO;

import java.util.concurrent.TransferQueue;

public class UserService extends BaseDAO<User> {


    public boolean validate(User user){
        UserDao userDao = new UserDaoimpl();
        userDao.delete(user);
        if (user!=null){
            return true;
        }
        return false;
    }

    public void regist(User user) {
        UserDao userDao = new UserDaoimpl();
        userDao.delete(user);

    }




    public boolean registerfind(User user) {

    }
}
