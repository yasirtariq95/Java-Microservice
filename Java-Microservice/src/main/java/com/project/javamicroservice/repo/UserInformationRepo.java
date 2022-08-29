package com.project.javamicroservice.repo;

import com.project.javamicroservice.domain.UserInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserInformationRepo extends CrudRepository<UserInformation, Integer> {

    @Query(value = "SELECT * FROM userinfo u WHERE u.profession = ?1", nativeQuery = true)
    List<UserInformation> findByProfession(String profession);

    @Query(value = "SELECT * FROM userinfo u WHERE u.datecreated BETWEEN ?1 AND ?2", nativeQuery = true)
    List<UserInformation> findUsersCreatedBetweenADateRange(Date dateOne, Date dateTwo);
}
