package com.chapter.user.domain.usecase;

import com.chapter.user.domain.api.IPasswordEncodedServicePort;
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


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OwnerUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Mock
    private IPasswordEncodedServicePort passwordEncodedServicePort;

    @InjectMocks
    private OwnerUseCase ownerUseCase;

    @Test
    void saveEmployee_shouldSaveEmployeeWithEncryptedPasswordAndRole() {
        User user = new User();
        user.setName("Alice");
        user.setLastname("Smith");
        user.setDocument(23456789L);
        user.setEmail("alice.smith@example.com");
        user.setPassword("password123");
        user.setPhone("+573001234567");

        Role employeeRole = new Role(3, "EMPLOYEE");
        when(rolePersistencePort.getRoleByName("EMPLOYEE")).thenReturn(employeeRole);
        when(passwordEncodedServicePort.encryptPassword("password123")).thenReturn("encryptedPassword123");

        ownerUseCase.saveEmployee(user);

        assertEquals(employeeRole.getRoleId(), user.getRole());
        assertEquals("encryptedPassword123", user.getPassword());

        verify(userPersistencePort).createUser(user);
    }

    @Test
    void getEmployee_shouldReturnEmployee() {
        String email = "alice.smith@example.com";

        User user = new User(1, "Alice", "Smith", 23456789L,"+573001234567", email , null, 4, "encryptedPassword123", null);

        when(userPersistencePort.getEmployee(email)).thenReturn(user);

        User result = ownerUseCase.getEmployee(email);

        assertEquals(user, result);
    }
}