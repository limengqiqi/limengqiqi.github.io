package com.lmq.dao.imp;

import com.lmq.dao.UserDao;
import com.lmq.pojo.User;
import com.lmq.utils.BaseDAO;

public class UserDaoimpl extends BaseDAO<User> implements UserDao {

    @Override
    public void delete(User user) {
        String sql = "delete from student where sno = ?";
        executeUpdate(sql,user.getSno());
    }
}
