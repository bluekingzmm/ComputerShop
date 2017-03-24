/**
 * 
 */
package com.zmm.service;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.zmm.dto.Computer;
import com.zmm.dto.ShopCart;

/**
 * @author Administrator
 *
 */
public class ShopCartService {

	/*
	 * 添加商品id 添加购物车信息，0添加失败，1添加商品成功，2商品已经存在
	 */
	@SuppressWarnings("unchecked")
	public StringBuffer addShopCart(int comId, HttpSession session) {
		Computer com = null;
		ComputerService coms = null;
		StringBuffer sbf = new StringBuffer();
		try {
			// 获得电脑id得到信息
			coms = new ComputerService();
			com = coms.get(comId);
			// 封装购物车信息
			ShopCart sc = new ShopCart();
			com.setBrand(com.getBrand());
			sc.setCom(com);
			sc.setCom_id(com.getCom_id());
			// 默认购买数量为1
			sc.setCount(1);
			// 将购物车存放在set集合接口中
			Set<ShopCart> shopCart = null;
			// 判断购物车是否为空！
			if (session.getAttribute("ShopCart") == null) {
				// 创建一个购物车
				shopCart = new HashSet<ShopCart>();
				shopCart.add(sc);// 添加到set集合中
				// 将添加的购物车集合放到session中
				session.setAttribute("ShopCart", shopCart);
				return sbf.append("宝贝已经添加到购物车中，请稍后在我的购物车查看...");
			} else {
				// 购物车不为空，判断是否相同，用Hashset集合，重写equale和hashcode方法
				shopCart = (HashSet<ShopCart>) session.getAttribute("ShopCart");
				if (shopCart.add(sc)) {
					return sbf.append("宝贝已经添加到购物车中，请稍后在我的购物车查看...");
				} else {

					return sbf.append("宝贝已经在购物车中...");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return sbf.append("系统繁忙，添加失败！...");
	}

	/*
	 * 得到购物车信息(集合)
	 */
	@SuppressWarnings("unchecked")
	public HashSet<ShopCart> getShopCart(HttpSession session) {
		if (session.getAttribute("ShopCart") != null) {
			return (HashSet<ShopCart>) session.getAttribute("ShopCart");
		} else {
			return null;
		}

	}
	public ShopCart getById(HttpSession session,int id){
		Set<ShopCart> setShopCart = getShopCart(session);
		if (setShopCart != null) {
			for (ShopCart shopCart : setShopCart) 
				if(shopCart.getCom_id()==id)
					return shopCart;
				
				}
			
		return null;

	
	}

	/*
	 * 根据电脑id进行删除
	 */
	@SuppressWarnings("unchecked")
	public void deleteShopCart(HttpSession session, int id) {
		Set<ShopCart> shopCartSet = (HashSet<ShopCart>) session.getAttribute("ShopCart");
		ShopCart sc = new ShopCart();
		sc.setCom_id(id);
		shopCartSet.remove(sc);

		session.setAttribute("ShopCart", shopCartSet);
	}

	/*
	 * 清除购物车
	 */
	public void clearShopCart(HttpSession session) {
		if (session.getAttribute("ShopCart") != null)
			session.removeAttribute("ShopCart");
	}

	/*
	 * 修改购买数量
	 */
	public boolean updateCount(int count, int comId, HttpSession session) {
		Set<ShopCart> setShopCart = getShopCart(session);
		boolean result = false;
		if (setShopCart != null) {
			for (ShopCart shopCart : setShopCart) {
				if(shopCart.getCom_id()==comId){
					shopCart.setCount(count);
					result = true;
				}
			}
			
//			Iterator<ShopCart> iterator = setShopCart.iterator();
//			while (iterator.hasNext()) {
//				ShopCart sc = (ShopCart) iterator.next();
//				if (sc.getCom().getCom_id() == comId) {
//					sc.setCount(count);
//					result = true;
//				}
//			}
		}

		return result;
	}

}
