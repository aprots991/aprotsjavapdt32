package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<GroupData> result = session.createQuery("from GroupData").list();
      session.getTransaction().commit();
      session.close();
      return new Groups(result);
    }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public Object lastAddedContact() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Object result = session.createQuery("select max(id) from ContactData where deprecated = '0000-00-00'").uniqueResult();
    session.getTransaction().commit();
    session.close();
    System.out.println("result: " + result);
    return result;
  }
  //SELECT max(id), firstname, middlename, lastname, nickname, title, company, address, home, mobile, work, fax, email, email2, email3, homepage, address2, phone2, notes, photo FROM `addressbook` WHERE `deprecated` = '000-00-00'

  /**public Contacts withModified(ContactData modifiedContact, ContactData contact) {
    Contacts contacts = new Contacts(contact);
    contacts.remove(modifiedContact);
    contacts.add(contact);
    return contacts;
  }**/
}