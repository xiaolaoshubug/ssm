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
 * @Description�� �ļ��ϴ�
 * -----------------------------------
 * @author��ouay
 * @Version��v1.0
 * @Date: 2020-12-01
 *********************************************************/
@RestController
public class FileController {

    static final Logger log = Logger.getLogger(FileController.class);

    /*
     * file.transferTo �������ϴ����ļ�
     */
    @RequestMapping("/upload")
    public Result fileUpload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        String msg = "";
        //�ϴ�·����������
        String path = request.getServletContext().getRealPath("/upload");
        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        //  uuidName
        String fileName = IDUtil.genId();
        try {
            //�ϴ��ļ���ַ
            log.info("�ϴ��ļ������ַ��" + realPath + "/" + fileName + "_" + file.getOriginalFilename());
            //ͨ��CommonsMultipartFile�ķ���ֱ��д�ļ���ע�����ʱ��
            file.transferTo(new File(realPath + "/" + fileName + "_" + file.getOriginalFilename()));
            msg = "�ϴ��ɹ�";
        } catch (IOException e) {
            msg = "�ϴ�ʧ��";
            log.error(e);
            e.printStackTrace();
        }
        return Result.succ(msg);
    }

    /**
     * file.transferTo �����ϴ�
     */
    @RequestMapping("fileImgSave")
    public Result fileImgSave(@RequestParam("filename") CommonsMultipartFile[] files, HttpServletRequest request) {
        String msg = "";
        //  �����ļ���·��
        String realPath = request.getServletContext().getRealPath("/upload");
        File path = new File(realPath);
        if (!path.exists()) {
            path.mkdir();
        }
        //  �ж�file���鲻��Ϊ�ղ��ҳ��ȴ���0
        if (files != null && files.length > 0) {
            //  ѭ����ȡfile�����е��ļ�
            for (CommonsMultipartFile file : files) {
                //�����ļ�
                if (!file.isEmpty()) {
                    try {
                        //  �ϴ��ļ�
                        this.fileUpload(file, request);
                        msg = "�ϴ��ɹ�";
                    } catch (IOException e) {
                        msg = "�ϴ�ʧ��";
                        log.error(e);
                        e.printStackTrace();
                    }
                }
            }
        }
        return Result.succ(msg);
    }
}
