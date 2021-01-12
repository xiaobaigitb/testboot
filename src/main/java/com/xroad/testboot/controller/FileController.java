package com.xroad.testboot.controller;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author : Xroad
 * @CreateTime : 2021/1/6
 * @Description :
 **/
@Controller
@RequestMapping("/file")
public class FileController {


    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    //以下三个配置项都可改为 录入在 配置文件 或者是 数据库参数配置表内

    private  static String FILE_PATH = "F:\\Users\\Administrator\\Desktop\\IDEA\\testboot\\src\\main\\resources\\static\\upload";

    private  static String IMG_VISIT_PATH ="/upload";

    private  static String IMG_HOST="localhost:8089";
    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("upload")
    public String upload(@RequestParam("myFile") MultipartFile file) {
        log.info("file name:" + file.getOriginalFilename());
        log.info("file type:" + file.getContentType());
        log.info("file size:" + file.getSize());
        //    设置新的文件名
        String newFileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "");
        //    获得文件的扩展名
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        //    获得新的文件名
        String newFileName = newFileNamePrefix + "." + extension;

        String FILE_PATH = "F:\\Users\\Administrator\\Desktop\\IDEA\\testboot\\src\\main\\resources\\static\\upload";
        //  FILE_PATH : 文件的保存路径
        String filePath=FILE_PATH +"/" + newFileName;
        try {
            log.info("开始上传文件【{}】...", newFileName);
            File dest = new File(filePath);
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        log.info("文件【{}】上传成功...", newFileName);
        return IMG_HOST+IMG_VISIT_PATH+"/"+newFileName;
    }
    /**
     * 文件下载
     * @param fileName
     * @param response
     * @throws IOException
     */
    @GetMapping("download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        // String fileName = "测试文件上传.txt";
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(FILE_PATH+ "/"
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
