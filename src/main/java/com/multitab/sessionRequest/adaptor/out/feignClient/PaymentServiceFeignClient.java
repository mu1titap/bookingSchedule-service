package com.multitab.sessionRequest.adaptor.out.feignClient;

import com.multitab.sessionRequest.adaptor.out.feignClient.vo.SessionPaymentVo;
import com.multitab.sessionRequest.common.entity.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://api.adaptors.site/payment-service")
public interface PaymentServiceFeignClient {

    @PostMapping("/api/v1/payment/session")
    BaseResponse<Void> paymentSession(@RequestBody SessionPaymentVo servicePaymentVo);
}
