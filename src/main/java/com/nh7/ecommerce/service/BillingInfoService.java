package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.BillingInfo;
import com.nh7.ecommerce.repository.BillingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingInfoService {
    @Autowired
    private BillingInfoRepository billingInfoRepository;

    public BillingInfo save(BillingInfo billingInfo) {
        return billingInfoRepository.save(billingInfo);
    }
}
