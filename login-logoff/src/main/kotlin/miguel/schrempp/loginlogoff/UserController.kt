package miguel.schrempp.loginlogoff

import com.fasterxml.jackson.databind.util.JSONPObject
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.Serializable

@RestController
@RequestMapping("/user")
class UserController {
    var arraylist = ArrayList<User>()

    @GetMapping
    fun getAllUsers() : Serializable? {
        if (arraylist.isEmpty()) {
            return ResponseEntity.status(200).body("No users found!").body
        }
        return ResponseEntity.status(200).body(arraylist).body
    }

    @PostMapping("/add")
    fun addUser(@RequestBody newUser: User): String? {
        if (newUser.Email.length < 5 || newUser.Name.length < 5 || newUser.Password.length < 5) {
            return ResponseEntity.status(406).body("Email or Name or Password must have at least 5 characters!").body
        }
        for (user in arraylist) {
            if (user.Username == newUser.Username || user.Email == newUser.Email) {
                return ResponseEntity.status(406).body("Username already exists!").body
            }
        }
        arraylist.add(newUser)
        return ResponseEntity.status(200).body("User added!").body
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody user: User): String? {
        for (user in arraylist) {
            if (user.Username == user.Username && user.Password == user.Password) {
                user.Logged = true
                return ResponseEntity.status(200).body("User logged!").body
            }
        }
        return ResponseEntity.status(406).body("User not found!").body
    }

    @PostMapping("/logout")
    fun logoutUser(@RequestBody user: User): String? {
        for (user in arraylist) {
            if (user.Username == user.Username && user.Password == user.Password && user.Logged == true) {
                user.Logged = false
                return ResponseEntity.status(200).body("User logged out!").body
            }
        }
        return ResponseEntity.status(406).body("User not found!").body
    }

    @DeleteMapping("/delete")
    fun deleteUser(@RequestBody user: User): String? {
        for (user in arraylist) {
            if (user.Email == user.Email && user.Password == user.Password) {
                arraylist.remove(user)
                return ResponseEntity.status(200).body("User deleted!").body
            }
        }
        return ResponseEntity.status(406).body("User not found!").body
    }
}