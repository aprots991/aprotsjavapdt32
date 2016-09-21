package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (!app.contact().isThereAHomePage()) app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withLastName("John").withHomePhone("4955555555")
              .withEmail2("mymail@myc.ru").withHomepage("http://myc.ru"));
    }
  }

  @Test
  public void testContactModificationByIndex() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Иван").withLastName("Иванов")
            .withTitle("title1").withCompany("company1").withAddress("address1").withMobilePhone("901900333")
            .withEmail("myedited@myc.ru").withEmail3("mymaifl3@myc.ru").withHomepage("http://mycf.ru");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

  @Test (enabled = false)
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Иван").withLastName("Иванов")
            .withTitle("title1").withCompany("company1").withAddress2("address1").withMobilePhone("901900333")
            .withEmail("myedited@myc.ru").withEmail3("mymaifl3@myc.ru").withHomepage("http://mycf.ru");
    app.contact().modifyFirstContact();
    app.contact().fillContactForm(contact, false);
    app.contact().submitContactModification();
    app.contact().returnToHomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(0);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}