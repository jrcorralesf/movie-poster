package movie.poster.api.crudposter.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class CommonUtils {

    private CommonUtils() {
        throw new IllegalStateException("Utility class 'CommonUtils' can not be instanced");
    }

    public static Pageable getRequestPagination(Map<String,String> pageableParams) throws IllegalArgumentException {
        Integer page = Integer.valueOf(pageableParams.getOrDefault("page", "1"));
        Integer startPage = Integer.valueOf(pageableParams.getOrDefault("start_page", "1"));
        Integer pageSize = Integer.valueOf(pageableParams.getOrDefault("page_size", "10"));
        String ordering = pageableParams.getOrDefault("ordering", "-releaseDate");
        if (startPage == 1) { page = page-1; }
        return PageRequest.of(page, pageSize, getSortObject(ordering));
    }

    private static Sort getSortObject(String ordering) {
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (ordering.contains("-")) {
            ordering = ordering.replace("-", "");
            sortDirection = Sort.Direction.DESC;
        } else if (ordering.contains("+")){
            ordering = ordering.replace("+", "");
        }
        return Sort.by(sortDirection, ordering);
    }
}
