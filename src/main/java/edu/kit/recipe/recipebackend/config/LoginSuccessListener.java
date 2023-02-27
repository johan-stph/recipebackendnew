package edu.kit.recipe.recipebackend.config;

import edu.kit.recipe.recipebackend.entities.user.Customer;
import edu.kit.recipe.recipebackend.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;


/**
 * This class is used to create a new user entry in the database if the user is not already registered.
 * @author Johannes Stephan
 */
@Component
@RequiredArgsConstructor
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final CustomerRepository customerRepository;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) event.getAuthentication();
        String email = (String) authentication.getTokenAttributes().get("email");
        createNewUserEntryInDB(email);
    }

    private void createNewUserEntryInDB(String email) {
        if (customerRepository.findByEmail(email).isEmpty()) {
            Customer customer = new Customer();
            customer.setEmail(email);
            customerRepository.save(customer);
        }
    }
}
