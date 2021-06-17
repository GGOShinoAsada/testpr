package com.example.demo.service;

import com.example.demo.model.Mouse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MouseService {
    List<Mouse> getAllMouse();
    Mouse getId(int id);
    void addingMouse(Mouse mouse);
    void updatingMouse(Mouse oldm, Mouse newm);
    void removingMouse(int id);
}
