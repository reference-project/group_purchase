package com.group.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.shop.common.CodeMsg;
import com.group.shop.common.Result;
import com.group.shop.entity.Goods;
import com.group.shop.service.GoodsService;
import com.group.shop.vo.GoodsInfo;
import com.group.shop.vo.GoodsUrl;

/**
 * @deprecated 商品操作控制层
 * @author Jin
 * @Data 2018/12/04
 */

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	//根据goodsId查询单个商品
	@GetMapping(value = "",produces = {"application/json;charset=UTF-8"})
	public Result<Object> getGoodsInfo(@RequestParam(name = "goodsId",required = true) int goodsId){
		Goods goods = goodsService.queryById(goodsId);
		if(goods != null) {
			return Result.success(goods);
		}else {
			return Result.error(CodeMsg.FAIL);
		}
	}
	
	@GetMapping(value= "/list",produces = {"application/json;charset=UTF-8"})
	public Result<List<GoodsUrl>> getGoodsInfoAndImg(@RequestParam(name = "setId",required = true) int setId){
		List<GoodsUrl> goodsUrls = goodsService.querySetInfoAndImgById(setId);
		if(!goodsUrls.isEmpty() && goodsUrls != null) {
			return Result.success(goodsUrls);
		}else {
			return Result.errorMsg("获取小套餐失败！");
		}
	}
	//根据goods添加单个商品信息
	@PostMapping(value = "",produces = {"application/json;charset=UTF-8"})
	public Result<Object> insertGoods(@RequestBody GoodsInfo goodsInfo){
			if(goodsService.insertSelective(goodsInfo)){
	            return Result.success(CodeMsg.SUCCESS);
	        }else{
	            return Result.error(CodeMsg.FAIL);
	        }
	}
	//根据修改后的goods更新单个商品信息
	@PutMapping(value = "",produces = {"application/json;charset=UTF-8"})
	public Result<Object> updateGoods(@RequestBody Goods goods){
			if(goodsService.updateByPrimarykeySelective(goods)){
	            return Result.success(CodeMsg.SUCCESS);
	        }else{
	            return Result.error(CodeMsg.FAIL);
	        }
	}
	
	//根据goodsId删除单个商品
	@DeleteMapping(value = "/goodslist/{goodsId}",produces = {"application/json;charset-UTF-8"})
	public Result<Object> deleteGoods(@PathVariable int goodsId){
		if(goodsService.deleteById(goodsId)) {
			return Result.success(CodeMsg.SUCCESS);
		}else {
			return Result.error(CodeMsg.FAIL);
		}
	}
	
	
}
