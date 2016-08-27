package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().clickByHomeLink();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    //clickByPhpAdressbokLink();
  }

}
