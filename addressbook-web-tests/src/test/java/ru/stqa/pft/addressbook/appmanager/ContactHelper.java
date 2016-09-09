package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickName());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());
    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPhone2());
    type(By.name("notes"), contactData.getNotes());

    if (creation) {
      if (contactData.getGroup() == null) return;
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void clickByPhpAdressbokLink() {
    click(By.linkText("php-addressbook"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div/input[@value='Delete']"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void acceptContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void modifyFirstContact() {
    click(By.xpath("//tr[2]/td[8]"));
  }

  public void modifyContactByIndex(int index) {
    wd.findElements(By.xpath("//td/a/img[@title='Edit']")).get(index).click();
  }

  public void gotoAddNewContactPage() {
    if (wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
            && isElementPresent(By.name("submit"))) {
      return;
    }
    click(By.linkText("add new"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void confirmContactDeletion() {
    isElementPresent(By.xpath("//div[.='Record successful deleted']"));
  }

  public void createContact(ContactData contact) {
    gotoAddNewContactPage();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    int elements = wd.findElements(By.name("entry")).size();
    for (int i = 2; i < (elements + 2); i++) {
      String firstName = wd.findElement(By.xpath("//tr[" + i + "]/td[3]")).getText();
      String lastName = wd.findElement(By.xpath("//tr[" + i + "]/td[2]")).getText();
      String address = wd.findElement(By.xpath("//tr[" + i + "]/td[4]")).getText();
      ContactData contact = new ContactData(firstName, null, lastName, null, null, null, null, address, null,
              null, null, null, null, null, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
