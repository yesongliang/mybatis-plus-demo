package com.kedacom.tz.sh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import com.kedacom.tz.sh.service.DemoUserService;
import com.kedacom.tz.sh.entity.DemoUser;

/**
 * @author yesongliang
 * @since 2020-08-18
*/
@RestController
@RequestMapping("/demo-user")
public class DemoUserController {

    @Autowired
    private DemoUserService demoUserService;

    @RequestMapping("/queryById/{id}")
    public DemoUser queryById(@PathVariable("id") Integer id) {
        return demoUserService.getById(id);
    }

}
