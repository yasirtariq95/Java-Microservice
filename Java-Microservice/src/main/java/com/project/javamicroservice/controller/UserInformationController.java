package com.project.javamicroservice.controller;

import com.project.javamicroservice.domain.UserInformation;
import com.project.javamicroservice.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserInformationController {

    @Autowired
    private UserInformationService userInformationService;

    @PostMapping("/user/save")
    public String saveData()
    {
        return userInformationService.insertCSVDataIntoTheDatabase();
    }

    @GetMapping("/user/{id}")
    public UserInformation getAUser(@PathVariable("id") int id)
    {
        UserInformation userInformation = userInformationService.findASpecificUser(id);
        return userInformation;
    }

    @GetMapping("/user/profession/{profession}")
    public List<UserInformation> getUsersByProfession(@PathVariable("profession") String profession)
    {
        List<UserInformation> userInformation = userInformationService.findAUserBasedOnProfession(profession);
        return userInformation;
    }

    @GetMapping("/user/dates")
    public List<UserInformation> getUsersBetweenADateRange(@RequestParam String dateOne, @RequestParam String dateTwo)
    {
        Date firstDate = java.sql.Date.valueOf(dateOne);
        Date secondDate = java.sql.Date.valueOf(dateTwo);
        List<UserInformation> userInformation = userInformationService.findUsersBetweenADateRange(firstDate, secondDate);
        return userInformation;
    }
}
