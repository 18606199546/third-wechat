package com.wechat.common;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO 类描述
 *
 * @author: wx
 * @date: 2022/2/9
 */
public class WxUtil {
    /**
     * @desc ：2.解析微信发来的请求（xmlStr）,获取请求参数
     *
     * @param xmlStr
     * @return
     * @throws Exception Map<String,String>
     */
    public static Map<String, String> parseXml(String xmlStr) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();


        //1.将字符串转为Document
        Document document = DocumentHelper.parseText(xmlStr);

        //2.获取根元素的所有子节点
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        //3.遍历所有子节点
        for (Element e : elementList){
            map.put(e.getName(), e.getText());
        }

        return map;
    }
}
