package com.dhbkhn.manageusers.service.ReportShopboat;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhbkhn.manageusers.model.ReportShopboat;
import com.dhbkhn.manageusers.repository.ReportShopboatRepository;

@Service
public class ReportShopboatServiceImpl implements ReportShopboatService {
    private final ReportShopboatRepository reportShopboatRepository;

    @Autowired
    public ReportShopboatServiceImpl(ReportShopboatRepository reportShopboatRepository) {
        this.reportShopboatRepository = reportShopboatRepository;
    }

    @Override
    @Transactional
    public void createNewReport(int shop_boat_id, int user_id, String description, String imgrp,
            String code_order_product, String status,
            Timestamp created_at) {
        reportShopboatRepository.createNewReport(shop_boat_id, user_id, description, imgrp, code_order_product, status,
                created_at);
    }

    @Override
    public Page<Object[]> getListReportSB(int page) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            return reportShopboatRepository.getListReportSB(pageable);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void updateStatusReport(String status, int id) {
        reportShopboatRepository.updateStatusReport(status, id);
    }

    @Override
    public ReportShopboat getReportShopboatById(int id) {
        return reportShopboatRepository.findById(id).orElse(null);
    }
}
