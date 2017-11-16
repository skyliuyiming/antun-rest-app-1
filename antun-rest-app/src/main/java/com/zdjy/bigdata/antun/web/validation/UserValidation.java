package com.zdjy.bigdata.antun.web.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.domain.Area;
import com.zdjy.bigdata.antun.domain.Channel;
import com.zdjy.bigdata.antun.domain.Link;
import com.zdjy.bigdata.antun.domain.Page;
import com.zdjy.bigdata.antun.domain.Product;
import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.service.AreaService;
import com.zdjy.bigdata.antun.service.ChannelService;
import com.zdjy.bigdata.antun.service.LinkService;
import com.zdjy.bigdata.antun.service.PageService;
import com.zdjy.bigdata.antun.service.ProductService;
import com.zdjy.bigdata.antun.service.UserService;
import com.zdjy.bigdata.antun.web.model.UserAdd;

/**
 * 用户验证类
 * 
 * @author david
 * @create 2017年11月13日 上午11:56:08
 */
@Component
public class UserValidation extends BaseValidation {
	@Autowired
	private UserService userService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private PageService pageService;

	/**
	 * 保存验证
	 * 
	 * @param userAdd
	 * @return
	 */
	public String saveUserValidation(UserAdd userAdd) {
		// 验证参数格式
		if (StringUtils.isBlank(userAdd.getName()))
			return empty("姓名");
		if (!isName(userAdd.getName()))
			return "名字格式不正确，只能是2-5位中文";
		if (StringUtils.isBlank(userAdd.getPhone()))
			return empty("手机号");
		if (!isPhone(userAdd.getPhone()))
			return empty("手机号格式不正确，只能是11位数字");
		if (userAdd.getSex() == null)
			return empty("性别");
		if (userAdd.getSex() != 1 && userAdd.getSex() != 0)
			return "性别格式不正确，只能是0或者1";
		if (StringUtils.isBlank(userAdd.getBirth()))
			return empty("生日");
		if (!isBirth(userAdd.getBirth()))
			return "出生日期格式不正确，正确的例子是：1991-04-17";
		if (StringUtils.isBlank(userAdd.getIdNo()))
			return empty("身份证号");
		if (!isIdNo(userAdd.getIdNo()))
			return empty("身份证号格式不正确，前17位只能是数字，最后一位可以是数字和x和X");
		if (userAdd.getProvince() == null)
			return empty("省份");
		if (userAdd.getCity() == null)
			return empty("城市");
		if (userAdd.getTown() == null)
			return empty("区县");
		if (StringUtils.isBlank(userAdd.getChannelCode()))
			return empty("渠道码");
		if (StringUtils.isBlank(userAdd.getProductCode()))
			return empty("产品码");
		// 验证省市县
		Area town = areaService.findArea(userAdd.getTown());
		if (town == null)
			return "区县不存在";
		userAdd.setTownName(town.getName());
		if (town.getParentId().longValue() != userAdd.getCity().longValue())
			return "区县和城市对不上";
		Area city = areaService.findArea(userAdd.getCity());
		if (city == null)
			return "城市不存在";
		userAdd.setCityName(city.getName());
		if (city.getParentId().longValue() != userAdd.getProvince().longValue())
			return "城市和省份对不上";
		Area province = areaService.findArea(userAdd.getProvince());
		if (province == null)
			return "省份不存在";
		userAdd.setProvinceName(province.getName());
		// 验证渠道
		Channel channel = channelService.findByCode(userAdd.getChannelCode());
		if (channel == null)
			return "渠道不存在";
		if (channel.getStatus() == null || channel.getStatus() != 1)
			return "该渠道暂时不允许数据入库";
		userAdd.setChannelName(channel.getName());
		// 验证产品
		Product product = productService.findByCode(userAdd.getProductCode());
		if (product == null)
			return "产品不存在";
		if (product.getStatus() == null || product.getStatus() != 1)
			return "该产品暂时不允许数据入库";
		userAdd.setProductName(product.getName());
		// 去重
		User user = userService.findByPhone(userAdd.getPhone());
		if (user != null)
			return exist("手机号");
		return null;
	}

