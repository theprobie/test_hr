package com.example.hrservice.hr.service;

import com.example.hrservice.hr.mapper.JObLevelMapper;
import com.example.hrservice.hr.model.JObLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobLevelService {

    @Autowired
    JObLevelMapper jObLevelMapper;
    public List<JObLevel> getAllJobLevel() {
        return jObLevelMapper.getAllJobLevel();
    }

    public Integer addJobLevel(JObLevel jObLevel) {
        jObLevel.setEnabled(true);
        jObLevel.setCreateDate(new Date());
        return jObLevelMapper.insertSelective(jObLevel);
    }

    public Integer updateJobLevel(JObLevel jObLevel) {
        return jObLevelMapper.updateByPrimaryKeySelective(jObLevel);
    }

    public Integer deletePositionById(Integer id) {
       return  jObLevelMapper.deleteByPrimaryKey(id);
    }

    public Integer deletePositionByIds(Integer[] ids) {
        return jObLevelMapper.deletePositionByIds(ids);
    }
}
