package cn.com.businessmatrix;

import cn.com.businessmatrix.model.DataItem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TA2CC {
    static final char COL_DELIMITER = '\u0001';
    static final char ROW_DELIMITER = '\n';
    static final String FILE_ENCODING = "UTF-8";

    public TA2CC() {
    }

    public static void split(Map<String, String> argsMap) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream((String)argsMap.get("infile")), (String)argsMap.get("infile_encoding")));
        BufferedWriter bw = null;
        String line = null;
        String tableName = null;
        String tmpFileName = null;
        File outFile = null;
        File tmpFile = null;

        while((line = br.readLine()) != null) {
            if (tableName == null) {
                tableName = line.substring(0, 12).trim() + "_" + (String)argsMap.get("pathdate");
                tmpFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + tableName;
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((String)argsMap.get("outfilepath") + "/" + tmpFileName + ".txt"), (String)argsMap.get("infile_encoding")));
                bw.write(line.substring(12) + '\r' + '\n');
            } else if ("END".equals(line)) {
                bw.write(line + '\r' + '\n');
                if (bw != null) {
                    bw.close();
                }

                outFile = new File((String)argsMap.get("outfilepath") + "/" + tableName + ".txt");
                tmpFile = new File((String)argsMap.get("outfilepath") + "/" + tmpFileName + ".txt");
                if (!outFile.exists()) {
                    tmpFile.renameTo(outFile);
                }

                tableName = null;
            } else {
                bw.write(line + '\r' + '\n');
            }
        }

        br.close();
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> argsMap = new HashMap();

        for(int i = 0; i < args.length; ++i) {
            String[] arr = args[i].replaceAll("--", "").split("=");
            argsMap.put(arr[0], arr[1]);
        }

        if (argsMap.get("outfilepath") != null) {
            split(argsMap);
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream((String)argsMap.get("infile")), (String)argsMap.get("infile_encoding")));
            DataItem[] items = DataItem.fromJsonConfig((String)argsMap.get("json_file"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((String)argsMap.get("outfile")), "UTF-8"));
            String line = null;
            String outLine = null;
            int i = 0;
            int cols = items.length;
            int rows = 0;

            byte[] bs = new byte[0];

            while((line = br.readLine()) != null) {
                ++i;
                if ("END".equals(line)) {
                    break;
                }

                if (i == 1) {
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
            System.out.println("cols:" + cols);
            System.out.println("filedLength:" + bs.length);
            System.out.println("rows:" + rows);

            br.close();
            bw.close();
            if (rows != 0 && rows != i - 2) {
                throw new Exception("rows Error");
            }
        }
    }
}

