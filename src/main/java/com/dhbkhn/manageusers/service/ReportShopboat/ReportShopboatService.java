package com.dhbkhn.manageusers.service.ReportShopboat;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.dhbkhn.manageusers.model.ReportShopboat;

public interface ReportShopboatService {
    // create new report
    void createNewReport(int shop_boat_id, int user_id, String description, String imgrp, String code_order_product,
            String status,
            Timestamp created_at);

    // get list report
    Page<Object[]> getListReportSB(int page);

    // update status report
    void updateStatusReport(String status, int id);

    // getReportShopboatById
    ReportShopboat getReportShopboatById(int id);
}
