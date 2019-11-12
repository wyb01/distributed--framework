package com.cf.rest.service;

import com.cf.pojo.User;

public interface RestService {
    User selectByPrimaryKey(Long id);
}
