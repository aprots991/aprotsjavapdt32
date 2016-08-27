package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getNavigationHelper().clickByHomeLink();
    app.getContactHelper().modifyFirstContact();
    app.getContactHelper().fillContactForm(new ContactData("Иван", "П", "Иванов", "ива", "title1", "company1", "address1", "4955555555",
            "901900333", "78985469522", "8-985-254880", "mymaifl@myc.ru", "mymaifl2@myc.ru",
            "mymaifl3@myc.ru", "http://mycf.ru", "Moscow, Russia Leningradsky st. 44 b. 4 ap. 447", "455",
            "www"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }

}