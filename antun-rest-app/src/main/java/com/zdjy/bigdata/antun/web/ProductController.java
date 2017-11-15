package com.zdjy.bigdata.antun.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zdjy.bigdata.antun.domain.Product;
import com.zdjy.bigdata.antun.service.ProductService;
import com.zdjy.bigdata.antun.web.model.ProductAdd;
import com.zdjy.bigdata.antun.web.model.ProductUpdate;
import com.zdjy.bigdata.antun.web.response.BaseResponse;
import com.zdjy.bigdata.antun.web.validation.ProductValidation;

/**
 * 产品管理
 * @author david
 * @create 2017年11月15日 上午11:31:24
 */
@RestController
@RequestMapping("/products")
public class ProductController extends BaseResponse{
	@Autowired
	private ProductValidation productValidation;
	@Autowired
	private ProductService productService;
	/**
	 * 查询全部
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public BaseResponse findAll() {
		List<Product> products=productService.findAll();
		return successModel("查询全部成功").data(products);
	}
	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/{id}/updateStatus",method=RequestMethod.PUT)
	public BaseResponse updateStatus(@PathVariable Long id,Integer status) {
		String msg=productValidation.updateStatusValidation(status);
		if(msg!=null)
			return errorModel(msg);
		int i=productService.updateStatus(id,status);
		return successModel("修改状态成功，数量："+i);
	}
	/**
	 * 保存
	 * @param productAdd
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.POST)
	public BaseResponse saveProduct(ProductAdd productAdd) {
		String msg=productValidation.saveProductValidation(productAdd);
		if(msg!=null)
			return errorModel(msg);
		int i=productService.saveProduct(productAdd);
		return successModel("新增成功，数量："+i);
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public BaseResponse deleteProduct(@PathVariable Long id) {
		int i=productService.deleteProduct(id);
		return successModel("删除成功，数量："+i);
	}
	/**
	 * id查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public BaseResponse getProduct(@PathVariable Long id) {
		Product product=productService.getProduct(id);
		return successModel("id查询成功").data(product);
	}
	/**
	 * 修改
	 * @param id
	 * @param productUpdate
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public BaseResponse updateProduct(@PathVariable Long id,ProductUpdate productUpdate) {
		String msg=productValidation.updateProductValidation(productUpdate);
		if(msg!=null)
			return errorModel(msg);
		int i=productService.updateProduct(id,productUpdate);
		return successModel("修改成功，数量："+i);
	}
	/**
	 * 状态查询
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/status/{status}",method=RequestMethod.GET)
	public BaseResponse findByStatus(@PathVariable Integer status) {
		List<Product> products=productService.findByStatus(status);
		return successModel("状态查询成功").data(products);
	}
}
