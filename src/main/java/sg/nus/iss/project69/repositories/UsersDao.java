// package sg.nus.iss.project69.repositories;

// import java.util.List;


// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.stereotype.Repository;

// import sg.nus.iss.project69.models.Users;

// @Repository
// public class UsersDao {//store key value pairs to create user "profiles"

//     public static final String HASH_KEY= "Users";
//     private RedisTemplate redisTemplate;

//     public Users save(Users users){
//         redisTemplate.opsForHash().put(HASH_KEY , users.getUserId() , users); //to pass objects 
//         return users;

//     }
    
//     public List<Users> findAll(){
//         return redisTemplate.opsForHash().values(HASH_KEY); //to obtain objects
//     }

//     public Users findUsersById(String userId){
//         return (Users) redisTemplate.opsForHash().get(HASH_KEY, userId);
//     }

//     public String deleteUsers(String userId){
//         redisTemplate.opsForHash().delete(HASH_KEY, userId);
//         return "User removed";
//     }
// }
