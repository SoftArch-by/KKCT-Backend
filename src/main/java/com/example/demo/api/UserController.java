package com.example.demo.api;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.jwt.JWTHelper;
import com.example.demo.models.Customer;
import com.example.demo.models.Role;
import com.example.demo.models.SignupForm;
import com.example.demo.models.Transaction;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.bson.json.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TransactionRepository requestRepository;

    @GetMapping("/users")
    public ResponseEntity<List<Customer>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<Customer> saveUser(@RequestBody Customer customer) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/customer/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(customer));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserFrom form) {
        userService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupForm signupForm) {
        userService.saveUser(new Customer(null, signupForm.getEmail(), signupForm.getPassword(), signupForm.getCitizenID(), new ArrayList<>()));
        return ResponseEntity.ok("signup success");
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authHeader.replace("Bearer ", "");
                DecodedJWT validRefreshToken = JWTHelper.validateToken(refresh_token);
                String email = validRefreshToken.getSubject();
                Customer customer = userService.getUser(email);
                String access_token = JWTHelper.generateNewAccessToken(customer);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_msg", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @GetMapping("/getTransaction")
    public ResponseEntity<JsonObject> FindTransactionByCId(@RequestParam String email){
        String object_id = userService.getUser(email).getId();
        List<Transaction> res = requestRepository.findByCustomerID(object_id);
        String res_string="";
        for (Transaction t:res) {
            System.out.println(t);
            res_string += t.toString();
        }

        JsonObject o = new JsonObject(res_string);

        return new ResponseEntity<JsonObject>(o, HttpStatus.OK);


    }

}

@Data
class RoleToUserFrom {
    private String email;
    private String roleName;
}
