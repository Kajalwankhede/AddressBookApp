package com.example.addressbookapp.Service;
import com.example.addressbookapp.DTO.AddressBookDTO;
import com.example.addressbookapp.Model.AddressBook;
import java.util.List;

public interface IAddressBookService {
 AddressBook createPersonContact(AddressBookDTO addressBookDTO);
 List<AddressBook> getAllPersonRecords();
 AddressBook getPersonRecordById(int id);
 AddressBook updatePersonRecordById(int id, AddressBookDTO addressBookDTO);
  void deletePersonRecordById(int id);
   public List<AddressBook> getPersonRecordByName(String name);
    public List<AddressBook> getPersonRecordByCity(String city);
    public List<AddressBook> getPersonRecordByState(String state);
   AddressBook createRecordAndToken(AddressBookDTO addressBookDTO);

 AddressBook getRecordByToken(String token);

 AddressBook updateRecordByToken(String token, AddressBookDTO addressBookDTO);

 AddressBook deletePersonRecordByToken(String token);
}
