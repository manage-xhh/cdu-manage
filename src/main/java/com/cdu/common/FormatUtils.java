package com.cdu.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:   Format   
 * @Description: TODO(Date转String工具) 
 * @author:      Administrator
 * @date:        2017年4月4日 下午4:27:53  
 *
 * @Copyright:   2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class FormatUtils {
    private static SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    public static String dateToString(Date date){
        return formate.format(date);
    }
}
