package fr.btn.BookMyTicketAPI.enums.converters;

import fr.btn.BookMyTicketAPI.enums.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        if(role == null)
            return null;

        return role.getCode();
    }

    @Override
    public Role convertToEntityAttribute(String code) {
        if(code == null)
            return null;

        return Stream.of(Role.values())
                .filter(role -> role.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
