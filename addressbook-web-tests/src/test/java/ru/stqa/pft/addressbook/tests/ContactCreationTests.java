package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("John").withLastName("Brown").withNickName("jho").withTitle("title")
            .withCompany("company").withAddress("address").withHomePhone("4955555555")
            .withPhone2("901900222").withWorkPhone("78985469521").withFax("8-985-254879").withEmail("mymail@myc.ru")
            .withEmail2("mymail2@myc.ru").withEmail3("mymail3@myc.ru").withHomepage("http://myc.ru")
            .withAddress("Moscow, Russia Leningradsky st. 45 b. 4 ap. 449").withAddress2("433")
            .withNotes("Это длинное примечание на raznich языках");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), (before.size() + 1));

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
