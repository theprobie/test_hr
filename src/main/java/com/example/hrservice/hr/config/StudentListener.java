//package com.example.hrservice.hr.config;
//
//import com.example.hrservice.hr.model.UpLoadStudent;
//import com.example.hrservice.hr.service.StudentService;
//
//public class StudentListener extends BaseExcelListener<UpLoadStudent> {
//
//    /**
//     * 这里需要注意入库使用到的Service或者DAO层需要使用到的相关方法时,
//     * 不要通过Spring 使用{@code @Autowired}注入,同时该Listener也不要交由Spring IOC进行管理
//     * 直接通过构造方法传入相关`xxxService` 或者 `xxxMapper`
//     */
//    private StudentService studentService;
//
//    public StudentListener(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//}
