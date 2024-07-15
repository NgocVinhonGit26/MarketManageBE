package com.dhbkhn.manageusers.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dhbkhn.manageusers.model.ReportShopboat;

public interface ReportShopboatRepository extends JpaRepository<ReportShopboat, Integer> {

        // create new report
        @Modifying
        @Query(value = "INSERT INTO reportshopboat (shop_boat_id, user_id, description, imgrp, code_order_product,status, created_at) "
                        + "VALUES (:shop_boat_id, :user_id, :description, :imgrp, :code_order_product,:status, :created_at)", nativeQuery = true)
        void createNewReport(
                        @Param("shop_boat_id") int shop_boat_id,
                        @Param("user_id") int user_id,
                        @Param("description") String description,
                        @Param("imgrp") String imgrp,
                        @Param("code_order_product") String code_order_product,
                        @Param("status") String status,
                        @Param("created_at") Timestamp created_at);

        // get list report
        @Query(value = "SELECT rs.id, rs.shop_boat_id, sb.name AS shop_boat_name, rs.user_id, u.name AS user_name, rs.description, rs.imgrp, rs.status, rs.created_at, u.phone_number, u.username, rs.code_order_product "
                        +
                        "FROM reportshopboat rs JOIN user u ON rs.user_id = u.id JOIN shopboat sb ON rs.shop_boat_id = sb.id", nativeQuery = true)
        public Page<Object[]> getListReportSB(Pageable pageable);

        // update status report
        @Modifying
        @Query(value = "UPDATE reportshopboat SET status = :status WHERE id = :id", nativeQuery = true)
        void updateStatusReport(@Param("status") String status, @Param("id") int id);
}
