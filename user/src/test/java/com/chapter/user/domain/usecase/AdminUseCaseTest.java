package com.chapter.user.domain.usecase;

import com.chapter.user.domain.api.IPasswordEncodedServicePort;
import com.chapter.user.domain.exception.IsOlderUserException;
import com.chapter.user.domain.model.Role;
import com.chapter.user.domain.model.User;
import com.chapter.user.domain.spi.persistence.IRolePersistencePort;
import com.chapter.user.domain.spi.persistence.IUserPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdminUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Mock
    private IPasswordEncodedServicePort passwordEncodedServicePort;

    @InjectMocks
    private AdminUseCase adminUseCase;

    @Test
    void saveOwner_whenUserIsOlder_shouldSaveUser() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -30);
        Date dateOfBirth = cal.getTime();

        User user = new User(null, "John", "Doe", 12345678L, "+573001234567", "john.doe@example.com", dateOfBirth, null, "password123", null);
        Role ownerRole = new Role(2, "OWNER");

        when(rolePersistencePort.getRoleByName("OWNER")).thenReturn(ownerRole);
        when(passwordEncodedServicePort.encryptPassword("password123")).thenReturn("encryptedPassword123");

        adminUseCase.saveOwner(user);

        assertEquals(ownerRole.getRoleId(), user.getRole());
        assertEquals("encryptedPassword123", user.getPassword());
        verify(userPersistencePort).createUser(user);
    }

    @Test
    void saveOwner_whenUserIsYounger_shouldThrowException() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -17);
        Date dateOfBirth = cal.getTime();

        User user = new User(null, "John", "Doe", 1234678908L,"+573001234567", "john.doe@example.com" , dateOfBirth,null, "password123" , null);

        assertThrows(IsOlderUserException.class, () -> adminUseCase.saveOwner(user));

        verify(userPersistencePort, never()).createUser(any (User.class));
    }

    @Test
    void getOwner_shouldReturnOwner() {
        Integer userId = 1;
        Role ownerRole = new Role(2, "OWNER");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -30); // Usuario de 30 años
        Date dateOfBirth = cal.getTime();
        User user = new User(userId, "John", "Doe", 12345678L, "+573001234567", "john.doe@example.com", dateOfBirth, ownerRole.getRoleId(), "encryptedPassword123", null);

        when(rolePersistencePort.getRoleByName("OWNER")).thenReturn(ownerRole);
        when(userPersistencePort.getUser(userId, ownerRole.getRoleId())).thenReturn(user);

        User result = adminUseCase.getOwner(userId);

        assertEquals(user, result);
    }
}