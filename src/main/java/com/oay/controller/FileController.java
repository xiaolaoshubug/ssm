package com.oay.controller;

import com.oay.utils.IDUtil;
import com.oay.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/*********************************************************
 * @Package: com.oay.controller
 * @ClassName: FileController.java
 * @Description： 文件上传
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2020-12-01
 *********************************************************/
@RestController
public class FileController {

    static final Logger log = Logger.getLogger(FileController.class);

    /*
     * file.transferTo 来保存上传的文件
     */
    @RequestMapping("/upload")
    public Result fileUpload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        String msg = "";
        //上传路径保存设置
        String path = request.getServletContext().getRealPath("/upload");
        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        //  uuidName
        String fileName = IDUtil.genId();
        try {
            //上传文件地址
            log.info("上传文件保存地址：" + realPath + "/" + fileName + "_" + file.getOriginalFilename());
            //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
            file.transferTo(new File(realPath + "/" + fileName + "_" + file.getOriginalFilename()));
            msg = "上传成功";
        } catch (IOException e) {
            msg = "上传失败";
            log.error(e);
            e.printStackTrace();
        }
        return Result.succ(msg);
    }

    /**
     * file.transferTo 批量上传
     */
    @RequestMapping("fileImgSave")
    public Result fileImgSave(@RequestParam("filename") CommonsMultipartFile[] files, HttpServletRequest request) {
        String msg = "";
        //  保存文件的路径
        String realPath = request.getServletContext().getRealPath("/upload");
        File path = new File(realPath);
        if (!path.exists()) {
            path.mkdir();
        }
        //  判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0) {
            //  循环获取file数组中得文件
            for (CommonsMultipartFile file : files) {
                //保存文件
                if (!file.isEmpty()) {
                    try {
                        //  上传文件
                        this.fileUpload(file, request);
                        msg = "上传成功";
                    } catch (IOException e) {
                        msg = "上传失败";
                        log.error(e);
                        e.printStackTrace();
                    }
                }
            }
        }
        return Result.succ(msg);
    }
}
