package com.example.addressbookapp.Repository;
import com.example.addressbookapp.Model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AddressBookRepository extends JpaRepository<AddressBook,Integer> {
List<AddressBook> getPersonByName(String name);
List<AddressBook> getPersonDataByCity(String city);
List<AddressBook> getPersonDataByState(String state);
}