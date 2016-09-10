package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact())
      app.getContactHelper().createContact(new ContactData("John", null, null, null, null, null, null, null, "4955555555",
              null, null, null, "mymail@myc.ru", null, null, "http://myc.ru", null, null, null));
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptContactDeletion();
    app.getContactHelper().confirmContactDeletion();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), (before.size() -1));

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
