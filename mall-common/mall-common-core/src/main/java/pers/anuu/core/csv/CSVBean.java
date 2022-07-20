package pers.anuu.core.csv;

import java.util.List;

/**
 * @author pangxiong
 * @title: CSVBean
 * @projectName A_Channel
 * @description: TODO
 * @date 2022/7/1115:13
 */

public class CSVBean {
    /**
     * 文件名称
     */
    public String fileName;
    /**
     * 表头
     */
    public List<String> title;
    /**
     * 文件内容
     */
    public List<?> listMap;
}
