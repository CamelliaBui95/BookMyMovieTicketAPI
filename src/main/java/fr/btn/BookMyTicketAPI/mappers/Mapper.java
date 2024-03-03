package fr.btn.BookMyTicketAPI.mappers;

public interface Mapper<Entity, Dto> {
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);
}
