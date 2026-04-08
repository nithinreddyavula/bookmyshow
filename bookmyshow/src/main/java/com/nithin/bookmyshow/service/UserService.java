package com.nithin.bookmyshow.service;



import com.nithin.bookmyshow.dto.LoginRequest;
import com.nithin.bookmyshow.dto.RegisterRequest;
import com.nithin.bookmyshow.model.User;
import com.nithin.bookmyshow.repository.UserRepository;
import com.nithin.bookmyshow.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String registerUser(RegisterRequest request) {
        if (userRepository.findByUserEmail(request.getUserEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user=new User();
        user.setUserName(request.getUserName());
        user.setUserEmail(request.getUserEmail());
        user.setUserPassword(hashedPassword);
        userRepository.save(user);
        return  "Successfully Registered";

    }
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    public String loginUser(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid email or password");
        }

        return jwtUtil.generateToken(request.getUserEmail());
    }


}
