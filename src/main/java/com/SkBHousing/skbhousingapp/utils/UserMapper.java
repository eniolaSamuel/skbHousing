package com.SkBHousing.skbhousingapp.utils;

import com.SkBHousing.skbhousingapp.data.models.User;
import com.SkBHousing.skbhousingapp.dtos.responses.FindUserResponse;

public class UserMapper {
    public static void map (User user, FindUserResponse findUserResponse){
        findUserResponse.setFullName(user.getFullName());
        findUserResponse.setId(user.getId());
    }
}
