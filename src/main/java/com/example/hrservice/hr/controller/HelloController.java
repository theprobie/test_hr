package com.example.hrservice.hr.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.example.hrservice.hr.config.UpLoadStudentListener;
import com.example.hrservice.hr.config.UploadDataListener;
import com.example.hrservice.hr.mapper.StudentMapper;
import com.example.hrservice.hr.model.*;
import com.example.hrservice.hr.service.*;
import com.example.hrservice.hr.util.EasyExcelUtil;
import com.example.hrservice.hr.util.JsonUtils;
import com.example.hrservice.hr.util.RedisUtil;
import com.example.hrservice.hr.util.RespBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.*;

@RestController
@Slf4j
public class HelloController {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @Autowired
    DepartService departService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    Menuservice menuservice;

//    @Autowired
//    private JsonUtils jsonUtils;

//    @Autowired
//    private RedisTemplate<Object, Object> redisTemplate;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/employee/basic/hello")
    public String hello2() {
        return "/employee/basic/hello";
    }

    @GetMapping("/employee/advanced/hello")
    public String hello3() {
        return "/employee/advanced/hello";
    }

    @GetMapping("statistics/personnel/hello")
    public String hello4() {
        return "/employee/advanced/hello";
    }

    @GetMapping("/salary/month/hello")
    public String hello5() {
        return "/employee/advanced/hello";
    }

    @GetMapping("/system/hr/hello")
    public String hello6() {
        return "/employee/advanced/hello";
    }
//    @RequestMapping(value = "/hello/{id}")
//    public Hr hello(@PathVariable(value = "id") Integer id) {
//       Hr hr= ((Hr) redisUtil.get("hrId"));
//        if (StringUtils.isEmpty(hr)) {
//            hr = userService.getHrById(id);
//           redisUtil.set("hrId",JsonUtils.obj2String(hr));
//        }
//        return hr;
//    }

    @GetMapping("/{id}")
    public String getAllDepartments(@PathVariable(value = "id") Integer id) throws JsonProcessingException {
        String str = "";
        str = redisUtil.get(id.toString());
        if (StringUtils.isEmpty(str)) {
            Department deps = departService.getDepartmentsById(id);
//            str = new ObjectMapper().writeValueAsString(deps);
            str = JsonUtils.objectToJson(deps);//此方法可以传入对象，也可以传入List、Map集合，例如如下接口
            redisUtil.set(id.toString(), str);

        }
        return str;//嵌套查询
    }

    @GetMapping("/depts")
    public RespBean getAllDepartments() throws JsonProcessingException {
        String str = "";
        str = redisUtil.get("deptId");
        if (StringUtils.isEmpty(str)) {
            List<Department> allDep = departService.getAllDepartments();
            redisUtil.set("deptId", JsonUtils.objectToJson(allDep));
        }
        return RespBean.ok("查询成功");//嵌套查询
    }

    /**
     * 按照查询嵌套处理
     *
     * @return
     */
    @GetMapping("/student")
    public RespBean getAllStudent() {
        return RespBean.ok("查询成功", studentService.getStudents());
    }

    /**
     * 按照结果集进行查询
     *
     * @return
     */
    @GetMapping("/student2")
    public RespBean getAllStudent2() {
        return RespBean.ok("查询成功", studentService.getStudents2());
    }

    /**
     * 按照结果集进行查询
     *
     * @param id
     * @return
     */
    @GetMapping("/teacher/{id}")
    public RespBean getTeacher(@PathVariable("id") Integer id) {
        return RespBean.ok("查询成功", teacherService.getTeacher(id));
    }

    /**
     * 按按照查询嵌套处理
     *
     * @param id
     * @return
     */
    @GetMapping("/teacher2/{id}")
    public RespBean getTeacher2(@PathVariable("id") Integer id) {
        return RespBean.ok("查询成功", teacherService.getTeacher2(id));
    }

    /**
     * 可从前端传入一个对象或者数组放入map中，
     * 为了简单测试，List<Integer> stuList = Arrays.asList(1,2,3)放入map中
     * 不能直接map.put("ids",1)......xml文件中无法遍历，只能放入List集合中进行遍历
     *
     * @return
     */
    @GetMapping("/batch")
    public RespBean getStudentInfor() {
        Map map = new HashMap();
        List<Integer> stuList = Arrays.asList(1, 2, 3);
        map.put("ids", stuList);
        return RespBean.ok("查询成功", studentService.getStudentInfor(map));

    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(LocalDate.now() + "测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Student.class).sheet("模板").doWrite(data());

    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), UpLoadStudent.class, new UploadDataListener(studentMapper)).sheet().doRead();

        return "success";
    }

    private List<Student> data() {
        List<Student> list = new ArrayList<Student>();
        List<Student> students = studentService.getStudents();
        for (Student student : students) {
            Student data = new Student();
            data.setId(student.getId());
            data.setName(student.getName());
            data.setTid(student.getTeacher().getId());
            data.setTname(student.getTeacher().getName());
            list.add(data);
        }
        return list;
    }

    @PostMapping("/readExcel")
    public RespBean readExcel(@RequestParam MultipartFile excel) {
        List<ErrRows> errRows = EasyExcelUtil.readExcel(excel, UpLoadStudent.class, new UpLoadStudentListener(studentService));
        log.info("/*------- 错误的行号数为 :  {}-------*/", JSON.toJSONString(errRows));
        if (errRows.size() == 0) {
            return RespBean.ok("excel批量插入成功");
        }
        return RespBean.error("excel批量插入失败");
    }

    //递归查询所有菜单的子菜单
    @GetMapping("/menu")
    public RespBean getMenuByMpP() {
        List<Menu> menus = menuservice.getMenuByMP();
        if(menus.size()>0){
            return RespBean.ok("查询成功",menus);
        }
        return RespBean.error("查询失败");
    }
}
