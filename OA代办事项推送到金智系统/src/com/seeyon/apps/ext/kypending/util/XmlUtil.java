package com.seeyon.apps.ext.kypending.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XmlUtil {

    public static String getNodeType(String xml, String param) {
        String type = "";
        Element root = rootElement(xml);
        Iterator it = root.elementIterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();// 一个Item节点
            Iterator it2 = element.elementIterator();
            while (it2.hasNext()) {
                Element el2 = (Element) it2.next();// 一个Item节点
                if (el2.getName().equals("n")) {
                    String i = el2.attributeValue("i");
                    if (param.equals(i)) {
                        Iterator el3 = el2.elementIterator();
                        while (el3.hasNext()) {
                            Element el4 = (Element) el3.next();
                            if (el4.getName().equals("s")) {
                                type = el4.attribute("j").getValue();
                            }
                        }
                    }
                }
            }
        }
        return type;
    }

    public static Map<String, String> getNodeTypeMap(String xml) {
        Element root = rootElement(xml);
        Map<String, String> map = new HashMap<String, String>();
        Iterator it = root.elementIterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();// 一个Item节点
            Iterator it2 = element.elementIterator();
            while (it2.hasNext()) {
                Element el2 = (Element) it2.next();// 一个Item节点
                if (el2.getName().equals("n")) {
                    String i = el2.attributeValue("i");
                    Iterator el3 = el2.elementIterator();
                    while (el3.hasNext()) {
                        Element el4 = (Element) el3.next();
                        if (el4.getName().equals("s")) {
                            String v4 = el4.attribute("j").getValue();
                            map.put(i, v4);
                        }
                    }
                }
            }
        }
        return map;
    }

    public static Element rootElement(String xml) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();// 指向根节点
        return root;
    }


}
