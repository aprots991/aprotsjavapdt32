package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.group().list().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test2"));
    }
    if (!app.contact().isThereAContact()) app.goTo().homePage();

  }

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("John", null, "Brown", "jho", "title", "test2", "company", "address", "4955555555",
            "901900222", "78985469521", "8-985-254879", "mymail@myc.ru", "mymail2@myc.ru",
            "mymail3@myc.ru", "http://myc.ru", "Moscow, Russia Leningradsky st. 45 b. 4 ap. 449", "433",
            "Это длинное примечание на raznich языках");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), (before.size() + 1));

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
