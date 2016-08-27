package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    gotoAddNewConactPage();
    fillContactForm(new ContactData("John", "M", "Brown", "jho", "title", "company", "address", "4955555555",
            "901900222", "78985469521", "8-985-254879", "mymail@myc.ru", "mymail2@myc.ru",
            "mymail3@myc.ru", "http://myc.ru", "Moscow, Russia Leningradsky st. 45 b. 4 ap. 449", "433",
            "Это длинное примечание на raznich языках"));
    submitContactCreation();
    returnToHomePage();
  }

}
