package edu.isistan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.isistan.dao.Person;

public class Read {

    
    public static void main(String[] args) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA");
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        Person sherlock = em.find(Person.class, 2000000);
        System.out.println(sherlock);
        System.out.println(sherlock.getAddress());

        Person homer = em.find(Person.class, 34000001);
        System.out.println(homer);
        System.out.println(homer.getAddress());
        
        em.getTransaction().commit();
        em.close();
        emfactory.close();
    }
}