	/**
	 * 保存数据验证
	 * 
	 * @param userAdd
	 * @param linkCode
	 * @return
	 */
	public String saveUserValidation(UserAdd userAdd, String linkCode) {
		Link link = linkService.findByCode(linkCode);
		if (link == null)
			return notExist("链接");
		if (link.getStatus() == null || link.getStatus() != 1)
			return "链接已下线";
		// 验证渠道，补全渠道
		String channelCode = link.getChannelCode();
		if (StringUtils.isBlank(channelCode))
			return empty("渠道");
		Channel channel = channelService.findByCode(channelCode);
		if (channel == null)
			return "渠道不存在";
		if (channel.getStatus() == null || channel.getStatus() != 1)
			return "该渠道已下线";
		userAdd.setChannelCode(channelCode);
		userAdd.setChannelName(channel.getName());
		// 验证页面
		String pageCode = link.getPageCode();
		if (StringUtils.isBlank(pageCode))
			return empty("页面");
		Page page = pageService.findByCode(pageCode);
		if (page == null)
			return notExist("页面不存在");
		if (page.getStatus() == null || page.getStatus() != 1)
			return "页面已下线";
		// 验证产品，补全产品
		String productCode = page.getProductCode();
		if (StringUtils.isBlank(productCode))
			return empty("产品");
		Product product = productService.findByCode(productCode);
		if (product == null)
			return notExist("产品");
		if (product.getStatus() == null || product.getStatus() != 1)
			return "产品已下线";
		userAdd.setProductCode(productCode);
		userAdd.setProductName(product.getName());
		// 验证参数格式
		if (StringUtils.isBlank(userAdd.getName()))
			return empty("姓名");
		if (!isName(userAdd.getName()))
			return "名字格式不正确，只能是2-5位中文";
		if (StringUtils.isBlank(userAdd.getPhone()))
			return empty("手机号");
		if (!isPhone(userAdd.getPhone()))
			return empty("手机号格式不正确，只能是11位数字");
		if (userAdd.getSex() == null)
			return empty("性别");
		if (userAdd.getSex() != 1 && userAdd.getSex() != 0)
			return "性别格式不正确，只能是0或者1";
		if (StringUtils.isBlank(userAdd.getBirth()))
			return empty("生日");
		if (!isBirth(userAdd.getBirth()))
			return "出生日期格式不正确，正确的例子是：1991-04-17";
		if (StringUtils.isBlank(userAdd.getIdNo()))
			return empty("身份证号");
		if (!isIdNo(userAdd.getIdNo()))
			return empty("身份证号格式不正确，前17位只能是数字，最后一位可以是数字和x和X");
		if (userAdd.getProvince() == null)
			return empty("省份");
		if (userAdd.getCity() == null)
			return empty("城市");
		if (userAdd.getTown() == null)
			return empty("区县");
		if (StringUtils.isBlank(userAdd.getChannelCode()))
			return empty("渠道码");
		if (StringUtils.isBlank(userAdd.getProductCode()))
			return empty("产品码");
		// 验证省市县
		Area town = areaService.findArea(userAdd.getTown());
		if (town == null)
			return "区县不存在";
		userAdd.setTownName(town.getName());
		if (town.getParentId().longValue() != userAdd.getCity().longValue())
			return "区县和城市对不上";
		Area city = areaService.findArea(userAdd.getCity());
		if (city == null)
			return "城市不存在";
		userAdd.setCityName(city.getName());
		if (city.getParentId().longValue() != userAdd.getProvince().longValue())
			return "城市和省份对不上";
		Area province = areaService.findArea(userAdd.getProvince());
		if (province == null)
			return "省份不存在";
		userAdd.setProvinceName(province.getName());
		// 去重
		User user = userService.findByPhone(userAdd.getPhone());
		if (user != null)
			return exist("手机号");
		return null;
	}

	private static final String NAME_PATTERN = "[\\u4e00-\\u9fa5]{2,5}";

	/**
	 * 验证是否是姓名
	 * 
	 * @param name
	 * @return
	 */
	public boolean isName(String name) {
		return name.matches(NAME_PATTERN);
	}

	private static final String PHONE_PATTERN = "\\d{11}";

	/**
	 * 验证是否是手机号
	 * 
	 * @param phone
	 * @return
	 */
	public boolean isPhone(String phone) {
		return phone.matches(PHONE_PATTERN);
	}

	private static final String BIRTH_PATTERN = "\\d{4}-\\d{2}-\\d{2}";

	/**
	 * 验证是否是生日
	 * 
	 * @param idNo
	 * @return
	 */
	public boolean isBirth(String birth) {
		return birth.matches(BIRTH_PATTERN);
	}

	private static final String IDNO_PATTERN = "\\d{17}[0-9xX]";

	/**
	 * 验证是否是身份证号
	 * 
	 * @param idNo
	 * @return
	 */
	public boolean isIdNo(String idNo) {
		return idNo.matches(IDNO_PATTERN);
	}

}
