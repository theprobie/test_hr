package com.example.hrservice.hr.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
//import org.apache.commons.lang3.ObjectUtils;
//import org.springframework.util.Assert;
//import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EasyExcelParams implements Serializable {


    /**
     * excel文件名（不带拓展名)
     */
    private String excelNameWithoutExt;
    /**
     * sheet名称
     */
    private String sheetName;

    /**
     * 数据
     */
    private List data;

    /**
     * 数据模型类型
     */
    private Class dataModelClazz;

    /**
     * 响应
     */
    private HttpServletResponse response;


    public EasyExcelParams() {
    }

    /**
     * 检查不允许为空的属性
     *
     * @return this
     */
    public EasyExcelParams checkValid() {
//         Assert.isTrue(ObjectUtils.allNotNull(), "导出excel参数不合法!");
        return this;
    }

}
