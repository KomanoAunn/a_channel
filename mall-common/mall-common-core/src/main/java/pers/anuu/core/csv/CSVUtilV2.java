package pers.anuu.core.csv;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author pangxiong
 * @title: CSVUtilV2
 * @projectName A_Channel
 * @description: excel导出工具
 * @date 2022/7/1115:11
 */
public class CSVUtilV2 {
    private static final String FILE_SAVE_PATH = "/mnt/www/download";// 保存路径
    private static final String FILE_DOWNLOAD_PATH = "/download";// 下载路径
    /**
     * 错误信息
     */
    private final StringBuilder errorMsg = new StringBuilder();
    /**
     * 每一行的内容
     */
    private final List<Object> rowN = new ArrayList<>();
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 输出的文件文件目录
     */
    private String outPath;
    /**
     * 文件大小
     */
    private long fileSize = 0;
    /**
     * 表头
     */
    private final List<String> title = new ArrayList<>();

    public static void exportCSVFile(CSVBean csvBean) {
//        CSVUtilV2 csvUtilV2 = new CSVUtilV2();
//        csvUtilV2.title=csvBean.title;
//        csvUtilV2.setFileName(csvBean.fileName);
//        csvUtilV2.writeTitleToFile(csvUtilV2.fileName);
//        csvUtilV2.writeRowToFile(csvBean.listMap);
    }


    /**
     * 生成文件
     *
     * @param filename
     * @return
     */
    public File getFile(String filename) {
        return getFile(filename, false);
    }

    public File getFile(String filename, Boolean createNew) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        String folderName = "/excel/" + year + "/" + month + "/" + day;
        File dir = new File(FILE_SAVE_PATH + folderName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(FILE_SAVE_PATH + folderName + "/" + filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
                errorMsg.append(e1.getMessage());
            }
        }
        if (createNew) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.outPath = FILE_DOWNLOAD_PATH + folderName + "/" + fileName;
        return file;
    }


    public boolean writeTitleToFile(String fileName) {
        return writeTitleToFile(fileName, "GBK");
    }

    public boolean writeTitleToFile(String fileName, String charset) {
        File file = getFile(fileName);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), charset), 1024);
            writeTitle(bw);
            bw.flush();
        } catch (Exception e) {
            errorMsg.append(e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                FileChannel fc = null;
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                fc = fis.getChannel();
                this.fileSize = fc.size();
                fc.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                errorMsg.append(e.getMessage());
            }
        }
        return errorMsg.length() == 0;
    }

    /**
     * 写入一行标题
     *
     * @param bw
     * @throws IOException
     */
    private void writeTitle(BufferedWriter bw) throws IOException {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < title.size(); i++) {
            String line = String.valueOf(title.get(i));
            if ("".equals(line.trim()) || line.equals("null")) {
                line = "";
            }
            sb.append("\"").append(line.replaceAll("\"", "\"\"")).append("\",");
        }
        bw.write(sb.deleteCharAt(sb.length() - 1).toString());
        bw.newLine();
    }

    /**
     * 将一行写入文件
     *
     * @param listMap
     * @param fileName
     * @return
     */
    public boolean writeRowToFile(List<Map<String, Object>> listMap, String fileName) {

        return writeRowToFile(listMap, fileName, "GBK");
    }

    public boolean writeRowToFile(List<Map<String, Object>> listMap, String fileName, String charset) {
        File file = getFile(fileName);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), charset), 1024);
            if (listMap != null && listMap.size() >= 1) {
                for (Map<String, Object> map : listMap) {
                    setRowContent(map);
                    writeRow(bw);
                }
            }
            bw.flush();
        } catch (Exception e) {
            errorMsg.append(e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                FileChannel fc = null;
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                fc = fis.getChannel();
                this.fileSize = fc.size();
                fc.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                errorMsg.append(e.getMessage());
            }
        }
        return errorMsg.length() == 0;
    }

    public boolean writeRowToFile(List<Map<String, Object>> listMap) {
        return writeRowToFile(listMap, fileName);
    }

    /**
     * 获取每一行的内容
     *
     * @param map
     * @return
     */
    private List<Object> setRowContent(Map<String, Object> map) {
        rowN.clear();
        for (Object titleName : title) {
            rowN.add(map.get(titleName));
        }
        return rowN;
    }

    /**
     * 写入一行内容
     *
     * @param bw
     * @throws IOException
     */
    private void writeRow(BufferedWriter bw) throws IOException {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < rowN.size(); i++) {
            String line = String.valueOf(rowN.get(i));
            if ("".equals(line.trim()) || line.equals("null")) {
                line = "";
            }
            if (line.matches("[0-9]+") && line.length() > 6) {
                line = line + "\t";
            }
            sb.append("\"").append(line.replaceAll("\"", "\"\"")).append("\",");
        }
        bw.write(sb.deleteCharAt(sb.length() - 1).toString());
        bw.newLine();
    }


    public void setFileName(String fileName) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String nowdate = format.format(new Date());
        this.fileName = fileName + "_" + nowdate + ".txt";
    }
}
