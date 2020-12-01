package com.oay.controller;

import com.oay.utils.IDUtil;
import com.oay.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

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

    @RequestMapping(value = "/download")
    public String downloads(HttpServletResponse response, HttpServletRequest request) throws Exception {
        //Ҫ���ص�ͼƬ��ַ
        String path = request.getServletContext().getRealPath("/upload");
        String fileName = "1e2f83d80d13436997af47434c47da63_xiaolaos1.jpg";
        log.info(path + "====" + fileName);


        //1������response ��Ӧͷ
        response.reset(); //����ҳ�治����,���buffer
        response.setCharacterEncoding("UTF-8"); //�ַ�����
        response.setContentType("multipart/form-data"); //�����ƴ�������
        //������Ӧͷ
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

        File file = new File(path, fileName);
        //2�� ��ȡ�ļ�--������
        InputStream input = new FileInputStream(file);
        //3�� д���ļ�--�����
        OutputStream out = response.getOutputStream();

        byte[] buff = new byte[1024];
        int index = 0;
        //4��ִ�� д������
        while ((index = input.read(buff)) != -1) {
            out.write(buff, 0, index);
            out.flush();
        }
        out.close();
        input.close();
        return null;
    }
}
