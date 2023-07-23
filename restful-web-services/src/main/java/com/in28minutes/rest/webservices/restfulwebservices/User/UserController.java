package com.in28minutes.rest.webservices.restfulwebservices.User;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUser() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveByID(@PathVariable int id) {

        User user = userDaoService.findByID(id);
        if (user == null){
            throw new UserNotFoundException("id: "+id);
        }
        return user;
    }

//    Create User by pass data in body\
//    URI คือข้อมูลที่ใช้ระบุตัวตนของทรัพยากร (resource)
//    โดยที่ resource อาจจะเป็น data, image, file, service, website, หนังสือ, คน หรือ หน่วยงาน ก็ได้
//    เรียกว่าอะไรก็ได้ เอามาระบุตัวตน (identify) โดยทำให้ออกมาเป็นรูปแบบเดียวกัน (uniform)
//    URL คือ URI ที่บอกวิธีการที่จะเข้าถึง/ติดต่อ (locate) resource มาให้ด้วย คือมีรายละเอียดขึ้นไปอีก
//    ส่วน URN คือ URI ที่เป็นการระบุชื่อ เช่น หนังสือ บทความ ชื่อหน่วยงาน
//    URN คือชื่อ
//    URL คือที่อยู่และวิธีติดต่อ
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User userResp = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userResp.getId())
                .toUri();
        return ResponseEntity.created(null).build();

    }

    @DeleteMapping("/users/{id}")
    public void deleteByID(@PathVariable int id) {
        userDaoService.deleteByID(id);
    }

}
