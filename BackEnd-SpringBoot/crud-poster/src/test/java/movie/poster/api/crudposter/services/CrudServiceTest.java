package movie.poster.api.crudposter.services;

import movie.poster.api.crudposter.entities.MovieSerieEntity;
import movie.poster.api.crudposter.entities.dto.MovieSerieEntityDto;
import movie.poster.api.crudposter.repositories.MovieSerieEntityRepository;
import movie.poster.api.crudposter.services.impl.MovieSerieServiceImpl;
import movie.poster.api.crudposter.utils.CommonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrudServiceTest {

    ModelMapper modelMapper;
    @Mock MovieSerieEntityRepository repository;
    @InjectMocks MovieSerieServiceImpl service;

    @BeforeEach
    public void init() {
        modelMapper = new ModelMapper();
        service = new MovieSerieServiceImpl(modelMapper, repository);
    }

    @Test
    void shouldSaveOnDb() {
        MovieSerieEntity instanceToSave = entityOnTest(false);
        MovieSerieEntity expectedInstance = entityOnTest(true);
        when(repository.save(instanceToSave)).thenReturn(expectedInstance);

        MovieSerieEntityDto dataToStore = dtoOnTest(false);
        MovieSerieEntity dataStored= service.saveOnDb(dataToStore);
        assertEquals(dataStored, expectedInstance);
    }

    @Test
    void shouldUpdateOnDb() {
        MovieSerieEntity instanceToUpdate = entityOnTest(true);
        when(repository.getById(instanceToUpdate.getId())).thenReturn(instanceToUpdate);
        MovieSerieEntity expectedInstance = entityOnTest(true);
        expectedInstance.setRate(9.5);
        when(repository.save(expectedInstance)).thenReturn(expectedInstance);

        MovieSerieEntityDto dataToUpdate = dtoOnTest(true);
        dataToUpdate.setRate(9.5);
        MovieSerieEntity dataUpdated= service.updateOnDb(dataToUpdate);
        assertEquals(dataUpdated, expectedInstance);
    }

    @Test
    void shouldSoftDelete(){
        MovieSerieEntity instanceToDelete = entityOnTest(true);
        UUID instanceId = instanceToDelete.getId();
        when(repository.getById(instanceId)).thenReturn(instanceToDelete);

        service.sofDelete(instanceId);
        verify(repository).save(instanceToDelete);
        assertNotNull(instanceToDelete.getDeletedAt());
    }

    @Test
    void shoudListPage(){
        Pageable pageable = CommonUtils.getRequestPagination(new HashMap<>() {});
        List <MovieSerieEntityDto> instanceList = Arrays.asList(dtoOnTest(true));
        Page<MovieSerieEntityDto> expectedPage =new PageImpl<>(instanceList, pageable, instanceList.size());
        when(repository.findAllProjectedBy(pageable)).thenReturn(expectedPage);

        Page<MovieSerieEntityDto> resulPage = service.listPage(pageable);
        assertEquals(expectedPage,resulPage);
    }


    /* Test context creation */
    private MovieSerieEntity entityOnTest(Boolean fullEntity) {
        LocalDate releaseDate = LocalDate.now();
        MovieSerieEntity fakeInstaceFromDb = new MovieSerieEntity(
                "Harry Potter",
                "This is a movie of a wizard",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwigilabs.medium.com%2F&psig=AOvVaw19FaLw5Ghqx07WvXpzaoyl&ust=1645470392250000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCOC1-YT9jvYCFQAAAAAdAAAAABAD",
                8.5,
                releaseDate
        );
        if (fullEntity){
            fakeInstaceFromDb.setId(UUID.fromString("3ed4e085-f2e7-40de-9a10-6810cd54491c"));
        }
        return fakeInstaceFromDb;
    }
    private MovieSerieEntityDto dtoOnTest(Boolean withId){
        LocalDate releaseDate = LocalDate.now();
        MovieSerieEntityDto dataInDto = new MovieSerieEntityDto(
                null,
                "Harry Potter",
                "This is a movie of a wizard",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwigilabs.medium.com%2F&psig=AOvVaw19FaLw5Ghqx07WvXpzaoyl&ust=1645470392250000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCOC1-YT9jvYCFQAAAAAdAAAAABAD",
                8.5,
                releaseDate
        );
        if (withId){
            dataInDto.setId(UUID.fromString("3ed4e085-f2e7-40de-9a10-6810cd54491c"));
        }
        return dataInDto;
    }
}
