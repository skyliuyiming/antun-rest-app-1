package com.zdjy.bigdata.antun.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.zdjy.bigdata.antun.domain.User;

/**
 * 平安外发类
 * @author david
 * @create 2017年11月14日 下午2:53:48
 */
@Component
public class PingAnSending {
	private Logger logger = LoggerFactory.getLogger(PingAnSending.class);
	@Value("${pingan.sending.url}")
	private String pingAnSendingUrl;
	@Value("${pingan.sending.channelCode}")
	private String pingAnSendingChannelCode;
	@Value("${pingan.sending.channelToken}")
	private String pingAnSendingChannelToken;
	private RestTemplate restTemplate=new RestTemplate();
	public Response send(User user) {
		try {
			StringBuilder url=new StringBuilder(pingAnSendingUrl+"?");
			url.append("channelCode="+pingAnSendingChannelCode+"&");
			url.append("channelToken="+pingAnSendingChannelToken+"&");
			url.append("productCode="+user.getProductCode()+"&");
			url.append("userName="+user.getName()+"&");
			url.append("userSex="+user.getSex()+"&");
			url.append("userBirth="+user.getBirth()+"&");
			url.append("userPhone="+user.getPhone()+"&");
			url.append("userIdNo="+user.getIdNo()+"&");
			url.append("province="+user.getProvince()+"&");
			url.append("city="+user.getCity()+"&");
			url.append("town="+user.getTown()+"&");
			String sendUrl=url.toString();
			logger.debug("【sending】:"+url);
			Response pingAnJiekouResponse = restTemplate.getForObject(sendUrl, Response.class);
			logger.debug("【response】:"+pingAnJiekouResponse.toString());
			return pingAnJiekouResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public class Response{
		private Integer code;
		private String result;
		public Integer getCode() {
			return code;
		}
		public void setCode(Integer code) {
			this.code = code;
		}
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
		@Override
		public String toString() {
			return String.format("code=%d,result=%s",code,result);
		}
	}
}
