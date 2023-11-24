package tn.esprit.tunisair.service;

import tn.esprit.tunisair.authrequestresponse.AuthenticationRequest;
import tn.esprit.tunisair.authrequestresponse.AuthenticationResponse;
import tn.esprit.tunisair.authrequestresponse.RegistrationRequest;
import tn.esprit.tunisair.dto.UserDTO;
import tn.esprit.tunisair.entity.User;

import java.util.List;


public interface UserService {
    User findById(Long id);
    void delete(Long id);
  AuthenticationResponse register(RegistrationRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

     UserDTO recherch(Long id);
    List<UserDTO> findAll();


}
