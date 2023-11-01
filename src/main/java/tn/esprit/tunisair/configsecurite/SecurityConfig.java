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
import tn.esprit.tunisair.entity.UserRole;
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












                .antMatchers("/avis/listeavis").permitAll()











//a

                .antMatchers(HttpMethod.GET, "formateur/listeformateur", "/lister", "Option/lister").hasRole(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/v1/auth/delete/{id}", "formateur/supprimer/{id}", "encadreursatge/supprimer/{id}").hasRole(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.POST, "formateur/saveformateur", "encadreursatge/saveencadreur", "/save", "Option/ajouteroption").hasRole(UserRole.ADMIN.name())

////////////////////////////////////////////////////////////kafka

                ///client envoyer reclamation

                .antMatchers(HttpMethod.GET, "/stagiaire/listerstagiaire", "/recherher/{id}", "/listecertificat", "/stage/listestage").hasRole(UserRole.RECRUTEUR.name())
                .antMatchers(HttpMethod.DELETE, "/stagiaire/supprimer/{id}", "/delete/{id}", "/stage/supprimer/{id}").hasRole(UserRole.RECRUTEUR.name())
                .antMatchers(HttpMethod.POST, "/stagiaire/saveOrUpdate", "/savecertif", "/savestage").hasRole(UserRole.RECRUTEUR.name())

                .antMatchers(HttpMethod.GET, "Materiel/session/{sessionId}").hasRole(UserRole.COORDINATEURFORMATION.name())
                .antMatchers(HttpMethod.GET, "/lister", "profil/user-profiles", "listersalle", "/recherher/{id}", "formation/recherher/{id}", "avis/recherher/{id}", "listesession", "formation/lister", "listermateriel").hasRole(UserRole.COORDINATEURFORMATION.name())
                .antMatchers(HttpMethod.POST, "DemandeFormationclient/saveOrUpdate", "addsalle", "addformation", "email/send-email", "addmateriel").hasRole(UserRole.COORDINATEURFORMATION.name())
                .antMatchers(HttpMethod.DELETE, "/supprimer/{id}", "/delete/{id}", "/deletemateriel/{id}").hasRole(UserRole.COORDINATEURFORMATION.name())



//////////////////////////////////kakfe


//////consulter reclamation
                .antMatchers(HttpMethod.POST, "Reclamation/saveOrUpdate",
                        "api/payment",
                        "avis/saveOrUpdate",
                        "avis/saveavis").hasRole("client")
                .antMatchers(HttpMethod.GET, "avis/analyzeSentiments",
                        "formation/formationbydateandtype",
                        "avis/recherher/{id}",
                        "/findAllreclamation").hasRole("client")


////cordinateur entreprise personnel

                .antMatchers(HttpMethod.GET, "/Personnel/personnel", "findAllcatalogue").hasRole(UserRole.COORDINATEURENTREPRISE.name())
                .antMatchers(HttpMethod.POST, "profil/profiluser", "Personnel/saveOrUpdate", "Personnel/charger-csv").hasRole(UserRole.COORDINATEURENTREPRISE.name())
                .antMatchers(HttpMethod.DELETE, "/Personnel/delete/{id}").hasRole(UserRole.COORDINATEURENTREPRISE.name())


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