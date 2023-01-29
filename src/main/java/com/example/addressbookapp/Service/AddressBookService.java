package com.example.addressbookapp.Service;
import com.example.addressbookapp.DTO.AddressBookDTO;
import com.example.addressbookapp.Exception.AddressBookException;
import com.example.addressbookapp.Model.AddressBook;
import com.example.addressbookapp.Repository.AddressBookRepository;
import com.example.addressbookapp.Util.EmailSenderService;
import com.example.addressbookapp.Util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressBookService implements IAddressBookService {
    @Autowired
    private AddressBookRepository addressBookRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSenderService;
    private List<AddressBook> addressBookList = new ArrayList<>();

    @Override
    public List<AddressBook> getAllPersonRecords() {

        return addressBookRepository.findAll();
    }

    @Override
    public AddressBook createPersonContact(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = null;
        addressBook = new AddressBook(addressBookDTO);
         addressBookRepository.save(addressBook);
        emailSenderService.sendEmail(addressBook.getEmail(),"Test Mail", "Hii...."+addressBook.getName()+" your details are added!\n\n Name:  "+addressBook.getName()+"\n Phone number:  "+addressBook.getPhoneNumber()+"\n Email:  "+addressBook.getEmail()+"\n Address:  "+addressBook.getAddress()+"\n City:  "+addressBook.getCity()+"\n State:  "+addressBook.getState()+"\n ZipCode:  "+addressBook.getZip());
        return addressBook;
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
        emailSenderService.sendEmail(addressBookData.getEmail(),"Test Mail","Hii  "+addressBookData.getName()+" your details has been edited!\n\n Name:  "+addressBookData.getName()+"\n Phone number:  "+addressBookData.getPhoneNumber()+"\n  Email:  "+addressBookData.getEmail()+"\n Address:  "+addressBookData.getAddress()+"\n City:  "+addressBookData.getCity()+"\n State:  "+addressBookData.getState()+"\n ZipCode:  "+addressBookData.getZip());
        return addressBookRepository.save(addressBookData);

}
 @Override
    public void deletePersonRecordById(int id) {
        Optional<AddressBook> addressBook=addressBookRepository.findById(id);
        if(addressBook.isPresent()){
            addressBookRepository.deleteById(id);
            emailSenderService.sendEmail(addressBook.get().getEmail(), "Test Mail","Hii Your details has been deleted!");
        }else {
            throw new AddressBookException("Sorry! Cannot find user id: " + id);
        }
}
@Override
    public List<AddressBook> getPersonRecordByName(String name) {
    List<AddressBook> getByName= addressBookRepository.getPersonByName(name);
    if(getByName.isEmpty()){
        throw new ArithmeticException("Sorry! Can not find user name: "+name);
    }else {
        return addressBookRepository.getPersonByName(name);
    }
  }

 @Override
    public List<AddressBook> getPersonRecordByCity(String city) {
        return addressBookRepository.getPersonDataByCity(city);
    }
 @Override
    public List<AddressBook> getPersonRecordByState(String state) {
        return addressBookRepository.getPersonDataByState(state);
    }

@Override
    public AddressBook createRecordAndToken(AddressBookDTO addressBookDTO) {
        AddressBook addressbookData = null;
        addressbookData = new AddressBook(addressBookDTO);
        addressBookRepository.save(addressbookData);
        String token = tokenUtil.createToken(addressbookData.getId());
    emailSenderService.sendEmail(addressbookData.getEmail(),"Test Mail","Hii"+addressbookData.getName()+" your details are added!\n\n Name:  "+addressbookData.getName()+"\n Phone number:  "+addressbookData.getPhoneNumber()+"\n Email:  "+addressbookData.getEmail()+"\n Address:  "+addressbookData.getAddress()+"\n City:  "+addressbookData.getCity()+"\n  State:  "+addressbookData.getState()+"\n ZipCode:  "+addressbookData.getZip() +"\n token: " +token);
    return addressbookData;

}
@Override
    public AddressBook getRecordByToken(String token) {
        int id = tokenUtil.decodeToken(token);
        AddressBook addressBookData = addressBookRepository.findById(id).get();
        return addressBookData ;
 }

@Override
public AddressBook updateRecordByToken(String token, AddressBookDTO addressBookDTO) {
        int id = tokenUtil.decodeToken(token);
        AddressBook addressBook = this.getPersonRecordById(id);
    addressBook.updatePersonData(addressBookDTO);
         addressBookRepository.save(addressBook);
    emailSenderService.sendEmail(addressBook.getEmail(),"Test Mail", "Hii"+addressBook.getName()+" your details are added!\n\n Name:  "+addressBook.getName()+"\n Phone number:  "+addressBook.getPhoneNumber()+"\n Email:  "+addressBook.getEmail()+"\n Address:  "+addressBook.getAddress()+"\n City:  "+addressBook.getCity()+"\n State:  "+addressBook.getState()+"\n ZipCode:  "+addressBook.getZip());
    return addressBook;

 }
 @Override
  public AddressBook deletePersonRecordByToken(String token) {
        int id = tokenUtil.decodeToken(token);
        if (addressBookRepository.findById(id).isPresent()) {
            AddressBook addressBookModel = addressBookRepository.findById(id).get();
            addressBookRepository.deleteById(id);
            emailSenderService.sendEmail(addressBookModel.getEmail(), "Test Mail","Hii Your details has been deleted!");
            return addressBookModel;
        } else {
            throw new AddressBookException("Invalid token ");
        }

    }
}