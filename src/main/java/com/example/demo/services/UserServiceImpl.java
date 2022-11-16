package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.Role;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Log4j2
public class UserServiceImpl implements UserService, UserDetailsService {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            log.error("Customer not found in the database");
            throw new UsernameNotFoundException("Customer not found in the database");
        } else {
            log.info("Customer {} found in the database", email);
        }
        Collection<SimpleGrantedAuthority> aurhoities = new ArrayList<>();
        customer.getRoles().forEach(role ->
                aurhoities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), aurhoities);
    }

    @Override
    public Customer saveUser(Customer customer) {
        log.info("Saving new customer {} to the database", customer.getEmail());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.getRoles().add(new Role(null, "USER"));
        return customerRepository.save(customer);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saveing new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("Adding role {} to customer {}", roleName, email);
        Customer customer = customerRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        customer.getRoles().add(role);
    }

    @Override
    public Customer getUser(String email) {
        log.info("Fethcing user");
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> getUsers() {
        log.info("Fethcing all users");
        log.info(customerRepository.findAll());
        return customerRepository.findAll();
    }



}
