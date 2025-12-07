package pl.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

public record UserSimpleDto(@Nullable Long id, String firstName, String lastName) {
}
