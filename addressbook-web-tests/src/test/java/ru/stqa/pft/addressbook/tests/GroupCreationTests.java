package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.gotoGroupsPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test1", "test1header", "test1comment"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}