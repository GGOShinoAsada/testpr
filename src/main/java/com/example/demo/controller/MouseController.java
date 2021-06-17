package com.example.demo.controller;

import com.example.demo.dao.MouseDao;
import com.example.demo.model.Mouse;
import com.example.demo.service.MouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mouse")
public class MouseController {
    private MouseService service;
    @Autowired
    public void setService(MouseService service) {
        this.service = service;
    }
   /* @GetMapping
    public ResponseEntity showStatus() {
        return new BaseResponse(SUCCESS_STATUS, 1);
    }*/

    @GetMapping
    public ModelAndView index(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("mouse/index");

        System.out.println(service.getAllMouse().size());
        mav.addObject("mouses", service.getAllMouse());

        return mav;
    }
    /*@ResponseBody*/
   /* public ModelAndView index(ModelMap model){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("mouses", service.getAllMouse());
        return mav;
    }*/
    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable int id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("mouse", service.getId(id));
        mav.setViewName("details");
        return mav;
    }
    @GetMapping("/new")
    public ModelAndView adding(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("create");
        return mav;
    }
    @PostMapping("/new")
    public String addingPost(@RequestParam("name") String name, @RequestParam("description") String description){
        service.addingMouse(new Mouse(name, description));
        return "return: /mouse";
    }
    @GetMapping("/edit/{id}")
    private String edit(@PathVariable int id, Model model){
        model.addAttribute(service.getId(id));
        return "edit";
    }
    @PostMapping("/edit/{id}")
    public String editmouse(@RequestParam("id") String id ,@RequestParam("name") String name, @RequestParam("description") String description){
        int d = Integer.parseInt(id);
        service.updatingMouse(service.getId(d), new Mouse(name, description));
        return "redirect: /mouse";
    }
    @GetMapping("/delete/{id}")
    public String deletemouse(@PathVariable int id){
        service.removingMouse(id);
        return "redirect: /mouse";
    }
}
