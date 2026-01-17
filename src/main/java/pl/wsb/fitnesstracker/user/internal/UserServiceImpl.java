package pl.wsb.fitnesstracker.user.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(final User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }


    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public void deleteUser(Long userId) {
        Optional<User> userToBeDeleted = userRepository.findById(userId);
        if (!userToBeDeleted.isPresent()) {
            throw new IllegalArgumentException("User has no DB ID!");
        }

        userRepository.delete(userToBeDeleted.get());
        }


    public interface UserService {
        List<User> findUsersOlderThan(LocalDate date);
    }

    public List<User> findUsersOlderThan(LocalDate date) {
        return userRepository.findAll().stream().filter(user -> user.getBirthdate().isBefore(date)).toList();
    }

    @Override
    public User updateUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));

        // Create a new user with updated data
        User updatedUser = new User(
                user.getFirstName() != null ? user.getFirstName() : existingUser.getFirstName(),
                user.getLastName() != null ? user.getLastName() : existingUser.getLastName(),
                user.getBirthdate() != null ? user.getBirthdate() : existingUser.getBirthdate(),
                user.getEmail() != null ? user.getEmail() : existingUser.getEmail()
        );

        // Delete the old user and save the new one with the same ID
        userRepository.deleteById(userId);
        userRepository.flush();

        // We need to manually set the ID since we're creating a new object
        return userRepository.save(updatedUser);
    }



}