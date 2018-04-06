package com.framework.base.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Random;

public class StringUtil {
	public static final String DEFAULT_SEPARATOR = ",";
	
	
	public static String getSncode(int len){
		StringBuffer buf = new StringBuffer("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
		buf.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
//		buf.append(",~,@,#,$,%,^,&,*,(,),_,+,|,`,.");
		buf.append(",1,2,3,4,5,6,7,8,9,0");
		String[] arr = buf.toString().split(",");
		
		StringBuffer b = new StringBuffer();
		java.util.Random r;
		int k ;
		for(int i=0;i<len;i++){
			 r = new java.util.Random();
			 k = r.nextInt();
			 b.append(String.valueOf(arr[Math.abs(k % 61)]));//字符数-1
		}
		
		return b.toString();
	}
	
	/**
	 * 将一个字符串数组按照指定的分隔符分割后，返回一个拼接的字符串.
	 * @param strs 一个字符串数组
	 * @param separator 拼接为字符串时，元素之间的分隔符
	 * @return
	 */
	public static String join(String[] strs, String separator) {
		StringBuilder strRet = new StringBuilder("");
		if (strs == null) return strRet.toString();
		for (String str : strs) {
			strRet.append(str + separator);
		}
		return strRet.substring(0, strRet.length() - separator.length());
	}
	
	/**
	 * 将字符串中按照separator分割的字串加上''
	 * @param text
	 * @param separator
	 * @return
	 */
	public static String insertChar(String text, String separator) {
		String[] parts = text.split(separator);
		StringBuilder sb = new StringBuilder();
		for (String part : parts) {
			sb.append("'" + part + "',");
		}
		
		return sb.deleteCharAt(sb.length() - 1).toString();
	}
	
	
	/**
	 * 判断指定字符串是否为空或者空字符串
	 * @param s 字符串引用
	 * @return	如果S 为空或者空字符串则返回true,否则为false.
	 */
	public static boolean isNullOrEmpty(String s){
		if(null != s){
			return (s.length() == 0);
		}
		return true;
	}
	
	/**
	 * 判断指定字符串是否为空引用、空字符串或者包含空白字符的字符串
	 * @param s 字符串引用
	 * @return	如果S 为空或者空字符串或者只包含空白字符的字符串则返回true,否则为false.
	 */
	public static boolean isNullOrBlank(String s){
		if((s == null) || (s.length() == 0) || (s.trim().length() == 0)){
			return true;
		}
		return false;
	}
	
	/**
     * 过滤掉超过3个字节的UTF8字符
     * @param text
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
    	if (text == null) return null; 
        byte[] bytes = text.getBytes("utf-8");
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        int i = 0;
        while (i < bytes.length) {
            short b = bytes[i];
            if (b > 0) {
                buffer.put(bytes[i++]);
                continue;
            }

            b += 256; // 去掉符号位

            if (((b >> 5) ^ 0x6) == 0) {
                buffer.put(bytes, i, 2);
                i += 2;
            } else if (((b >> 4) ^ 0xE) == 0) {
                buffer.put(bytes, i, 3);
                i += 3;
            } else if (((b >> 3) ^ 0x1E) == 0) {
                i += 4;
            } else if (((b >> 2) ^ 0x3E) == 0) {
                i += 5;
            } else if (((b >> 1) ^ 0x7E) == 0) {
                i += 6;
            } else {
                buffer.put(bytes[i++]);
            }
        }
        buffer.flip();
        return new String(buffer.array(), "utf-8");
    }
    
    /**
     * 随机生成6位字母或数字的验证码
     * @return
     */
    public static String getRandStr(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int a = Math.abs((new Random()).nextInt(9));// 产生0~57的随机数
            if (a <= 9) {// 将0~9转为char的0~9
                sb.append((char) (a + 48));
            } else if (a < 33) {// 将10~33转为char的A~Z
                if((a + 55) == 79 || (a + 55) == 73){
                    sb.append((char) (a + 63));
                }else{
                    sb.append((char) (a + 55));
                }
            } else {// 将33~57转为char的a~z
                sb.append((char) (a + 63));
            }
        }
        return sb.toString();
	}
    
    /**
     * 随机生成4位字母或数字的验证码
     * @return
     */
    public static String get4RandStr(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int a = Math.abs((new Random()).nextInt(9));// 产生0~57的随机数
            if (a <= 9) {// 将0~9转为char的0~9
                sb.append((char) (a + 48));
            } else if (a < 33) {// 将10~33转为char的A~Z
                if((a + 55) == 79 || (a + 55) == 73){
                    sb.append((char) (a + 63));
                }else{
                    sb.append((char) (a + 55));
                }
            } else {// 将33~57转为char的a~z
                sb.append((char) (a + 63));
            }
        }
        return sb.toString();
	}
    
    /**
     * 移除字符串中包含的Emoji表情
     * @return
     */
    public static String removeEmoji(String content) {
    	if (content != null) {
			return content.replaceAll("[^\\u0000-\\uFFFF]", "");
		}
    	return null;
    }
    
    
    // 按指定的长度下，考虑字符的全角半角，且当舍掉最后半个汉字后，长度不足的情况下是否要在右边补空格。  
 	public static String subByStrByte(String value, int length) {  
 	    String valueTemp = "";  
 	    // 指定的长度下，考虑字符的全角半角，最后的汉字。  
 	    if (value.getBytes().length > length) {  
 	        for (char c : value.toCharArray()) {  
 	            if (valueTemp.getBytes().length <= length) {  
 	                valueTemp += c;  
 	  
 	                if (valueTemp.getBytes().length == length) {  
 	                    break;  
 	  
 	                } else if (valueTemp.getBytes().length > length) {  
 	                    char[] charTemp = valueTemp.toCharArray();  
 	                    valueTemp = "";  
 	  
 	                    for (int i = 0; i < charTemp.length; i++) {  
 	                        valueTemp += charTemp[i];  
 	                    }  
 	  
 	                    break;  
 	                }  
 	            }  
 	        }  
 	        value = valueTemp;  
 	    }  
 	    return value;  
 	}  
    
   /* public static void main(String[] args) {
    	System.out.println(getRandStr());
	}*/
}
