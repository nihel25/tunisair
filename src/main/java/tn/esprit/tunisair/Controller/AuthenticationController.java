package tn.esprit.tunisair.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.AuthRequestResponse.AuthenticationRequest;
import tn.esprit.tunisair.AuthRequestResponse.AuthenticationResponse;
import tn.esprit.tunisair.DTO.*;
import tn.esprit.tunisair.Service.UserService;
import tn.esprit.tunisair.Service.UserServiceimpl;

import java.util.List;

@RestController
    @RequestMapping("/api/v1/auth")
    @RequiredArgsConstructor
    public class AuthenticationController {
//
        private final UserService service;
private final UserServiceimpl userServiceimpl;
        @PostMapping("/registerclient")
        public ResponseEntity<AuthenticationResponse> register(
                @RequestBody ClientDTO request
        ) {
            return ResponseEntity.ok(service.register(request));
        }


    @Secured( "ADMIN")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id ) {
        service.delete(id);
    }







    @Secured( "ADMIN")
    @GetMapping("/alluser")

    public List<UserDTO> liste() {

        return service.findAll();

    }
//
//
    @PostMapping("/registercoordinateuformation")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody CoordinateurFormationDTO request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/registercoordinateurentreprise")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody CoordinateurEntrepriseDTO request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/registerrecruteur")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RecreteurDTO request
    ) {
        return ResponseEntity.ok(service.register(request));
    }




    @PostMapping("/registeradmin")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AdminDTO request
    ) {
        return ResponseEntity.ok(service.register(request));
    }



        @PostMapping("/authenticate")
        public ResponseEntity<AuthenticationResponse> register(
                @RequestBody AuthenticationRequest request
        ) {
            return ResponseEntity.ok(service.authenticate(request));
        }








    @GetMapping("/recherher/{id}")
    public UserDTO recherch(@PathVariable Long id) {
        return userServiceimpl.recherch(id);
    }








    }
//
//