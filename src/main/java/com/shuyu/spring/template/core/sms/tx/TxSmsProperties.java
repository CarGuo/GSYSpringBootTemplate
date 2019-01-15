package com.shuyu.spring.template.core.sms.tx;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sms.tx")
public class TxSmsProperties {
    // 短信应用SDK AppID
    private int appid;

    // 短信应用SDK AppKey
    private String appkey;

    // 短信模板ID，需要在短信应用中申请
    private int templateId;

    // 签名
    private String smsSign;

    //超时
    private int timeoutSecond;

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getSmsSign() {
        return smsSign;
    }

    public void setSmsSign(String smsSign) {
        this.smsSign = smsSign;
    }

    public int getTimeoutSecond() {
        return timeoutSecond;
    }

    public void setTimeoutSecond(int timeoutSecond) {
        this.timeoutSecond = timeoutSecond;
    }
}
