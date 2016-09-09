package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getNavigationHelper().gotoGroupsPage();
      app.getGroupHelper().createGroup(new GroupData("test2", null, null));
    }
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("John", null, "Brown", "jho", "title", "test2", "company", "address", "4955555555",
            "901900222", "78985469521", "8-985-254879", "mymail@myc.ru", "mymail2@myc.ru",
            "mymail3@myc.ru", "http://myc.ru", "Moscow, Russia Leningradsky st. 45 b. 4 ap. 449", "433",
            "Это длинное примечание на raznich языках"));
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), (before.size() + 1));

  }

}
