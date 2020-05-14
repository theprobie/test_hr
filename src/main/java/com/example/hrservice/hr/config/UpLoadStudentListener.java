package com.example.hrservice.hr.config;

import com.example.hrservice.hr.model.UpLoadStudent;
import com.example.hrservice.hr.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class UpLoadStudentListener extends BaseExcelListener<UpLoadStudent> {

    /**
     * 这里需要注意入库使用到的Service或者DAO层需要使用到的相关方法时,
     * 不要通过Spring 使用{@code @Autowired}注入,同时该Listener也不要交由Spring IOC进行管理
     * 直接通过构造方法传入相关`xxxService` 或者 `xxxMapper`
     */
    StudentService studentService;

    @Override
    public boolean validateBeforeAddData(UpLoadStudent upLoadStudent) {
        return true;
    }

    @Override
    public void doService() {

        log.info("/*------- 写入数据 -------*/");
        studentService.save(this.getData());//this.getData()代表含义子类继承父类,子类可以直接用this调用父类
    }
}
