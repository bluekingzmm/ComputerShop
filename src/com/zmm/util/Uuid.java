/**
 * 
 */
package com.zmm.util;

import java.util.UUID;

/**
 * @author Administrator 
    
 * replace()
 * 方法用于在字符串中用一些字符替换另一些字符，或替换一个与正则表达式匹配的子串。
 * toUpperCase()描述说明:该方法返回指定的char值的大写形式
 *
 */
public class Uuid {

	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
