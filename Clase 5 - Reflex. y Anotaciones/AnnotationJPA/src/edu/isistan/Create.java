package edu.isistan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.isistan.dao.Address;
import edu.isistan.dao.Person;

public class Create {

    
    public static void main(String[] args) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA");
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        Address address1 = new Address("London", "Baker st.", 221);
        Address address2 = new Address("Springfield", "Evergreen Terrace", 742);
        Person sherlock = new Person(2000000, "Sherlock Homes", address1);
        Person watson = new Person(2000001, "John Watson", address1);
        Person homer = new Person(34000001, "Homer Simpson", address2);
        Person marge = new Person(34000002, "Marge Simpson", address2);
        Person bart = new Person(34000003, "Bart Simpson", address2);
        Person lisa = new Person(34000004, "Lisa Simpson", address2);
        Person magie = new Person(34000005, "Magie Simpson", address2);
        em.persist(sherlock);
        em.persist(watson);
        em.persist(homer);
        em.persist(marge);
        em.persist(bart);
        em.persist(lisa);
        em.persist(magie);
        em.getTransaction().commit();
        em.close();
        emfactory.close();
    }
}
