package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test2"));
    }
    if (!app.contact().isThereAContact()) app.goTo().homePage();

  }

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("John").withLastName("Brown").withNickName("jho").withTitle("title")
            .withCompany("company").withAddress("address").withHomePhone("4955555555")
            .withPhone2("901900222").withWorkPhone("78985469521").withFax("8-985-254879").withEmail("mymail@myc.ru")
            .withEmail2("mymail2@myc.ru").withEmail3("mymail3@myc.ru").withHomepage("http://myc.ru")
            .withAddress("Moscow, Russia Leningradsky st. 45 b. 4 ap. 449").withAddress2("433")
            .withNotes("Это длинное примечание на raznich языках");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
