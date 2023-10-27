package tn.esprit.tunisair.ConfigSecurite;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import tn.esprit.tunisair.Repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collections;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()
                .antMatchers(
                        "/**/auth/**",
                        "/**/formation/downloadImage/**",
                        "/**/stat/sentimentStats/**",
                        "/**/user/getcoordinateurformationid/**",
                        "/**/user/coordinateurformationusers/**",
                        "/**/catalogue/findAllcatalogue/**",
                        "/**/chat.sendMessage/**",

                        "/**/chat.addUser/**",


                        "swagger",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html"
                )
                .permitAll()
              //  Addmin barka 3andi 7a9 yfasa5
                //admin encadreurReclamation
                .antMatchers(HttpMethod.GET, "Materiel/session/{sessionId}").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.GET, "avis/analyzeSentiments").hasRole("client")
                .antMatchers(HttpMethod.GET, "/chat.addUser").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.POST, "/chat.sendMessage").hasRole("coordinateurformation")

                .antMatchers(HttpMethod.GET, "send/conversation").hasRole("coordinateurentreprise")
                .antMatchers(HttpMethod.GET, "send/conversation").hasRole("coordinateurformation")


////encadreur

//.antMatchers(HttpMethod.GET, "/lister").hasRole("ADMIN")
               // .antMatchers(HttpMethod.DELETE, "/supprimer/{id}").hasRole("ADMIN")
              //  .antMatchers(HttpMethod.POST, "/saveOrUpdate").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/lister").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.POST, "file/upload").hasRole("coordinateurformation")

                .antMatchers(HttpMethod.GET, "profil/user-profiles").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.DELETE, "/api/v1/auth/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "avis/saveOrUpdate").hasRole("client")
                .antMatchers("/avis/listeavis").permitAll()
                .antMatchers(HttpMethod.POST, "/filepdf/uploadPDFs").hasRole("coordinateurentreprise")


                .antMatchers(HttpMethod.POST, "profil/profiluser").hasRole("coordinateurentreprise")

                .antMatchers(HttpMethod.GET, "chat/messages").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.GET, "chat/messages").hasRole("coordinateurentreprise")

                .antMatchers(HttpMethod.GET, "chat/allmessages").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.GET, "chat/allmessages").hasRole("coordinateurentreprise")

                .antMatchers(HttpMethod.GET, "formation/formationbydateandtype").hasRole("client")




//admin formateur

                .antMatchers(HttpMethod.GET, "formateur/listeformateur").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "formateur/supprimer/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "formateur/saveOrUpdate").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "encadreursatge/saveOrUpdatee").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/save").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/lister").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/lister").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.DELETE, "encadreursatge/supprimer/{id}").hasRole("ADMIN")


                //////////////////option formateur
                .antMatchers(HttpMethod.POST, "Option/ajouteroption").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "Option/lister").hasRole("ADMIN")
////////////////////////////////////////////////////////////kafka
                .antMatchers(HttpMethod.POST, "formationssssss/send").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.GET, "formationssssss/send").hasRole("coordinateurentreprise")

                .antMatchers(HttpMethod.POST, "entreprise/send").hasRole("coordinateurentreprise")
                .antMatchers(HttpMethod.GET, "entreprise/send").hasRole("coordinateurformation")

                ///client envoyer reclamation
                .antMatchers(HttpMethod.POST, "Reclamation/saveOrUpdate").hasRole("client")
                .antMatchers(HttpMethod.POST, "api/payment").hasRole("client")
                .antMatchers(HttpMethod.POST, "DemandeFormationclient/saveOrUpdate").hasRole("coordinateurformation")
               // .antMatchers(HttpMethod.POST, "/payment/charge").hasRole("client")
                  /////recruteur stagiaire
                .antMatchers(HttpMethod.GET, "stagiaire/listerstagiaire").hasRole("recruteur")
                .antMatchers(HttpMethod.DELETE, "stagiaire/supprimer/{id}").hasRole("recruteur")
                .antMatchers(HttpMethod.POST, "stagiaire/saveOrUpdate").hasRole("recruteur")


                /////recruteur cetififcat

                .antMatchers(HttpMethod.GET, "/recherher/{id}").hasRole("recruteur")
                .antMatchers(HttpMethod.GET, "/listecertificat").hasRole("recruteur")
                .antMatchers(HttpMethod.DELETE, "/delete/{id}").hasRole("recruteur")
                .antMatchers(HttpMethod.POST, "/saveOrUpdate").hasRole("recruteur")



