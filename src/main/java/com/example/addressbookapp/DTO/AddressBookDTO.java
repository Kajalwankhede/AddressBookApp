package com.example.addressbookapp.DTO;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookDTO {
@Nullable
private int id;
@NonNull
 @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid Name")
   private String name;
@NotBlank(message = "Address should not be empty")
    private String address;
@NotBlank(message = "City should not be empty")
    private String city;
 @NotBlank(message = "State should not be empty")
    private String state;
 @Pattern(regexp="^[1-9]{1}[0-9]{5}$" ,message = "Zip should be 6 digits")
    private String zip;
 @Pattern(regexp="^[6-9][0-9]{9}$",message = "Phone number should be 10 digits")
    private String phoneNumber;
@NotBlank(message = "Email should not be empty")
    @Pattern(regexp= "^[a-zA-Z0-9]{3,}([\\\\.\\\\+\\\\-]?[a-zA-Z0-9]{3,})?[@][A-Za-z0-9]{1,}[.][A-Za-z]{2,4}[,]?([.][A-Za-z]{2,4}[.]?)?$",message="Email should not be empty")
    private String email;

}
