package com.shuyu.spring.template.core.sms.tx;


import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.shuyu.spring.template.annotation.LoginUser;
import com.shuyu.spring.template.config.GlobalConfig;
import com.shuyu.spring.template.module.user.entity.User;
import com.shuyu.spring.template.utils.CacheUtil;
import com.shuyu.spring.template.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/sms")
public class TxSmsController {

    @Autowired
    public TxSmsProperties txSmsProperties;

    @Autowired
    public SmsSingleSender ssender;

    @PostMapping("/getIdCode")
    public Object getAll(@LoginUser User user, @RequestBody String phoneNumbers) {
        if (user == null) {
            return ResponseUtil.badArgument("系统找不到该用户");
        }

        if (phoneNumbers == null || phoneNumbers.isEmpty()) {
            return ResponseUtil.badArgument("手机号码不能为空");
        }
        SmsSingleSenderResult result;
        String verifyCode;
        try {
            verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
            String[] params = {verifyCode, "" + txSmsProperties.getTimeoutSecond() / 60};
            result = ssender.sendWithParam("86", phoneNumbers,
                    txSmsProperties.getTemplateId(), params, txSmsProperties.getSmsSign(), "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            return ResponseUtil.serious();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            return ResponseUtil.serious();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            return ResponseUtil.serious();
        }
        if (result != null && result.result == 0) {
            CacheUtil.put(GlobalConfig.ID_CODE_KEY, user.getAccount(),
                    verifyCode, txSmsProperties.getTimeoutSecond(), txSmsProperties.getTimeoutSecond());
            return ResponseUtil.ok();
        } else {
            return ResponseUtil.serious();
        }

    }


}
