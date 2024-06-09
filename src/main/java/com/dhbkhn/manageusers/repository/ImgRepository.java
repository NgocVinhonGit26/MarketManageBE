package com.dhbkhn.manageusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dhbkhn.manageusers.DTO.Imgqr;

public interface ImgRepository extends JpaRepository<Imgqr, Integer> {

    // get imgqr by id = 1
    @Query(value = "select img from imgqr where id = 1", nativeQuery = true)
    String getImgqrById();

    // update imgqr by id = 1
    @Modifying
    @Transactional
    @Query(value = "update imgqr set img = :imgQR where id = 1", nativeQuery = true)
    void updateImgqrById(@Param("imgQR") String imgQR);
}
