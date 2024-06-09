package com.dhbkhn.manageusers.service.Imgqr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhbkhn.manageusers.repository.ImgRepository;

@Service
public class ImgqrServiceImpl implements ImgqrService {
    @Autowired
    private ImgRepository imgRepository;

    public ImgqrServiceImpl(ImgRepository imgRepository) {
        this.imgRepository = imgRepository;
    }

    @Override
    public String getImgqrById() {
        return imgRepository.getImgqrById();
    }

    @Override
    public void updateImgqrById(String imgQR) {
        imgRepository.updateImgqrById(imgQR);
    }
}
