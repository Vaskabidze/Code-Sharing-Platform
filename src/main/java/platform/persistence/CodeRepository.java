package platform.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import platform.model.Code;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeRepository extends CrudRepository<Code, Integer> {
    /**
     * Fonde and return Code from DB by uuid
     *
     * @param uuid uuid of Code in DB
     * @return Code from DB
     */
    Optional<Code> findByUuid(String uuid);

    /**
     * Remove Code by uuid from DB
     *
     * @param uuid uuid of Code in DB
     */
    @Transactional
    void deleteByUuid(String uuid);

    /**
     * Find and return List of last 10 uploading codes (without code with restrictions)
     *
     * @return List of Codes from DB
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM CODE " +
                    "WHERE " +
                    "TIME_RESTRICTION = FALSE " +
                    "AND VIEWS_RESTRICTION = FALSE " +
                    "ORDER BY DATE DESC LIMIT 10")
    List<Code> getLatestCodes();
}
