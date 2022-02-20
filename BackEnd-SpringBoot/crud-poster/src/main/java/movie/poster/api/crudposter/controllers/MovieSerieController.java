package movie.poster.api.crudposter.controllers;

import movie.poster.api.crudposter.entities.dto.MovieSerieEntityDto;
import movie.poster.api.crudposter.services.MovieSerieService;
import movie.poster.api.crudposter.utils.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/movie_serie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieSerieController {

    private final MovieSerieService serviceLayer;

    public MovieSerieController(MovieSerieService serviceLayer) { this.serviceLayer = serviceLayer; }

    @PostMapping(value = "/save")
    public ResponseEntity<Boolean> saveData(@Valid @RequestBody MovieSerieEntityDto data){
        serviceLayer.saveOnDb(data);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> updateData(@Valid @RequestBody MovieSerieEntityDto data){
        serviceLayer.updateOnDb(data);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> sofDelete(@Valid @PathVariable(name="id") UUID idToDelete){
        serviceLayer.sofDelete(idToDelete);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<MovieSerieEntityDto>> listPage(@RequestParam Map<String,String> pageableParams){
        Pageable pageable = CommonUtils.getRequestPagination(pageableParams);
        return new ResponseEntity<>( serviceLayer.listPage(pageable) , HttpStatus.OK);
    }
}
