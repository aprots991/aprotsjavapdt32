package ru.stqa.pft.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupsTests extends TestBase {

  Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test2"));
    }
    if (!app.contact().isThereAContact()) app.goTo().homePage();

  }

  @Test
  public void testContactWithGroupCreation() {
    Groups groups = app.db().groups();
    logger.info("groups " + groups);
    Contacts before = app.db().contacts();
    ContactData contact = new ContactData().withFirstName("John").withLastName("Brown")
            .withAddress("address").withHomePhone("4955555555").withMobilePhone("78985469521").withEmail("mymail@myc.ru")
            .withTitle("JcB").withEmail3("email3@comp.ru").withHomepage("www.john.comp.ru")
            .inGroup(groups.iterator().next());
    app.contact().create(contact);
    Contacts after = app.db().contacts();
    System.out.println("bEFORE: " + before + "\naFTER:  " + after + "\nlAST CON: " + contact);
    assertThat(after.size(), equalTo(before.size() + 1));
    logger.debug("last contact " + app.db().lastAddedContact());
    assertThat(after, equalTo(before.withAdded(contact.withId(
            after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testContactToGroup() {
    Groups groups = app.db().groups();
    logger.info("groups " + groups);
    Contacts before = app.db().contacts();
    ContactData contact = before.iterator().next();
    System.out.println(before.iterator().next().getGroups() + " | " +  groups.iterator().next().getId() + " | " + contact);
    Contacts after = app.db().contacts();
    System.out.println("bEFORE: " + before + "\naFTER:  " + after + "\ngROUPS: " + groups);
    logger.debug("last contact " + app.db().lastAddedContact());
    assertThat(after, equalTo(before.withAdded(contact.withId(
              after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}