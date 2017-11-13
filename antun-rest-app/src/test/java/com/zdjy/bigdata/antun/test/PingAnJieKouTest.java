package com.zdjy.bigdata.antun.test;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class PingAnJieKouTest {
	@Test
	public void test001() {
		try {
			RestTemplate template=new RestTemplate();
			StringBuilder url=new StringBuilder("http://47.94.250.65/pinganjiekou/user/add?");
			url.append("channelCode=afangti_zhangrui_001&");
			url.append("channelToken=26246952-113-11343-3510060-1131-46-46-91114&");
			url.append("productCode=PA000000CXSF-CXWY-01&");
			url.append("userName=中国&");
			url.append("userSex=1&");
			url.append("userBirth=1991-04-08&");
			url.append("userPhone=18851632234&");
			url.append("userIdNo=32012514785469187x&");
			url.append("province=110000&");
			url.append("city=110100&");
			url.append("town=110101&");
			PingAnJiekouResponse pingAnJiekouResponse = template.getForObject(url.toString(), PingAnJiekouResponse.class);
			System.out.println(pingAnJiekouResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
