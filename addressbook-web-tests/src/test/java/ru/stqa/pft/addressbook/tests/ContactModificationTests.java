package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (!app.contact().isThereAHomePage()) app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("John", null, null, null, null, null, null, null, "4955555555",
              null, null, null, "mymail@myc.ru", null, null, "http://myc.ru", null, null, null));
    }
  }

  @Test
  public void testContactModificationByIndex() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "Иван", "П", "Иванов", null, "title1", null, "company1", "address1", null, "901900333",
            null, null, "myedited@myc.ru", null, "mymaifl3@myc.ru", "http://mycf.ru", null, null, "www");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

  @Test (enabled = false)
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData(before.get(0).getId(), "Иван", "П", "Иванов", null, "title1", null, "company1", "address1", null, "901900333",
            null, null, "myedited@myc.ru", null, "mymaifl3@myc.ru", "http://mycf.ru", null, null, "www");
    app.contact().modifyFirstContact();
    app.contact().fillContactForm(contact, false);
    app.contact().submitContactModification();
    app.contact().returnToHomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(0);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}