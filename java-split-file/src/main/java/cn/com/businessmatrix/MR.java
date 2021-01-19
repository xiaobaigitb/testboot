package cn.com.businessmatrix;

import cn.com.businessmatrix.model.DataItem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MR {
    static final char COL_DELIMITER = '\u0001';
    static final char ROW_DELIMITER = '\n';
    static final String FILE_ENCODING = "UTF-8";

    public MR() {
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> argsMap = new HashMap();

        String infile="F:\\Users\\Administrator\\Desktop\\上投\\Tmp\\OFD_98_01_20120410_71.TXT";
        String infile_encoding="GBK";
        String json_file="F:\\Users\\Administrator\\Desktop\\上投\\Tmp\\mrDict.json";
        String outfile="F:\\Users\\Administrator\\Desktop\\上投\\Tmp\\OUT\\71_out.txt";
        argsMap.put("infile",infile);
        argsMap.put("infile_encoding",infile_encoding);
        argsMap.put("json_file",json_file);
        argsMap.put("outfile",outfile);

        /*for(int i = 0; i < args.length; ++i) {
            String[] arr = args[i].replaceAll("--", "").split("=");
            argsMap.put(arr[0], arr[1]);
        }*/

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream((String)argsMap.get("infile")), (String)argsMap.get("infile_encoding")));
        DataItem[] items = null;
        Map<String, DataItem> map = DataItem.fromJsonDataDict((String)argsMap.get("json_file"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((String)argsMap.get("outfile")), "UTF-8"));
        String line = null;
        String outLine = null;
        int i = 0;
        int cols = 0;
        int rows = 0;
        byte[] bs = new byte[0];

        while((line = br.readLine()) != null) {
            ++i;
            if ("OFDCFEND".equals(line)) {
                break;
            }

            if (i >= 10) {
                if (i == 10) {
                    cols = Integer.parseInt(line);
                    items = new DataItem[cols];
                } else if (i <= 10 + cols) {
                    items[i - 11] = (DataItem)map.get(line);
                } else if (i == 11 + cols) {
                    rows = Integer.parseInt(line);
                } else {
                    outLine = "";
                    int len = 0;
                    bs = line.getBytes((String)argsMap.get("infile_encoding"));


                    for(int m = 0; m < cols; ++m) {
                        outLine = outLine + items[m].parse(new String(bs, len, items[m].scale, (String)argsMap.get("infile_encoding"))) + (m < items.length ? '\u0001' : "");
                        len += items[m].scale;
                    }

                    bw.write(outLine + '\n');
                }
            }
        }

        System.out.println("cols:" + cols);
        System.out.println("filedLength:" + bs.length);
        System.out.println("rows:" + rows);


        br.close();
        bw.close();
        if (rows != 0 && rows != i - cols - 11 - 1) {
            throw new Exception("rows Error");
        }
    }
}

