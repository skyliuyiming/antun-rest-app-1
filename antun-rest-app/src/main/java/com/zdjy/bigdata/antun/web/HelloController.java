package com.zdjy.bigdata.antun.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zdjy.bigdata.antun.domain.Hello;
import com.zdjy.bigdata.antun.service.HelloService;
import com.zdjy.bigdata.antun.web.model.HelloAdd;
import com.zdjy.bigdata.antun.web.model.HelloUpdate;
import com.zdjy.bigdata.antun.web.response.BaseResponse;
import com.zdjy.bigdata.antun.web.response.PageMap;
import com.zdjy.bigdata.antun.web.validation.HelloValidation;

/**
 * hello接口
 * @author david
 * @create 2017年11月13日 上午10:24:25
 */
@RestController
@RequestMapping("/hellos")
public class HelloController extends BaseResponse{
	@Autowired
	private HelloService helloService;
	@Autowired
	private HelloValidation helloValidation;
	/**
	 * 查询全部
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public BaseResponse findAll() {
		List<Hello> hellos=helloService.findAll();
		return successModel("查询全部成功").data(hellos);
	}
	/**
	 * 保存
	 * @param helloAdd
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.POST)
	public BaseResponse saveHello(HelloAdd helloAdd) {
		String msg=helloValidation.saveHelloValidation(helloAdd);
		if(msg!=null)
			return errorModel(msg);
		int i=helloService.saveHello(helloAdd);
		return successModel("新增成功，数量："+i);
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public BaseResponse deleteHello(@PathVariable Long id) {
		int i=helloService.deleteHello(id);
		return successModel("删除成功，数量："+i);
	}
	/**
	 * 修改
	 * @param id
	 * @param helloUpdate
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public BaseResponse updateHello(@PathVariable Long id,HelloUpdate helloUpdate) {
		String msg=helloValidation.updateHelloValidation(helloUpdate);
		if(msg!=null)
			return errorModel(msg);
		int i=helloService.updateHello(id,helloUpdate);
		return successModel("修改成功，数量："+i);
	}
	/**
	 * id查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public BaseResponse getHello(@PathVariable Long id) {
		Hello hello=helloService.getHello(id);
		return successModel("id查询成功").data(hello);
	}
	/**
	 * 分页查询
	 * @param offset
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public BaseResponse getByPage(Integer offset,Integer limit) {
		PageMap map=helloService.getByPage(offset,limit);
		return successModel("分页查询成功").data(map);
	}
}
