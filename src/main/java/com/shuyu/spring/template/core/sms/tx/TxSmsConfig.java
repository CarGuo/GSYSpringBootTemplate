package com.shuyu.spring.template.core.sms.tx;

import com.github.qcloudsms.SmsSingleSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TxSmsConfig {

    @Autowired
    public TxSmsProperties txSmsProperties;


    @Bean
    SmsSingleSender getSmsSingleSender() {
        SmsSingleSender ssender = new SmsSingleSender(txSmsProperties.getAppid(), txSmsProperties.getAppkey());
        return ssender;
    }
}
