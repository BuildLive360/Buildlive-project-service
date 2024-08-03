package com.buildlive.projectservice.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "COMPANY-SERVICE",url = "http://18.190.72.144:8010")
public interface CompanyFeignClient {

        @GetMapping("/api/v1/company/{companyId}/get-partyMember-name/{email}")
        String getPartyMemberName(@PathVariable("email")String email, @PathVariable UUID companyId);


}
