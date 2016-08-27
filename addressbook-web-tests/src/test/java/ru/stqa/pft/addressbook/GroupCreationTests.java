package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    gotoGroupsPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test1header", "test1comment"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
