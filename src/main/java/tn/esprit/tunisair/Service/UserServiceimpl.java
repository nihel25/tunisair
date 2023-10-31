package tn.esprit.tunisair.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.AuthRequestResponse.AuthenticationRequest;
import tn.esprit.tunisair.AuthRequestResponse.AuthenticationResponse;
import tn.esprit.tunisair.AuthRequestResponse.RegistrationRequest;
import tn.esprit.tunisair.ConfigSecurite.JwtUtils;
import tn.esprit.tunisair.DTO.*;
import tn.esprit.tunisair.Repository.AdminRepository;
import tn.esprit.tunisair.Repository.UserRepository;
import tn.esprit.tunisair.entity.*;

import javax.persistence.EntityNotFoundException;
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
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " not found"));
        userRepository.deleteById(id);
    }


    @Override
    public AuthenticationResponse register(RegistrationRequest request) {


        User user = new User();

        if (request instanceof CoordinateurEntrepriseDTO) {
            user = new CoordinateurEntreprise();
            user = CoordinateurEntrepriseDTO.toentity((CoordinateurEntrepriseDTO) request);
            user.setRole(UserRole.coordinateurentreprise);
        }
        if (request instanceof RecreteurDTO) {
            user = new Recruteur();

            user = RecreteurDTO.toentity((RecreteurDTO) request);
            user.setRole(UserRole.recruteur);
        }


        if (request instanceof AdminDTO) {
            user = new Admin();

            user = AdminDTO.toEntity((AdminDTO) request);
            user.setRole(UserRole.ADMIN);
        }


        if (request instanceof CoordinateurFormationDTO) {
            user = new CoordinateurFormation();
            user = CoordinateurFormationDTO.toEntity((CoordinateurFormationDTO) request);
            user.setRole(UserRole.coordinateurformation);
        }
      if (request instanceof ClientDTO) {
            user = new Client();
          user = ClientDTO.toEntity((ClientDTO) request);
          user.setRole(UserRole.client);
      }


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var savedUser = userRepository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId()); // optional
        claims.put("fullName", savedUser.getFullname()); // optional
        claims.put("prenom", savedUser.getPrenom());
        claims.put("email", savedUser.getEmail());
       // generate a JWT token
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



