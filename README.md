# Spring Security Basic Authentication Flow

## Overview
This document explains the complete flow of Basic Authentication in Spring Security, from receiving an HTTP request to returning a response.

---

## 🔐 Authentication Flow Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│  1. HTTP Request with Basic Auth Header                        │
│     Authorization: Basic base64(email:password)                 │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│  2. SecurityFilterChain                                         │
│     ├─ Configured via HttpSecurity                             │
│     └─ Intercepts incoming requests                            │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│  3. BasicAuthenticationFilter                                   │
│     ├─ Reads Authorization header                              │
│     ├─ Decodes Base64 credentials                              │
│     └─ Extracts username and password                          │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│  4. UsernamePasswordAuthenticationToken (Unauthenticated)       │
│     └─ Created with extracted credentials                      │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│  5. AuthenticationManager                                       │
│     └─ Delegates to appropriate AuthenticationProvider         │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│  6. AuthenticationProvider (DaoAuthenticationProvider)          │
│     └─ Handles the actual authentication logic                 │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│  7. UserDetailsService                                          │
│     ├─ loadUserByUsername(email)                               │
│     ├─ Fetches user from database                              │
│     └─ Returns CustomUserDetails object                        │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│  8. PasswordEncoder                                             │
│     ├─ Retrieves raw password from request                     │
│     ├─ Compares with hashed password from DB                   │
│     └─ Validates password match                                │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│  9. ✅ Authentication SUCCESS                                   │
│     ├─ Authorities loaded (e.g., ROLE_USER)                    │
│     ├─ Authentication object marked as authenticated           │
│     └─ Principal details populated                             │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│  10. SecurityContextHolder                                      │
│      ├─ Stores Authentication in SecurityContext               │
│      └─ Available for the duration of this request             │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│  11. Authorization Check                                        │
│      ├─ @PreAuthorize annotations evaluated                    │
│      ├─ URL-based security rules checked                       │
│      └─ Role/authority verification                            │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│  12. Controller Method Executes                                 │
│      └─ Business logic runs (@GetMapping, @PostMapping, etc.)  │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│  13. 🎉 Response Returned to Client                             │
└─────────────────────────────────────────────────────────────────┘
```

---

## 📋 Detailed Step-by-Step Explanation

### Step 1: HTTP Request
- Client sends request with `Authorization` header
- Format: `Authorization: Basic base64(email:password)`
- Example: `Authorization: Basic dXNlckBleGFtcGxlLmNvbTpwYXNzd29yZDEyMw==`

### Step 2: SecurityFilterChain
- Spring Security's filter chain intercepts the request
- Configured through `HttpSecurity` in your security configuration
- Determines which filters should process this request

### Step 3: BasicAuthenticationFilter
- Detects the presence of Basic Auth header
- Decodes the Base64-encoded credentials
- Extracts username (email) and password as plain text

### Step 4: UsernamePasswordAuthenticationToken
- Creates an **unauthenticated** authentication token
- Contains the username and password
- Passed to the authentication manager for verification

### Step 5: AuthenticationManager
- Acts as a coordinator for authentication
- Delegates to the appropriate `AuthenticationProvider`
- Typically uses `ProviderManager` implementation

### Step 6: AuthenticationProvider
- `DaoAuthenticationProvider` is commonly used
- Responsible for validating the credentials
- Coordinates between UserDetailsService and PasswordEncoder

### Step 7: UserDetailsService
- Custom implementation (e.g., `CustomUserDetailsService`)
- Loads user from database using `loadUserByUsername(email)`
- Returns `UserDetails` object containing user information

### Step 8: PasswordEncoder
- Compares the raw password from the request
- With the encoded password stored in the database
- Uses BCrypt, Argon2, or other encoding algorithms

### Step 9: Authentication Success
- If credentials match, authentication is successful
- User authorities/roles are loaded (e.g., `ROLE_USER`, `ROLE_ADMIN`)
- Authentication object is marked as authenticated

### Step 10: SecurityContextHolder
- Stores the authenticated `Authentication` object
- Available throughout the request lifecycle
- Can be accessed via `SecurityContextHolder.getContext().getAuthentication()`

### Step 11: Authorization Check
- Method-level security: `@PreAuthorize`, `@Secured`
- URL-level security: configured in `HttpSecurity`
- Checks if user has required roles/authorities

### Step 12: Controller Method Execution
- If authorized, the controller method runs
- Business logic executes
- Can access current user via `@AuthenticationPrincipal`

### Step 13: Response Returned
- HTTP response sent back to client
- May include data, status codes, and headers

---

## 🔄 Failure Scenario

If authentication fails at any step:

```
Authentication Failure
         │
         ▼
AuthenticationException Thrown
         │
         ▼
AuthenticationEntryPoint Triggered
         │
         ▼
HTTP 401 Unauthorized Response
         │
         ▼
WWW-Authenticate: Basic realm="..."
```

---

## 💡 Key Components

| Component | Purpose |
|-----------|---------|
| **SecurityFilterChain** | Configures security filters and rules |
| **BasicAuthenticationFilter** | Processes Basic Auth headers |
| **AuthenticationManager** | Manages authentication process |
| **AuthenticationProvider** | Performs actual authentication |
| **UserDetailsService** | Loads user-specific data |
| **PasswordEncoder** | Encodes and validates passwords |
| **SecurityContextHolder** | Stores authentication information |

---

## 📝 Example Configuration

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable());
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return new CustomUserDetails(user);
    }
}
```

---

## 🎯 Best Practices

1. **Always use HTTPS** - Basic Auth sends credentials in Base64 (not encrypted)
2. **Use strong password encoding** - BCrypt, Argon2, or SCrypt
3. **Implement proper exception handling** - Return meaningful error messages
4. **Add rate limiting** - Prevent brute force attacks
5. **Use custom UserDetails** - Extend with application-specific user data
6. **Configure CORS properly** - If building REST APIs
7. **Set appropriate session policies** - Stateless for REST APIs

---

## 🚀 Testing Basic Auth

### Using cURL
```bash
curl -u email@example.com:password123 http://localhost:8080/api/users
```

### Using Postman
1. Select "Authorization" tab
2. Choose "Basic Auth" type
3. Enter username and password
4. Send request

### Using JavaScript (Fetch API)
```javascript
const credentials = btoa('email@example.com:password123');
fetch('http://localhost:8080/api/users', {
    headers: {
        'Authorization': `Basic ${credentials}`
    }
})
.then(response => response.json())
.then(data => console.log(data));
```

---

## 📚 Additional Resources

- [Spring Security Documentation](https://docs.spring.io/spring-security/reference/index.html)
- [Basic Authentication RFC 7617](https://tools.ietf.org/html/rfc7617)
- [OWASP Authentication Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Authentication_Cheat_Sheet.html)

---

**Note:** Basic Authentication is simple but should only be used over HTTPS in production environments. Consider using JWT or OAuth2 for more complex authentication requirements.
