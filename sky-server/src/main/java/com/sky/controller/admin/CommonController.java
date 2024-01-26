package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Api(tags = "CommonController")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    /**
     * File Upload
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation(value = "file_upload")
    public Result<String> upload(MultipartFile file){
        log.info("file uploading, {}", file);
        //Original File Name
        String originalFilename = file.getOriginalFilename();
        //Get File's Extension Name
        String extensionName = originalFilename.substring(originalFilename.lastIndexOf("."));
        //Integrate New File Name
        String objectName = UUID.randomUUID().toString() + extensionName;
        //File's Request Path
        try {
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("File Upload Failed: {}", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
