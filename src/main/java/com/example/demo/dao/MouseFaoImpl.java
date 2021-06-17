package com.example.demo.dao;

import com.example.demo.model.Mouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
@Repository
@Primary

public class MouseFaoImpl implements MouseDao {

   private SessionFactory factory;
    @PersistenceUnit(unitName = "customEMF")
    private EntityManagerFactory entityManagerFactory;
    private  EntityManager manager;

    /*private void setManager(){
        this.manager = factory.createEntityManager();

    }*/

   /* @Autowired
    @PersistenceContext(name = "customEMF")
    private EntityManager entityManager;*/


   /* private SessionFactory getSessionFactory() {
        var data = entityManagerFactory.unwrap(SessionFactory.class);
        return data;
    }*/




   /*@Inject
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

*/

    @Override
    public List<Mouse> getAllMouse() {
        List<Mouse> mouses = new ArrayList();
        try{

            EntityManager manager = entityManagerFactory.createEntityManager();
            Session session = manager.unwrap(Session.class);
           // Session session = factory.openSession();

            session.getTransaction().begin();
            mouses = session.createSQLQuery("select * from mouse").getResultList();
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (factory!=null && factory.isOpen())
                factory.close();
           /* if (factory.isOpen() && factory!=null)
                factory.close();*/
        }
        return mouses;
    }

    @Override
    public Mouse getMouseId(int id) {
        Mouse mouse = new Mouse();
        try{
            Session session = factory.getCurrentSession();
            session.getTransaction().begin();
            mouse = session.get(Mouse.class, id);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (factory!=null && factory.isOpen())
                factory.close();
        }
        return mouse;
    }

    @Override
    public void addingMouse(Mouse mouse) {
        try{
            EntityManager manager = factory.getCurrentSession();
            Session session = manager.unwrap(Session.class);
            session.getTransaction().begin();
            session.save(mouse);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (factory!=null&& factory.isOpen())
                factory.close();
            /*if (factory!=null && factory.isOpen())
                factory.close();*/
        }
    }

    @Override
    public void removingMouse(int id) {
        try{
            EntityManager manager = factory.getCurrentSession();
            Session session = manager.unwrap(Session.class);
            session.getTransaction().begin();
            session.delete(session.get(Mouse.class, id));
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

    @Override
    public void updatingMouse(Mouse oldm, Mouse newm) {
        try{
            EntityManager manager = factory.getCurrentSession();
            Session session = manager.unwrap(Session.class);
            session.getTransaction().begin();
            Mouse m = (Mouse) session.createSQLQuery("select * from mouse where name=? and description=?").setParameter(1, oldm.getName()).setParameter(2, oldm.getDescription()).getSingleResult();
            if (m!=null){
                m.setName(newm.getName());
                m.setDescription(newm.getDescription());
            }
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
}
