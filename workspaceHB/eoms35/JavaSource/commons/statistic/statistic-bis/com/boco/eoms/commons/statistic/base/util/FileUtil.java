package com.boco.eoms.commons.statistic.base.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.tools.MappingTool;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

import com.boco.eoms.commons.statistic.base.reference.StaticMethod;

public class FileUtil {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String path = "E:/8aa081a61eb4c268011eb4c9558b001c20090108134900.html";
        String s = FileUtil.readFile(path);
        System.out.println(s);

    }

    /**
     * 获取WEBURL 的绝对路径例如 E:\work\shanxi\web
     *
     * @return
     */
    public static String getWEBURL(String classPath) {
        String WEBURL = "";
        try {
            WEBURL = StaticMethod.getFilePathForUrl(classPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return WEBURL.substring(0, WEBURL.indexOf(Constants.WEB_INF));
    }

    public static String readFile(String path) {
        FileInputStream fis = null;
        String fileString = "";

        try {
            fis = new FileInputStream(path);
            fileString = InputStreamToString(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fis = null;
            }
        }

        return fileString;
    }


    /**
     * 读取输出流中的内容转换为String
     *
     * @param ops
     * @return
     */
    public static String InputStreamToString(InputStream ops) {
        BufferedReader br = null;
        StringBuffer str = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(ops, "UTF-8"));

            String line = br.readLine();
            while (line != null) {
                str.append(line);
                line = br.readLine();
            }
            ops.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ops != null) {
                    ops.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return str.toString();
    }

    /**
     * 读取输出流中的内容转换为String
     *
     * @param ops
     * @return
     * @throws IOException
     */
    public static String OutputStreamToString(OutputStream ops) throws IOException {
        byte[] b = ((ByteArrayOutputStream) ops).toByteArray();
        BufferedReader br = null;
        StringBuffer str = new StringBuffer();
        ByteArrayInputStream bis = null;
        InputStreamReader isr = null;
        try {
            bis = new ByteArrayInputStream(b);
            isr = new InputStreamReader(bis, "UTF-8");
            br = new BufferedReader(isr);

            String line = br.readLine();
            while (line != null) {
                str.append(line);
                line = br.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                bis.close();
            }

            if (isr != null) {
                isr.close();
            }

            if (br != null) {
                br.close();
            }

            if (ops != null) {
                ops.close();
            }
        }

        return str.toString();
    }

    /**
     * 写文件
     *
     * @param fileStream
     * @param filePath
     */
    public static File writeFile(ByteArrayOutputStream fileStream, String filePath) {
        InputStream fis = new ByteArrayInputStream(fileStream.toByteArray());
        File file = new File(filePath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] data = new byte[2048];
            int len = 0;
            while ((len = fis.read(data)) > 0) {

                fos.write(data, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static File writeFile(String fileString, String filePath) {
        ByteArrayOutputStream hrmlOutStream = new ByteArrayOutputStream();
        try {
            hrmlOutStream.write(fileString.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (hrmlOutStream != null) {
                    hrmlOutStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return writeFile(hrmlOutStream, filePath);
    }

    public static void castorDefautXml(String path, Object obj) throws MappingException, IOException, MarshalException, ValidationException {
        //利用castor影射为HTMLStream
        Mapping mapping = new Mapping(); //用于加载mapping.xml
//        mapping.loadMapping("E:/statExcelToHtmlMappingV35.xml");

        OutputStream stream = new ByteArrayOutputStream();

        OutputStreamWriter osw = new OutputStreamWriter(stream, "UTF-8");
//        Marshaller tmpM = new Marshaller(osw);
//        tmpM.setMapping(mapping);
//        tmpM.marshal(obj);
//        osw.close();
//        


        Marshaller.marshal(obj, osw);
        writeFile((ByteArrayOutputStream) stream, path);
    }


}
