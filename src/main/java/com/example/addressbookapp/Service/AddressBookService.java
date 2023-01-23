package com.example.addressbookapp.Service;
import com.example.addressbookapp.DTO.AddressBookDTO;
import com.example.addressbookapp.Exception.AddressBookException;
import com.example.addressbookapp.Model.AddressBook;
import com.example.addressbookapp.Repository.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class AddressBookService implements IAddressBookService {
 @Autowired
    private AddressBookRepository addressBookRepository;
    private List<AddressBook> addressBookList = new ArrayList<>();
@Override
    public List<AddressBook> getAllPersonRecords() {

    return addressBookRepository.findAll();
 }
@Override
    public AddressBook createPersonContact(AddressBookDTO addressBookDTO) {
        AddressBook addressbookData = null;
        addressbookData = new AddressBook(addressBookDTO);
        return addressBookRepository.save(addressbookData);
}
 @Override
    public AddressBook getPersonRecordById(int id) {
        return addressBookRepository.findById(id)
                .orElseThrow(() -> new AddressBookException("Person with id" + id + "doesnt exist!!"));

}
 @Override
    public AddressBook updatePersonRecordById(int id, AddressBookDTO addressBookDTO) {
        AddressBook addressBookData = this.getPersonRecordById(id);
        addressBookData.updatePersonData(addressBookDTO);
        return addressBookRepository.save(addressBookData);

 }
@Override
    public void deletePersonRecordById(int id) {
        AddressBook addressBookData = this.getPersonRecordById(id);
        addressBookRepository.delete(addressBookData);
}
@Override
    public List<AddressBook> getPersonRecordByName(String name) {

    return addressBookRepository.getPersonByName(name);
 }
@Override
    public List<AddressBook> getPersonRecordByCity(String city) {
        return addressBookRepository.getPersonDataByCity(city);
}
@Override
    public List<AddressBook> getPersonRecordByState(String state) {
        return addressBookRepository.getPersonDataByState(state);
    }
}
