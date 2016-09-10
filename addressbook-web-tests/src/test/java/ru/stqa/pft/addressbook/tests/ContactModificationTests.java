package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModificationByIndex() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("John", null, null, null, null, null, null, null, "4955555555",
              null, null, null, "mymail@myc.ru", null, null, "http://myc.ru", null, null, null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().modifyContactByIndex(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Иван", "П", "Иванов", null, "title1", null, "company1", "address1", null, "901900333",
            null, null, "myedited@myc.ru", null, "mymaifl3@myc.ru", "http://mycf.ru", null, null, "www");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove((before.size() - 1));
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

  @Test (enabled = false)
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("John", null, null, null, null, null, null, null, "4955555555",
              null, null, null, "mymail@myc.ru", null, null, "http://myc.ru", null, null, null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().modifyFirstContact();
    ContactData contact = new ContactData(before.get(0).getId(), "Иван", "П", "Иванов", null, "title1", null, "company1", "address1", null, "901900333",
            null, null, "myedited@myc.ru", null, "mymaifl3@myc.ru", "http://mycf.ru", null, null, "www");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(0);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}