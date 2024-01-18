package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
public class ApiController {
    private final TutorialRepository tutorialRepository;
    public ApiController(TutorialRepository tutorialRepository)
    {
        this.tutorialRepository = tutorialRepository;
    }
    @GetMapping("/tutorials") //curl "localhost:8080/tutorials?age=21&title=f"
    public List<User_User> getAllTutorials(@RequestParam(required = false) String title, @RequestParam(required = false, defaultValue = "-1") int age) {

        List<User> users = new ArrayList<User>();
        if (title == null) {
            tutorialRepository.findAll().forEach(users::add);
        } else {
            tutorialRepository.findByUsernameContaining(title).forEach(users::add);
        }
        List<User_User>to_return = new ArrayList<>();
        for(User user : users){
            if(age != -1) {
                int min_age = Math.min(age - 5, 0);
                int max_age = age + 5;
                if (user.getAge() >= min_age && user.getAge() <= max_age) {
                    to_return.add(new User_User(user.getId(), user.getUsername(), user.getAge()));
                }
            }else{
                to_return.add(new User_User(user.getId(), user.getUsername(), user.getAge()));
            }
        }
        return to_return;
    }
    @GetMapping("/tutorials/{id}") //curl localhost:8080/tutorials/1
    public User_User getTutorialById(@PathVariable("id") long id) {
        Optional<User> tutorialData =
                tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            return new User_User(tutorialData.get().getId(), tutorialData.get().getUsername(), tutorialData.get().getAge());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/tutorials") //curl -X POST -H "Content-Type: application/json" -d "{\"username\": \"fedor\", \"password\": \"ds\", \"age\": 24, \"repeatPassword\": \"ds\"}" localhost:8080/tutorials
    public User createTutorial(@RequestBody User_Get user) {
        if(!user.checkPassword()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<User> userData = tutorialRepository.findByUsernameContaining(user.getUsername());
        for(User t : userData){
            if (t.getUsername().equals(user.getUsername())){
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        }
        return tutorialRepository
                .save(new User(user.getUsername(),
                        user.getPassword(), user.getAge()));
    }
    @PutMapping("/tutorials/{id}") //curl -X PUT -H "Content-Type: application/json" -d "{\"username\": \"fedor\", \"password\": \"ds\", \"age\": 24, \"repeatPassword\": \"ds\"}" localhost:8080/tutorials/1
    public User updateTutorial(@PathVariable("id") long id,
                               @RequestBody User_Get user) {
        if(!user.checkPassword()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Optional<User> tutorialData =
                tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            User _user = tutorialData.get();
            _user.setUsername(user.getUsername());
            _user.setPassword(user.getPassword());
            _user.setAge(user.getAge());
            return tutorialRepository.save(_user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/tutorials/{id}") //curl -X DELETE localhost:8080/tutorials/1
    public HttpStatus deleteTutorial(@PathVariable("id") long id) {
        Optional<User> tutorialData =
                tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            tutorialRepository.deleteById(id);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return HttpStatus.NO_CONTENT;
    }
    @DeleteMapping("/tutorials") //curl -X DELETE localhost:8080/tutorials
    public HttpStatus deleteAllTutorials() {
        tutorialRepository.deleteAll();
        return HttpStatus.NO_CONTENT;
    }
}