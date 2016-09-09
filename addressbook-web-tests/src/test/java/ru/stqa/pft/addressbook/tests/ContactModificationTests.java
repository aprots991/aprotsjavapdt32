package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("John", null, null, null, null, null, null, null, "4955555555",
              null, null, null, "mymail@myc.ru", null, null, "http://myc.ru", null, null, null));
      before++;
    }
    app.getContactHelper().modifyFirstContact();
    app.getContactHelper().fillContactForm(new ContactData("Иван", "П", "Иванов", null, "title1", null, "company1", "address1", null,
            "901900333", null, null, "myedited@myc.ru", null,
            "mymaifl3@myc.ru", "http://mycf.ru", null, null,
            "www"), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }

}