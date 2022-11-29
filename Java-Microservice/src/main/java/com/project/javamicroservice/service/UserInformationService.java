package com.project.javamicroservice.service;

import com.project.javamicroservice.domain.UserInformation;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.List;

public interface UserInformationService {

    UserInformation findASpecificUser(int id);

    List<UserInformation> findAUserBasedOnProfession(String profession);

    List<UserInformation> findUsersBetweenADateRange(Date dateOne, Date dateTwo);

    String insertCSVDataIntoTheDatabase();

    Claims parseJWT(String secret, String token);
}
