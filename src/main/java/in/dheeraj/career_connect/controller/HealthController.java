package in.dheeraj.career_connect.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> getHealth(){
        return new ResponseEntity<>("UP and Running", HttpStatus.OK);
    }

    @GetMapping("/dummy")
    public ResponseEntity<String> dummyResponse(){
        return new ResponseEntity<>("Dummy Response", HttpStatus.OK);
    }
}
