package tn.esprit.tunisair.configsecurite;


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
import tn.esprit.tunisair.repository.UserRepository;

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







                .antMatchers(HttpMethod.GET, "Materiel/session/{sessionId}").hasRole("coordinateurformation")





                .antMatchers("/avis/listeavis").permitAll()











//admin formateur

                .antMatchers(HttpMethod.GET, "formateur/listeformateur", "/lister", "Option/lister").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/auth/delete/{id}", "formateur/supprimer/{id}", "encadreursatge/supprimer/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "formateur/saveformateur", "encadreursatge/saveencadreur", "/save", "Option/ajouteroption").hasRole("ADMIN")

////////////////////////////////////////////////////////////kafka

                ///client envoyer reclamation

                .antMatchers(HttpMethod.GET, "/stagiaire/listerstagiaire", "/recherher/{id}", "/listecertificat", "/stage/listestage").hasRole("recruteur")
                .antMatchers(HttpMethod.DELETE, "/stagiaire/supprimer/{id}", "/delete/{id}", "/stage/supprimer/{id}").hasRole("recruteur")
                .antMatchers(HttpMethod.POST, "/stagiaire/saveOrUpdate", "/savecertif", "/savestage").hasRole("recruteur")

                .antMatchers(HttpMethod.GET, "/lister", "profil/user-profiles", "listersalle", "/recherher/{id}", "formation/recherher/{id}", "avis/recherher/{id}", "listesession", "formation/lister", "listermateriel").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.POST, "DemandeFormationclient/saveOrUpdate", "addsalle", "addformation", "email/send-email", "addmateriel").hasRole("coordinateurformation")
                .antMatchers(HttpMethod.DELETE, "/supprimer/{id}", "/delete/{id}", "/deletemateriel/{id}").hasRole("coordinateurformation")



//////////////////////////////////kakfe


//////consulter reclamation
                .antMatchers(HttpMethod.POST, "Reclamation/saveOrUpdate").hasRole("client")
                .antMatchers(HttpMethod.POST, "api/payment").hasRole("client")

                .antMatchers(HttpMethod.POST, "avis/saveOrUpdate").hasRole("client")
                .antMatchers(HttpMethod.GET, "avis/analyzeSentiments").hasRole("client")
                .antMatchers(HttpMethod.GET, "formation/formationbydateandtype").hasRole("client")
                .antMatchers(HttpMethod.POST  ,"avis/saveavis").hasRole("client")
                .antMatchers(HttpMethod.GET  ,"avis/recherher/{id}").hasRole("client")
                .antMatchers(HttpMethod.GET  ,"/findAllreclamation").hasRole("client")

////cordinateur entreprise personnel

                .antMatchers(HttpMethod.GET, "/Personnel/personnel", "findAllcatalogue").hasRole("coordinateurentreprise")
                .antMatchers(HttpMethod.POST, "profil/profiluser", "Personnel/saveOrUpdate", "Personnel/charger-csv").hasRole("coordinateurentreprise")
                .antMatchers(HttpMethod.DELETE, "/Personnel/delete/{id}").hasRole("coordinateurentreprise")


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