package com.shuyu.spring.template.core.wx;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.shuyu.spring.template.core.shiro.JWTUtil;
import com.shuyu.spring.template.core.wx.bean.InMsgEntity;
import com.shuyu.spring.template.module.user.entity.User;
import com.shuyu.spring.template.module.user.service.IUserService;
import com.shuyu.spring.template.utils.ResponseUtil;
import com.shuyu.spring.template.utils.UserUtils;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 鉴权服务
 */
@RestController
@RequestMapping("/wx/auth")
public class WxAuthController {

    private final Log logger = LogFactory.getLog(WxAuthController.class);

    @Autowired
    public WxMaService wxService;


    @Autowired
    public IUserService userService;


    /**
     * 获取OpenId
     *
     * @param wxCode 小程序登录Code
     */
    @PostMapping("getOpenId")
    public Object getOpenId(@RequestParam String wxCode) {
        if (StringUtils.isEmpty(wxCode)) {
            return ResponseUtil.badArgument("wxCode 不能为空");
        }
        String openId;
        try {
            //获取OpenId
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(wxCode);
            openId = result.getOpenid();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail(401, "OpenId 获取失败 " + e.getMessage());
        }


        //创建临时用户
        User user = userService.getByAccount(openId);
        if (user == null) {
            user = UserUtils.createWxTmpUser(openId);
            userService.save(user);
        }

        Map<Object, Object> result = new HashMap<>();
        String token = JWTUtil.createToken(user.getAccount());
        result.put("token", token);
        result.put("openId", openId);
        return ResponseUtil.ok(result);
    }

    /**
     * 公众号登记和推送接口
     * @param msg
     * @return
     */
    @PostMapping(value = "sign", produces = { "application/xml;charset=UTF-8" })
    @ResponseBody
    //public Object signApi(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr) {
    public String signApi(@RequestBody InMsgEntity msg) {
        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
        item.setDescription("点击提交信息，转发无效。");
        item.setPicUrl("picUrl");
        item.setTitle("xxxx");
        item.setUrl("xxxxxxxx" + msg.getFromUserName());

        WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS()
                .fromUser(msg.getToUserName())
                .toUser(msg.getFromUserName())
                .addArticle(item)
                .build();

        return m.toXml();
    }
}
