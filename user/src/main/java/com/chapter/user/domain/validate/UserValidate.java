package com.chapter.user.domain.validate;

import java.time.LocalDate;
import java.time.Period;

public class UserValidate {

    private static final int MINIMUM_AGE = 18;

        public static boolean isOlder(String fechaNacimiento) {
            try {
                // Convertir String a LocalDate (formato: YYYY-MM-DD)
                LocalDate birthDate = LocalDate.parse(fechaNacimiento);

                // Obtener fecha actual
                LocalDate today = LocalDate.now();

                // Calcular edad
                int age = Period.between(birthDate, today).getYears();

                // Validar que sea mayor de 18
                return age >= MINIMUM_AGE;
            } catch (Exception e) {
                return false;
            }
        }
    }
