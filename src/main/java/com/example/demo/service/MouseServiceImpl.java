package com.example.demo.service;

import com.example.demo.dao.MouseDao;
import com.example.demo.model.Mouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MouseServiceImpl implements MouseService {
   private MouseDao dao;
    @Autowired
    public void setDao(MouseDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Mouse> getAllMouse() {
        List<Mouse> array = dao.getAllMouse();
        return array;
    }

    @Override
    public Mouse getId(int id) {
        Mouse m = dao.getMouseId(id);
        return m;
    }

    @Override
    public void addingMouse(Mouse mouse) {
        dao.addingMouse(mouse);
    }

    @Override
    public void updatingMouse(Mouse oldm, Mouse newm) {
        dao.updatingMouse(oldm, newm);
    }

    @Override
    public void removingMouse(int id) {

    }
}
