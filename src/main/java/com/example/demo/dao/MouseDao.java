package com.example.demo.dao;

import com.example.demo.model.Mouse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MouseDao {
    List<Mouse> getAllMouse();
    Mouse getMouseId(int id);
    void addingMouse(Mouse mouse);
    void removingMouse(int id);
    void updatingMouse(Mouse oldm, Mouse newm);
}
