package bridgeLabz.quantity_measurement.controller;

import bridgeLabz.quantity_measurement.dto.request.GoogleAuthRequestDTO;
import bridgeLabz.quantity_measurement.dto.request.LoginRequestDTO;
import bridgeLabz.quantity_measurement.dto.request.PasswordChangeRequestDTO;
import bridgeLabz.quantity_measurement.dto.request.ProfileUpdateRequestDTO;
import bridgeLabz.quantity_measurement.dto.request.SignupRequestDTO;
import bridgeLabz.quantity_measurement.dto.response.AuthResponseDTO;
import bridgeLabz.quantity_measurement.dto.response.RestResponse;
import bridgeLabz.quantity_measurement.entity.UserEntity;
import bridgeLabz.quantity_measurement.repository.UserRepository;
import bridgeLabz.quantity_measurement.security.JwtTokenProvider;
import bridgeLabz.quantity_measurement.service.GoogleTokenVerifierService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final GoogleTokenVerifierService googleTokenVerifier;

    /**
     * POST /api/auth/signup — Register a new user
     */
    @PostMapping("/signup")
    public ResponseEntity<RestResponse<AuthResponseDTO>> signup(
            @Valid @RequestBody SignupRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(RestResponse.error("Email is already registered"));
        }

        UserEntity user = new UserEntity();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setProvider("LOCAL");

        userRepository.save(user);

        String token = jwtTokenProvider.generateToken(user.getEmail());

        AuthResponseDTO authResponse = new AuthResponseDTO(
                token, user.getEmail(), user.getFirstName(), user.getLastName());
        authResponse.setMessage("User registered successfully");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RestResponse.success("Signup successful", authResponse));
    }

    /**
     * POST /api/auth/login — Authenticate user and return JWT
     */
    @PostMapping("/login")
    public ResponseEntity<RestResponse<AuthResponseDTO>> login(
            @Valid @RequestBody LoginRequestDTO request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));

        String token = jwtTokenProvider.generateToken(authentication);

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        AuthResponseDTO authResponse = new AuthResponseDTO(
                token, user.getEmail(), user.getFirstName(), user.getLastName());

        return ResponseEntity.ok(
                RestResponse.success("Login successful", authResponse));
    }

    /**
     * POST /api/auth/google — Authenticate or register with Google
     */
    @PostMapping("/google")
    public ResponseEntity<RestResponse<AuthResponseDTO>> googleAuth(
            @Valid @RequestBody GoogleAuthRequestDTO request) {

        // Verify the Google ID token
        GoogleIdToken.Payload payload = googleTokenVerifier.verify(request.getCredential());
        if (payload == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(RestResponse.error("Invalid Google token"));
        }

        String email = payload.getEmail();
        String firstName = (String) payload.get("given_name");
        String lastName = (String) payload.get("family_name");

        if (firstName == null) firstName = "Google";
        if (lastName == null) lastName = "User";

        // Find or create user
        Optional<UserEntity> existingUser = userRepository.findByEmail(email);
        UserEntity user;

        if (existingUser.isPresent()) {
            user = existingUser.get();
            // If user exists but was LOCAL, update provider
            if ("LOCAL".equals(user.getProvider())) {
                user.setProvider("GOOGLE");
                userRepository.save(user);
            }
        } else {
            // Create new user from Google data
            user = new UserEntity();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
            user.setRole("USER");
            user.setProvider("GOOGLE");
            userRepository.save(user);
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());

        AuthResponseDTO authResponse = new AuthResponseDTO(
                token, user.getEmail(), user.getFirstName(), user.getLastName());
        authResponse.setMessage("Google authentication successful");

        return ResponseEntity.ok(
                RestResponse.success("Google login successful", authResponse));
    }

    /**
     * GET /api/auth/profile — Get current user profile
     */
    @GetMapping("/profile")
    public ResponseEntity<RestResponse<AuthResponseDTO>> getProfile(Authentication authentication) {
        UserEntity user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        AuthResponseDTO profile = new AuthResponseDTO(
                null, user.getEmail(), user.getFirstName(), user.getLastName());
        profile.setMessage("Profile fetched successfully");

        return ResponseEntity.ok(RestResponse.success("Profile fetched", profile));
    }

    /**
     * PUT /api/auth/profile — Update user profile (firstName, lastName, email)
     */
    @PutMapping("/profile")
    public ResponseEntity<RestResponse<AuthResponseDTO>> updateProfile(
            @Valid @RequestBody ProfileUpdateRequestDTO request,
            Authentication authentication) {

        UserEntity user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(RestResponse.error("Email is already taken"));
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        userRepository.save(user);

        String newToken = jwtTokenProvider.generateToken(user.getEmail());

        AuthResponseDTO authResponse = new AuthResponseDTO(
                newToken, user.getEmail(), user.getFirstName(), user.getLastName());
        authResponse.setMessage("Profile updated successfully");

        return ResponseEntity.ok(RestResponse.success("Profile updated", authResponse));
    }

    /**
     * PUT /api/auth/profile/password — Change password
     */
    @PutMapping("/profile/password")
    public ResponseEntity<RestResponse<String>> changePassword(
            @Valid @RequestBody PasswordChangeRequestDTO request,
            Authentication authentication) {

        UserEntity user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(RestResponse.error("Current password is incorrect"));
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(RestResponse.success("Password changed successfully", null));
    }
}
