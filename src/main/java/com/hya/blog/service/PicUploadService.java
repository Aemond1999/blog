package com.hya.blog.service;

import com.hya.blog.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface PicUploadService {
   Result upload(MultipartFile file);
}