////// recruteur stage
                .antMatchers(HttpMethod.GET, "stage/listestage").hasRole("recruteur")

                .antMatchers(HttpMethod.DELETE, "stage/supprimer/{id}").hasRole("recruteur")
                .antMatchers(HttpMethod.POST, "/savestage").hasRole("recruteur")

                /////coordinateur formation formation
                .antMatchers(HttpMethod.GET  ,"/lister").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.DELETE  ,"/delete/{id}").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.POST, "/saveOrUpdate").hasRole("coordinateurformation")




//////////////////////////////////kakfe




//consulter demande client
                .antMatchers(HttpMethod.GET, "/listerdemande").hasRole("coordinateurformation")
        //supprimer demande client
                .antMatchers(HttpMethod.DELETE  ,"/supprimer/{id}").hasRole("coordinateurformation")


                /////coordinateur formation salle
                .antMatchers(HttpMethod.GET  ,"listersalle").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.DELETE  ,"/delete/{id}").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.POST, "/saveOrUpdate").hasRole("coordinateurformation")

                /////coordinateur formation session
                .antMatchers(HttpMethod.GET  ," /recherher/{id}").hasRole("coordinateurformation")

                .antMatchers(HttpMethod.GET  ,"formation/recherher/{id}").hasRole("coordinateurformation")

                .antMatchers(HttpMethod.GET  ,"avis/recherher/{id}").hasRole("coordinateurformation")



                .antMatchers(HttpMethod.GET  ,"listesession").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.DELETE  ,"/delete/{id}").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.POST, "/saveOrUpdate").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.GET  ,"formation /lister").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.POST, "email/send-email").hasRole("coordinateurformation")
////////////////////////////////////////////////coordinateur formation archive

                /////coordinateur formation materiel
                .antMatchers(HttpMethod.GET  ,"/lister").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.DELETE  ,"/delete/{id}").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.POST, "/saveOrUpdate").hasRole("coordinateurformation")

                /////coordinateur formation catalogue
                .antMatchers(HttpMethod.GET  ,"catalogue/lister").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.DELETE  ,"/delete/{id}").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.POST, "/saveOrUpdate").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.GET  ,"/lister").hasRole("coordinateurentreprise")
                .antMatchers(HttpMethod.GET  ,"/lister").hasRole("client")
                .antMatchers(HttpMethod.GET  ,"catalogue/recherher/{id}").hasRole("coordinateurformation")



//////consulter reclamation
                .antMatchers(HttpMethod.POST  ,"avis/saveOrUpdate").hasRole("client")
                .antMatchers(HttpMethod.GET  ,"avis/recherher/{id}").hasRole("client")
                .antMatchers(HttpMethod.GET  ,"/findAllreclamation").hasRole("client")
                ////cordinateur enreprise envoyer demande et consulter catalogue
                .antMatchers(HttpMethod.GET  ,"findAllcatalogue").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.DELETE  ,"delete/{id}").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.POST, "/saveOrUpdate").hasRole("coordinateurformation")

////cordinateur entreprise personnel
                .antMatchers(HttpMethod.GET  ,"/Personnel/personnel").hasRole("coordinateurentreprise")
                .antMatchers(HttpMethod.DELETE  ,"/Personnel/delete/{id}").hasRole("coordinateurentreprise")
                .antMatchers(HttpMethod.POST, "Personnel/saveOrUpdate").hasRole("coordinateurentreprise")
                .antMatchers(HttpMethod.POST, "Personnel/charger-csv").hasRole("coordinateurentreprise")
                .antMatchers(HttpMethod.GET  ,"findAllcatalogue").hasRole("coordinateurentreprise")


                .anyRequest()
                .authenticated()
              //  ay page ba3d permit all maye9blhech
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
                .authenticationProvider(authenticationProvider())

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .cors()
        ;
        return http.build();
    }



    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User does not exist in the database, please register"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
      //  return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }
 @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();

    config.setAllowCredentials(true);
   config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
    config.setAllowedHeaders(Arrays.asList("*"));
    config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PATCH", "OPTIONS"));

    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}