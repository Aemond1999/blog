package com.hya.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PathUtil {
    public static  String generateFilePath(String fileName){
        // 2020/3/1UUID.type
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        String date=dateFormat.format(new Date());
        String UUID= java.util.UUID.randomUUID().toString().replaceAll("-","");
        int index=fileName.lastIndexOf('.');
        String fileType=fileName.substring(index);
        return new StringBuilder().append(date).append(UUID).append(fileType).toString();

    }
}
