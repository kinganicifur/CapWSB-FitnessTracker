package pl.wsb.fitnesstracker.user.internal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.time.LocalDate;
import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("{id}")
    public UserDto getAllInformationForUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    @GetMapping("/email")
    public List<UserDto> getAllInformationForUserByEmail(@RequestParam String email) {
        System.out.println("Searching by email: " + email);
        return List.of(userMapper.toDto(userService.getUserByEmail(email).get()));
    }

    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long userId){

        userService.deleteUser(userId);

    }
//
//    @GetMapping("/older-than")
//    public List<User> findUsersOlderThan(@RequestParam int age) {
//        return userService.findUsersOlderThan(age);
//    }

    @GetMapping("/older/{time}")
    public List<User> getUsersOlderThan(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate time
    ) {
        return userService.findUsersOlderThan(time);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email()
        );
        User updatedUser = userService.updateUser(id, user);
        return userMapper.toDto(updatedUser);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email()
        );
        User createdUser = userService.createUser(user);
        return userMapper.toDto(createdUser);
    }






}

