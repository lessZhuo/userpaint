package com.atguigu.webapp.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;



@RestController   //标识controller入口类   //1 接收请求 包括参数   2 返回结果
public class DemoController {


    @RequestMapping("/hello")  //标识访问路径
    public  String getHelloWorld(@RequestParam("name") String name ,@RequestParam("age") Integer age){  //装载参数
            return "hello world:"+name+","+age+" 岁";    //返回值
    }

    @RequestMapping("/order/{id}")  //标识访问路径
    public  String getOrder(@PathVariable("id") String id){  //装载参数
        return "order:"+id;    //返回值
    }

    @PostMapping(value = "/order") // post请求  一般用于用户提交写操作
    public String saveOrder(@RequestBody Map orderMap){ //封装到结构化对象中  map 或者 bean
        System.out.println(orderMap);  //可以保存数据库
        return  "success";
    }


}
