package com.liu.weathermail.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;

import java.util.Arrays;

/**
 * Tencent Cloud Sms Sendsms
 *
 * @author Tencent Cloud
 */
@Slf4j
public class SendSmsUtil {

    /**
     * 发送短信
     */
    public static void sendSms(String templateId, String[] addressee, String[] args) {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        String secretId = weatherConfig.getString("secretId");
        String secretKey = weatherConfig.getString("secretKey");
        String sdkAppId = weatherConfig.getString("sdkAppId");
        String signName = weatherConfig.getString("signName");
        log.info("传送给腾讯云短信参数 templateId: {}，parameter: {}", templateId, Arrays.toString(args));

        try {
            //实例化一个认证对象，入参需要传入腾讯云账户密钥对secretId，secretKey。
            //SecretId、SecretKey 查询: https://console.cloud.tencent.com/cam/capi
            Credential cred = new Credential(secretId, secretKey);

            //实例化一个http选项，可选，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();

            httpProfile.setReqMethod("POST");
            //SDK有默认的超时时间，非必要请不要进行调整
            httpProfile.setConnTimeout(60);
            //指定接入地域域名，默认就近地域接入域名为 sms.tencentcloudapi.com ，也支持指定地域域名访问，例如广州地域的域名为 sms.ap-guangzhou.tencentcloudapi.com */
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            /* 非必要步骤:
             * 实例化一个客户端配置对象，可以指定超时时间等配置 */
            ClientProfile clientProfile = new ClientProfile();
            /* SDK默认用TC3-HMAC-SHA256进行签名
             * 非必要请不要修改这个字段 */
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);

            /* 实例化要请求产品(以sms为例)的client对象
             * 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，支持的地域列表参考 https://cloud.tencent.com/document/api/382/52071#.E5.9C.B0.E5.9F.9F.E5.88.97.E8.A1.A8 */
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);

            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 你可以直接查询SDK源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用IDE进行开发，可以方便跳转查阅各个接口和数据结构的文档说明 */
            SendSmsRequest req = new SendSmsRequest();

            /* 填充请求参数,这里request对象的成员变量即对应接口的入参
             * 你可以通过官网接口文档或跳转到request对象的定义处查看请求参数的定义
             * 基本类型的设置:
             * 帮助链接：
             * 短信控制台: https://console.cloud.tencent.com/smsv2
             * 腾讯云短信小助手: https://cloud.tencent.com/document/product/382/3773#.E6.8A.80.E6.9C.AF.E4.BA.A4.E6.B5.81 */

            // 应用 ID 可前往 [短信控制台](https://console.cloud.tencent.com/smsv2/app-manage) 查看
            req.setSmsSdkAppId(sdkAppId);

            // 使用 UTF-8 编码，签名信息可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-sign) 或 [国际/港澳台短信](https://console.cloud.tencent.com/smsv2/isms-sign) 的签名管理查看
            req.setSignName(signName);

            // 模板 ID 可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-template) 或 [国际/港澳台短信](https://console.cloud.tencent.com/smsv2/isms-template) 的正文模板管理查看
            req.setTemplateId(templateId);

            //模板参数: 模板参数的个数需要与 TemplateId 对应模板的变量个数保持一致，若无模板参数，则设置为空
            req.setTemplateParamSet(args);

            //下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]
            //示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号
            req.setPhoneNumberSet(addressee);

            //用户的 session 内容（无需要可忽略）: 可以携带用户侧 ID 等上下文信息，server 会原样返回
            req.setSessionContext("");

            //短信码号扩展号（无需要可忽略）: 默认未开通，如需开通请联系 [腾讯云短信小助手]
            req.setExtendCode("");

            //国际/港澳台短信 SenderId（无需要可忽略）: 国内短信填空，默认未开通，如需开通请联系 [腾讯云短信小助手]
            req.setSenderId("");

            //通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
            //返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应
            SendSmsResponse res = client.SendSms(req);

            //输出json格式的字符串回包
            String responseJson = SendSmsResponse.toJsonString(res);

            log.info("调用腾讯云发送短信 API 发送短信返回结果: {}", responseJson);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
    }

}