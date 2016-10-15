package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (!app.contact().isThereAHomePage()) app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Jackson").withLastName("John").withAddress("Moscow Russia \n Len st. 68 k1")
              .withHomePhone("8(812) 355-55-99").withMobilePhone("+7-901-355-55-99").withWorkPhone("4955555555")
              .withEmail("mail1@mail.ru").withEmail2("mymail@myc.ru").withEmail3("mail3@mail.ru"));
    }
  }

  @Test
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromDetails = app.contact().infoFromDetails(contact);

    assertThat(mergeContactData(contact), equalTo(mergeContactDetailsData(contactInfoFromDetails)));
  }

  public String mergeContactData(ContactData contact) {
    String getAllPhones1 = contact.getAllPhones().split("\n")[0];
    String getAllPhones2 = contact.getAllPhones().split("\n")[1];
    String getAllPhones3 = contact.getAllPhones().split("\n")[2];
    return Arrays.asList(contact.getLastName(), contact.getFirstName(), contact.getAddress(),
            getAllPhones1, getAllPhones2, getAllPhones3, contact.getAllEmails())
            .stream().filter((s) -> !(s == null || s.equals(""))).collect(Collectors.joining("\n"));
  }

  public String mergeContactDetailsData(ContactData contactInfoFromDetails) {
    return Arrays.asList(contactInfoFromDetails.getNameAndAddress(), contactInfoFromDetails.getAllPhones(), contactInfoFromDetails.getAllEmails())
            .stream().filter((s) -> !(s == null || s.equals(""))).collect(Collectors.joining("\n"));
  }

}
