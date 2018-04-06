package com.framework.base.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.framework.base.model.Dict;


/**
 * 数据字典常用方法
 *
 */
public abstract class DictUtils  {
	
	/**
     * 根据数据字典编码返回 字典返回的是 name value list集合
     * @param code
     * @return
     */
    public static List<Map<String,String>> getDictListByCode(String code){
        List<Dict> dictBeanListByCode = getDictBeanListByCode(code);
        return getDictNameValue(dictBeanListByCode);
    }

    
    /**
     *   字段对象转化为name value
     * @param dictBeanListByCode
     * @return
     */
    public static List<Map<String,String>> getDictNameValue(List<Dict> dictBeanListByCode) {
        List<Map<String,String>> mapList = new ArrayList();
        for(Dict dict : dictBeanListByCode){
            Map<String,String> map = new HashMap();
            map.put("name",dict.getDictName());
            map.put("value",dict.getDictCode());
            mapList.add(map);
        }
        return mapList;
    }
    
    /**
     * 根据数据字典编码返回 字典返回的是listBean集合
     * @param code
     * @return
     */
    public static List<Dict> getDictBeanListByCode(String code){
        Map<String, Dict> dictionaryMap = loadDict();
        if(dictionaryMap.containsKey(code)){
        	Dict dictionary = dictionaryMap.get(code);
            List<Dict> dictList = dictionary.getDictList();
            return dictList;
        }
        return Collections.emptyList();
    }
    
    /**
	 * 加载数据字典，后面存放缓存
	 */
	public static Map<String, Dict> loadDict(){
		Dict dict = new Dict();
		//从数据库查询数据字典信息
		List<Dict> dicts = new ArrayList<Dict>();
		List<Dict> rootDicts = new ArrayList<Dict>();
		for(Dict d : dicts){
			if("0".equals(d.getParentId())){
				rootDicts.add(d);
			}
		}
		
		if(rootDicts.size() == 0){
			return null ;
		}
	
		for(Dict d : rootDicts){
			getDictByDigui2(d, dicts);
		}
	
		Map<String, Dict> map = new TreeMap<String, Dict>();
		for (Dict d : dicts) {
			if ("0".equals(d.getParentId()))
				map.put(d.getDictCode(), d);
		}
		return map;
	}
	
	/**
	 * 递归加载
	 * @param item
	 */
	private static void getDictByDigui2(Dict item, final List<Dict> dicts){
		List<Dict> children = new ArrayList<Dict>();
		for(Dict d : dicts) {
			if(item.getDictCode().equals(d.getParentId())){
				children.add(d);
			}
		}
		//从数据库查询数据字典信息
		List<Dict> dictMapList = new ArrayList<Dict>();
		item.setDictList(dictMapList);

		item.setChildren(children);
		if(children.size() == 0){
			return ;
		}

		for(Dict d : children){
			getDictByDigui2(d, dicts);
		}
	}
   
}
