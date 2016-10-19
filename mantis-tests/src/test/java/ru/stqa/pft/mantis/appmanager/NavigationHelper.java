package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void loginPage() {
    wd.get(app.getProperty("web.baseUrl"));
  }

  public void userPage() {
    if (wd.getCurrentUrl() == "http://localhost/mantisbt-1.3.2/account_page.php") {
      return;
    }
    click(By.xpath("//a[.='пользователь']"));
  }
}
