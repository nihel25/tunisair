package tn.esprit.tunisair.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.authrequestresponse.AuthenticationRequest;
import tn.esprit.tunisair.authrequestresponse.AuthenticationResponse;
import tn.esprit.tunisair.authrequestresponse.RegistrationRequest;
import tn.esprit.tunisair.configsecurite.JwtUtils;
import tn.esprit.tunisair.dto.*;
import tn.esprit.tunisair.entity.*;
import tn.esprit.tunisair.repository.AdminRepository;
import tn.esprit.tunisair.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceimpl implements UserService {
  private final PasswordEncoder passwordEncoder;
   private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public User findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElse(null);
    }

    @Override
    public void delete(Long id) {

        userRepository.deleteById(id);
    }


    @Override
    public AuthenticationResponse register(RegistrationRequest request) {


        User user = new User();

        if (request instanceof CoordinateurEntrepriseDTO) {

            user = CoordinateurEntrepriseDTO.toentity((CoordinateurEntrepriseDTO) request);
            user.setRole(UserRole.COORDINATEURENTREPRISE);
        }
        if (request instanceof RecreteurDTO) {


            user = RecreteurDTO.toentity((RecreteurDTO) request);
            user.setRole(UserRole.RECRUTEUR);
        }


        if (request instanceof AdminDTO) {


            user = AdminDTO.toEntity((AdminDTO) request);
            user.setRole(UserRole.ADMIN);
        }


        if (request instanceof CoordinateurFormationDTO) {

            user = CoordinateurFormationDTO.toEntity((CoordinateurFormationDTO) request);
            user.setRole(UserRole.COORDINATEURFORMATION);
        }
      if (request instanceof ClientDTO) {

          user = ClientDTO.toEntity((ClientDTO) request);
          user.setRole(UserRole.CLIENT);
      }


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var savedUser = userRepository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId()); // optional
        claims.put("fullName", savedUser.getFullname()); // optional
        claims.put("prenom", savedUser.getPrenom());
        claims.put("email", savedUser.getEmail());

        String token = jwtUtils.generateToken(savedUser, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }




    public UserDTO recherch(Long id) {
        Optional<User> optionalstagair =userRepository.findById(id);
        if (optionalstagair.isPresent()) {
            User stagiaire=optionalstagair.get();
            return UserDTO.fromEntity(stagiaire);
        }
        else
        {
            return null;
        }}


















    //
   @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final User user = userRepository.findByEmail(request.getEmail()).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId()); // optional
        claims.put("fullName", user.getFullname()); // optional
       claims.put("prenom", user.getPrenom());
       claims.put("email", user.getEmail());
        // generate a JWT token
        String token = jwtUtils.generateToken(user, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }





    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }
    }



