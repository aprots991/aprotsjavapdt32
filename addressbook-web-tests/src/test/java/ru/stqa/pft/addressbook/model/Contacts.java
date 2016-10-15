package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ru.stqa.pft.addressbook.appmanager.DbHelper;

public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactData>(contacts.delegate());
  }

  public Contacts(Collection<ContactData> contact) {
    this.delegate = new HashSet<ContactData>(contact);
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts() {
    this.delegate = new HashSet<ContactData>();
  }

  public Contacts withAdded(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

  public Contacts withModified(ContactData modifiedContact, ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(modifiedContact);
    contacts.add(contact);
    return contacts;
  }

}
