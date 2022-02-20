package movie.poster.api.crudposter.services.impl;

import movie.poster.api.crudposter.entities.MovieSerieEntity;
import movie.poster.api.crudposter.entities.dto.MovieSerieEntityDto;
import movie.poster.api.crudposter.repositories.MovieSerieEntityRepository;
import movie.poster.api.crudposter.services.MovieSerieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class MovieSerieServiceImpl implements MovieSerieService {

    private final ModelMapper modelMapper;
    private final MovieSerieEntityRepository repository;

    @Autowired
    public MovieSerieServiceImpl(ModelMapper modelMapper, MovieSerieEntityRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public MovieSerieEntity saveOnDb(MovieSerieEntityDto dataInDto) {
        MovieSerieEntity dataToSave = modelMapper.map(dataInDto, MovieSerieEntity.class);
        return repository.save(dataToSave);
    }

    @Override
    public MovieSerieEntity updateOnDb(MovieSerieEntityDto dataInDto) {
        MovieSerieEntity dataToUpdate = modelMapper.map(dataInDto, MovieSerieEntity.class);
        MovieSerieEntity dataOnDb = repository.getById(dataToUpdate.getId());
        BeanUtils.copyProperties(dataToUpdate, dataOnDb,
                "id", "createdBy", "modifiedBy",
                "createdAt", "modifiedAt",  "deletedAt");
        return repository.save(dataOnDb);
    }

    @Override
    public void sofDelete(UUID idToDelete) {
        MovieSerieEntity dataOnDb = repository.getById(idToDelete);
        dataOnDb.setDeletedAt(LocalDateTime.now());
        repository.save(dataOnDb);
    }

    @Override
    public Page<MovieSerieEntityDto> listPage(Pageable pageable) {
        return repository.findAllProjectedBy(pageable);
    }
}
