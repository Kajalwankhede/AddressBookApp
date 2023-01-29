package com.example.addressbookapp.Controller;
import com.example.addressbookapp.DTO.AddressBookDTO;
import com.example.addressbookapp.DTO.ResponseDTO;
import com.example.addressbookapp.Exception.AddressBookException;
import com.example.addressbookapp.Model.AddressBook;
import com.example.addressbookapp.Service.IAddressBookService;
import com.example.addressbookapp.Util.EmailSenderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
@Autowired
    private IAddressBookService addressService;
@GetMapping(value = {"", "/", "/getAll"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
        List<AddressBook> addressBookList = null;
        addressBookList = addressService.getAllPersonRecords();
        ResponseDTO respDTO = new ResponseDTO("Got All the Data Successfully", addressBookList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

@PostMapping("/create")
    public ResponseEntity<ResponseDTO> createPersonContact(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        AddressBook addressBookData = null;
        addressBookData = addressService.createPersonContact(addressBookDTO);
        ResponseDTO respDTO = new ResponseDTO("Created Person contact Successfully", addressBookData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
}
@GetMapping("/getdata/{id}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("id") int id) {
        AddressBook addressBookData = null;
        addressBookData = addressService.getPersonRecordById(id);
        ResponseDTO respDTO = new ResponseDTO("Get data for Person Id Successfully", addressBookData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
 }

@PutMapping(path = "/update/{id}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@Valid @PathVariable("id") int id,
                                                                 @RequestBody AddressBookDTO addressBookDTO) {
        AddressBook addressBookData = null;
        addressBookData = addressService.updatePersonRecordById(id, addressBookDTO);
        ResponseDTO respDTO = new ResponseDTO("Updated Person Data for Id : ", addressBookData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
}

 @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deletePersonDataById(@PathVariable("id") int id) {
        addressService.deletePersonRecordById(id);
        ResponseDTO respDTO = new ResponseDTO("Deleted Data Successfully", "Deleted id: " + id);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
}

 @GetMapping("/getbyname/{name}")
    public ResponseEntity<ResponseDTO> getPersonByName(@PathVariable("name") String name){
        List<AddressBook> addressBookList = null;
        addressBookList =addressService.getPersonRecordByName(name);
        ResponseDTO respDTO = new ResponseDTO("Got data by Name successfully", addressBookList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
}
@GetMapping("/getbycity/{city}")
    public ResponseEntity<ResponseDTO> getPersonByCity(@PathVariable("city") String city){
        List<AddressBook> addressBookList = null;
        addressBookList =addressService.getPersonRecordByCity(city);
        ResponseDTO respDTO = new ResponseDTO("Got data by City successfully", addressBookList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
}
@GetMapping("/getbystate/{state}")
    public ResponseEntity<ResponseDTO> getPersonByState(@PathVariable("state") String state){
        List<AddressBook> addressBookList = null;
        addressBookList =addressService.getPersonRecordByState(state);
        ResponseDTO respDTO = new ResponseDTO("Got data by State successfully", addressBookList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
// creating address book data by using  token
@PostMapping("/createbytoken")
    public ResponseEntity<ResponseDTO> createByToken(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        AddressBook addressBook = addressService.createRecordAndToken(addressBookDTO);
        ResponseDTO response = new ResponseDTO("Token Generted sucessfully", addressBook);
        return new ResponseEntity<>(response, HttpStatus.OK);
   }
   // Getting particular address book data by  token
 @CrossOrigin
@GetMapping("/getbytoken/{token}")
    public ResponseEntity<ResponseDTO> getDetailByToken(@PathVariable String token) {
        ResponseDTO response = new ResponseDTO("Address Book Data fetched Successfully ",
                addressService.getRecordByToken(token));
        return new ResponseEntity<>(response, HttpStatus.OK);
}

// Update particular address book data data by jwt token
@PutMapping("/updatebytoken/{token}")
    public ResponseEntity<ResponseDTO> updatebyToken(@PathVariable String token,
                                                     @Valid @RequestBody AddressBookDTO addressBookDTO) {
        ResponseDTO response = new ResponseDTO("Address Book Data Updated Successfully",
                addressService.updateRecordByToken(token, addressBookDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
}

// Delete particular address book data by token
@DeleteMapping("/deletebytoken/{token}")
public ResponseEntity<ResponseDTO> deleteByToken(@PathVariable String token) {
    addressService.deletePersonRecordByToken(token);
    ResponseDTO response = new ResponseDTO(" Data Deleted Successfully "+token,true);

    return new ResponseEntity<>(response, HttpStatus.OK);
}
}
