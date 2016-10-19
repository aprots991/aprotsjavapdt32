package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class PasswordChangeTests extends TestBase {

  @Test
  public void testChangePassword() throws IOException {
    app.registration().loginByAdmin();
    app.goTo().userPage();
    app.registration().changePassword(app.getProperty("web.adminPasswordNew"));
  }
}
