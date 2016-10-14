package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (!app.contact().isThereAHomePage()) app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withLastName("John").withWorkPhone("4955555555")
              .withEmail("mymail@myc.ru").withHomepage("http://myc.ru"));
    }
  }

  @Test(invocationCount = 2)
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Assert.assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
