package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;
import java.util.regex.Pattern;

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
    attach(By.name("photo"), contactData.getPhoto());
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
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(String.valueOf(contactData.getGroups()
                .iterator().next().getName()));
        System.out.println("group name: " + contactData.getGroups().iterator().next().getName()
                + " group id: " + contactData.getGroups().iterator().next().getId());
      } else
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }

  public void deleteSelectedContact() {
    click(By.xpath("//div/input[@value='Delete']"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
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

  public void modifyContactById(int id) {
    wd.findElement(By.xpath("//input[@id='" + id + "']/../..//img[@title='Edit']")).click();
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

  public void create(ContactData contact) {
    gotoAddNewContactPage();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    modifyContactById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    acceptContactDeletion();
    contactCache = null;
    confirmContactDeletion();
  }

  public boolean isThereAContact() {
    if (!isThereAHomePage()) click(By.linkText("home"));
    return isElementPresent(By.name("selected[]"));
  }

  private Contacts contactCache = null;

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    int elements = wd.findElements(By.name("entry")).size();
    for (int i = 2; i < (elements + 2); i++) {
      int id = Integer.parseInt(wd.findElement(By.xpath("//tr[" + i + "]//input")).getAttribute("id"));
      String firstName = wd.findElement(By.xpath("//tr[" + i + "]/td[3]")).getText();
      String lastName = wd.findElement(By.xpath("//tr[" + i + "]/td[2]")).getText();
      String address = wd.findElement(By.xpath("//tr[" + i + "]/td[4]")).getText();
      String allEmails = wd.findElement(By.xpath("//tr[" + i + "]/td[5]")).getText();
      String allPhones = wd.findElement(By.xpath("//tr[" + i + "]/td[6]")).getText();
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withPhone2(phone2)
            .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public ContactData infoFromDetails(ContactData contact) {
    initContactDetailsById(contact.getId());
    String allDetails = wd.findElement(By.id("content")).getText();
    String[] splitByLine = allDetails.split("\\n\\n");
    String nameAndAddress = splitByLine[0];
    String allPhones = splitByLine[1].replaceAll("[-()]", "")
            .replaceAll("H: ", "").replaceAll("M: ", "").replaceAll("W: ", "").replaceAll(" ", "");
    String allEmails = splitByLine[2].replaceAll(" \\(.+?\\)", "");
    /**String[] splitBySpace = allDetails.split("\\s");
     String[] splitByLine = allDetails.split("\\n\\n");
     String firstname = splitBySpace[0];
     String lastname = splitBySpace[1];
     String address = splitByLine[0].replace(lastname, "").replace(firstname, "");
     String allPhones = splitByLine[1].replaceAll("[-()]", "")
     .replaceAll("H: ", "").replaceAll("M: ", "").replaceAll("W: ", "").replaceAll(" ", "");
     String allEmails = splitByLine[2].replaceAll(" \\(.+?\\)", "");
     return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withAddress(address)
     .withAllPhones(allPhones).withAllEmails(allEmails);**/
    return new ContactData().withNameAndAddress(nameAndAddress).withAllPhones(allPhones).withAllEmails(allEmails);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells =row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  private void initContactDetailsById(int id) {
    wd.findElement(By.xpath(String.format("//a[@href='view.php?id=%s']", id))).click();
  }
}
