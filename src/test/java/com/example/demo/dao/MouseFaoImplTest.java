package com.example.demo.dao;

import com.example.demo.model.Mouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MouseFaoImplTest {
    @Inject
    private ApplicationContext context;
    private SessionFactory factory;

    @Autowired
    public void setContext() {
        this.context = new AnnotationConfigApplicationContext();
    }
    @Autowired
    public void setFactory() {
        this.factory = context.getBean(SessionFactory.class);
    }
    @PersistenceContext
    private EntityManager em;
    @Autowired
    public void setEm(EntityManager em) {
        this.em = em;
    }
    private MouseDao dao;
    @Autowired
    public void setDao(MouseDao dao){
        this.dao = dao;
    }
    @Test
    void getAllMouse() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            Mouse item = new Mouse();
            item = new Mouse("мышь №1", "это мышь №1");
            List<Mouse> array = new ArrayList();
            array.add(new Mouse("мышь №1", "это мышь №1"));
            session.save(item);
            Assert.assertEquals(array.size(), dao.getAllMouse().size() );
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (factory.isOpen() && factory!=null)
                factory.close();
        }
    }

    @Test
    void getMouseId() {
    }

    @Test
    void addingMouse() {
    }

    @Test
    void removingMouse() {
    }

    @Test
    void updatingMouse() {
    }
}