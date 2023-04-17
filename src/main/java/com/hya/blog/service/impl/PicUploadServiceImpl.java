package com.hya.blog.service.impl;

import com.google.gson.Gson;
import com.hya.blog.common.pojo.MyUserDetails;
import com.hya.blog.enums.HttpCodeEnum;
import com.hya.blog.service.PicUploadService;
import com.hya.blog.service.UserService;
import com.hya.blog.utils.PathUtil;
import com.hya.blog.utils.Result;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
@ConfigurationProperties(prefix = "qiniu")
public class PicUploadServiceImpl implements PicUploadService {
    @Autowired
    UserService userService;
    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg",
            ".jpeg", ".png"};
    @Value("${qiniu.accessKeyId}")
    private String accessKey;
    @Value("${qiniu.secretKey}")
    private String secretKey;
    @Value("${qiniu.bucket}")
    private String bucket;
    @Value("${qiniu.urlPrefix}")
    private String urlPrefix;

    @Override
    public Result upload(MultipartFile img) {
        boolean flag = false;
        for (String s : IMAGE_TYPE) {
            if (img.getOriginalFilename().endsWith(s)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return Result.failResult(HttpCodeEnum.UNSUPPORTED_MEDIA_TYPE.getCode(),HttpCodeEnum.UNSUPPORTED_MEDIA_TYPE.getMsg());
        }
        //构造一个带指定Region对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(cfg);
        //设置文件名称
        String key = PathUtil.generateFilePath(img.getOriginalFilename());

        try {
            InputStream inputStream = img.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                //将图片地址保存到User表中
                UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
                Long id = userDetails.getUserDO().getId();
                userService.updateAvatar(urlPrefix + key, id);
                return  Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), urlPrefix + key);

            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

     return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
    }


}
