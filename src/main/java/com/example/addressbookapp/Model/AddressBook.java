package com.example.addressbookapp.Model;
import com.example.addressbookapp.DTO.AddressBookDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Entity
@Table(name="addressbook_data")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class AddressBook {
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
    private int id;
@NonNull
    private String name;
@NonNull
    private String address;
@NonNull
    private String city;
@NonNull
    private String state;
@NonNull
    private String zip;
@NonNull
    private String phoneNumber;
@NonNull
    private String email;
 public AddressBook(AddressBook addressBook) {}
public AddressBook( AddressBookDTO addressBookDTO) {
        this.updatePersonData(addressBookDTO);
    }
public void updatePersonData(AddressBookDTO addressBookDTO) {
      this.name = addressBookDTO.getName();
      this.address = addressBookDTO.getAddress();
       this.city = addressBookDTO.getCity();
        this.state = addressBookDTO.getState();
        this.zip = addressBookDTO.getZip();
        this.phoneNumber = addressBookDTO.getPhoneNumber();
        this.email = addressBookDTO.getEmail();
    }
}
