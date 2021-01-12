package com.xroad.testboot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.jodconverter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *URL:  https://blog.csdn.net/qq_35387940/article/details/108752041
 * 这篇实例，实现在线预览WORD文档，分两步：
 * 一. 安装OpenOffice
 * 二.写点小代码
 * 百度网盘链接：
 * https://pan.baidu.com/s/17y5O7IqkB0YxA6tWdjJNvA
 * 提取码：7u23
 * 然后我们在浏览器访问接口  http://localhost:8089/testPreview  ，可以看到预览没问题
 */
@Controller
public class OpenOfficeController {

    @Value("${jodconverter.test.test-file-path}")
    private String TEST_FILE_PATH;
    @Value("${jodconverter.test.save-path}")
    private String SAVE_PATH;

    @Autowired
    private DocumentConverter converter;  //用于转换

    @ResponseBody
    @RequestMapping("testPreview")
    public void toPdfFile(HttpServletResponse response) {
        File file = new File(TEST_FILE_PATH);//需要转换的文件
        try {
            File newFile = new File(SAVE_PATH);//转换之后文件生成的地址,pdf文件生成保存的路径
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
            String fileName="JCccc"+UUID.randomUUID().toString().replaceAll("-","").substring(0,6);
            String fileType=".pdf"; //pdf文件后缀
            String newFileMix=SAVE_PATH+fileName+fileType;  //将这三个拼接起来,就是我们最后生成文件保存的完整访问路径了

            //文件转化
            converter.convert(file).to(new File(newFileMix)).execute();
            //使用response,将pdf文件以流的方式发送的前端浏览器上
            ServletOutputStream outputStream = response.getOutputStream();
            InputStream in = new FileInputStream(new File(newFileMix));// 读取文件
            int i = IOUtils.copy(in, outputStream);   // copy流数据,i为字节数
            in.close();
            outputStream.close();
            System.out.println("流已关闭,可预览,该文件字节大小："+i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}