// package sg.nus.iss.project69.controllers;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import sg.nus.iss.project69.repositories.UsersDao;
// import sg.nus.iss.project69.models.Users;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;


// @RestController
// @RequestMapping("/users")
// public class UsersRestController {
//     @Autowired
//     private UsersDao udao;


// @GetMapping(value="path")
// public List<Users> getAllUsers(){
//     return udao.findAll();
// }

// @PostMapping
// public Users save(@RequestBody Users users){
//     return udao.save(users);
// }

// @GetMapping("/{userId}")
// public Users findUsers(@PathVariable String userId){
//     return udao.findUsersById(userId);
// }

// @DeleteMapping
// public String remove(@PathVariable String id){
//     return udao.deleteUsers(id);
// }

// }

