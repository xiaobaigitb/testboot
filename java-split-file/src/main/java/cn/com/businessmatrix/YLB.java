package cn.com.businessmatrix;

import cn.com.businessmatrix.model.DataItem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class YLB {
    static final char COL_DELIMITER = '\u0001';
    static final char ROW_DELIMITER = '\n';
    static final String FILE_ENCODING = "UTF-8";

    public YLB() {
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> argsMap = new HashMap();

        for(int i = 0; i < args.length; ++i) {
            String[] arr = args[i].replaceAll("--", "").split("=");
            argsMap.put(arr[0], arr[1]);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream((String)argsMap.get("infile")), (String)argsMap.get("infile_encoding")));
        DataItem[] items = DataItem.fromJsonConfig((String)argsMap.get("json_file"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((String)argsMap.get("outfile")), "UTF-8"));
        String line = null;
        String outLine = null;
        int i = 0;
        int cols = 0;
        int rows = 0;
        String[] arr = null;

        while((line = br.readLine()) != null) {
            ++i;
            if (i == 1) {
                rows = Integer.parseInt(line);
            } else if (i == 2) {
                cols = line.split("\\|", -1).length;
            } else {
                if (i > rows + 2) {
                    break;
                }

                outLine = "";
                arr = line.split("\\|", -1);

                for(int m = 0; m < cols; ++m) {
                    if (cols - 1 == arr.length && m == arr.length) {
                        outLine = outLine + (m < items.length ? '\u0001' : "");
                    } else {
                        outLine = outLine + items[m].parse(arr[m]) + (m < items.length ? '\u0001' : "");
                    }
                }

                bw.write(outLine + '\n');
            }
        }
        System.out.println("cols:" + cols);
        System.out.println("rows:" + rows);

        br.close();
        bw.close();
    }
}

