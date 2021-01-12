package com.xroad.springbootvalidatecodedemo.controller;

import com.xroad.springbootvalidatecodedemo.util.ValidateCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
public class ValidateCodeController {


    /**
     * http://localhost:8080/getCaptchaImg
     * @param request
     * @param response
     * @param session
     */
    //返回验证码图片
    @GetMapping("/getCaptchaImg")
    public void getCaptchaImg(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        try {

            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");
            ValidateCodeUtil validateCode = new ValidateCodeUtil();
            // getRandomCodeImage方法会直接将生成的验证码图片写入response
            validateCode.getRandomCodeImage(request, response);
            // System.out.println("session里面存储的验证码为："+session.getAttribute("JCCODE"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //验证码校验
    @GetMapping("/checkCaptcha")
    public boolean getCheckCaptcha(@RequestParam("code") String code, HttpSession session) {

        try {
            //toLowerCase() 不区分大小写进行验证码校验
            String sessionCode= String.valueOf(session.getAttribute("JCCODE")).toLowerCase();
            System.out.println("session里的验证码："+sessionCode);
            String receivedCode=code.toLowerCase();
            System.out.println("用户的验证码："+receivedCode);
            return !sessionCode.equals("") && !receivedCode.equals("") && sessionCode.equals(receivedCode);

        } catch (Exception e) {

            return false;
        }

    }

    // 生成验证码,返回的是 base64
    @GetMapping("/getCaptchaBase64")
    public Object getCaptchaBase64(HttpServletRequest request, HttpServletResponse response) {

        Map result = new HashMap();
        try {

            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");
            ValidateCodeUtil validateCode = new ValidateCodeUtil();
            // 返回base64
            String base64String = validateCode.getRandomCodeBase64(request, response);
            result.put("url", "data:image/png;base64," + base64String);
            result.put("message", "created successfull");
            //http://tool.chinaz.com/tools/imgtobase/  base64直接转为图片网站
            System.out.println("结果：" + result.get("url"));

        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }
}




