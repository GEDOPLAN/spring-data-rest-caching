package de.gedoplan.demo.cachingrest.repository;

import de.gedoplan.demo.cachingrest.repository.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@RepositoryRestResource(path = "user", excerptProjection = UserNameProjection.class)
//@RepositoryRestResource(path = "user", exported = false)
@RepositoryRestResource(path = "user")
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, ListCrudRepository<User, Long> {

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @Cacheable(cacheNames = "users")
    Optional<User> findById(Long aLong);

    @Override
    @Caching(evict={
            @CacheEvict(cacheNames = "users", key = "#entity.getId()"),
            @CacheEvict(cacheNames = "avgAge", allEntries = true),
    })
    <S extends User> S save(S entity);

    @Override
    @Cacheable(cacheNames = "userPage", condition = "#pageable.getPageNumber()==0", unless = "#result.getTotalElements()==0")
    Page<User> findAll(Pageable pageable);
}
