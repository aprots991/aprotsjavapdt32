package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      if (!app.contact().isThereAHomePage()) app.goTo().homePage();
      app.contact().create(new ContactData().withLastName("John").withHomePhone("4955555555")
              .withEmail2("mymail@myc.ru").withHomepage("http://myc.ru"));
    }
  }

  @Test
  public void testContactModificationByIndex() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Иван").withLastName("Иванов")
            .withTitle("title1").withCompany("company1").withAddress("address1").withHomePhone("901900333").withMobilePhone("901900333")
            .withEmail("myedited@myc.ru").withEmail3("mymaifl3@myc.ru").withHomepage("http://mycf.ru");
    app.contact().modify(contact);
    assertEquals(app.contact().count(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withModified(modifiedContact, contact)));
  }

  @Test (enabled = false)
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Иван").withLastName("Иванов")
            .withTitle("title1").withCompany("company1").withAddress2("address1").withMobilePhone("901900333")
            .withEmail("myedited@myc.ru").withEmail3("mymaifl3@myc.ru").withHomepage("http://mycf.ru");
    app.contact().modifyFirstContact();
    app.contact().fillContactForm(contact, false);
    app.contact().submitContactModification();
    app.contact().returnToHomePage();
    assertEquals(app.contact().count(), before.size());
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withModified(modifiedContact, contact)));

  }
}