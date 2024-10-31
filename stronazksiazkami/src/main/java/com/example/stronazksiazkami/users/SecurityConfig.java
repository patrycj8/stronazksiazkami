package com.example.stronazksiazkami.users;



//@Configuration
//@EnableWebSecurity
public class SecurityConfig{

   /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/api/v1/users").hasRole("ADMIN") // Tylko admini mogą dodawać użytkowników
                .anyRequest().authenticated() // Pozostałe żądania wymagają uwierzytelnienia
                .and()
                .httpBasic(); // Użyj podstawowego uwierzytelnienia

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Bean do kodowania haseł
    }
*/
   /*@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/users/**").authenticated()  // Autoryzacja dla endpointów użytkowników
                        .anyRequest().permitAll()  // Pozwala na dostęp do pozostałych endpointów bez logowania
                );
        return http.build();
    }

    @Bean
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER");
    }*/
}
