package com.project.javamicroservice.service;

import com.project.javamicroservice.domain.UserInformation;
import com.project.javamicroservice.repo.UserInformationRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserInformationImplementation implements UserInformationService{

    @Autowired
    private UserInformationRepo repo;

    @Override
    public UserInformation findASpecificUser(int id) {
        Optional<UserInformation> userInformation = repo.findById(id);
        return userInformation.get();
    }

    @Override
    public List<UserInformation> findAUserBasedOnProfession(String profession) {
        List<UserInformation> userInformation = repo.findByProfession(profession);
        return userInformation;
    }

    @Override
    public List<UserInformation> findUsersBetweenADateRange(Date dateOne, Date dateTwo) {
        List<UserInformation> userInformation = repo.findUsersCreatedBetweenADateRange(dateOne, dateTwo);
        return userInformation;
    }

    @Override
    public String insertCSVDataIntoTheDatabase() {
        String result = "Data inserted...";
        try {
            UserInformation userInformation = new UserInformation();
            BufferedReader lineReader = new BufferedReader(new FileReader("src/main/resources/UserInformation.csv"));
            lineReader.readLine(); // skip header line
            String lineText = "";
            while ((lineText = lineReader.readLine()) != null){
                String[] data = lineText.split(",");
                int ID = Integer.parseInt(data[0]);
                String firstName = data[1];
                String lastName = data[2];
                String emailInfo = data[3];
                String professionInfo = data[4];
                java.sql.Date dateInfo = java.sql.Date.valueOf(data[5]);
                String countryInfo = data[6];
                String cityInfo = data[7];
                userInformation.setId(ID);
                userInformation.setFirstName(firstName);
                userInformation.setLastName(lastName);
                userInformation.setEmailAddress(emailInfo);
                userInformation.setProfession(professionInfo);
                userInformation.setDate(dateInfo);
                userInformation.setCountry(countryInfo);
                userInformation.setCity(cityInfo);
                repo.save(userInformation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Claims parseJWT(String secret, String token) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token).getBody();
        return claims;
    }
}
