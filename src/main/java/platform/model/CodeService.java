package platform.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.persistence.CodeRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;


@Service
public class CodeService {
    @Autowired
    CodeRepository codeRepository;

    /**
     * Save a new Code to DB
     *
     * @param code new Code from controller
     * @return Code from DB
     */
    public Code saveCode(Code code) {
        code.setViewsRestriction(code.getViews() > 0); // TRUE, If code from JSON has View restriction
        code.setTimeRestriction(code.getTime() > 0); // TRUE, If code from JSON has Time restriction
        code.setUuid(UUID.randomUUID().toString()); // Create a new random uuid
        code.setDate(LocalDateTime.now()); // Set current date and time
        return codeRepository.save(code);
    }

    /**
     * Remov code from the database
     *
     * @param uuid uuid of removing Code
     */
    public void deleteCode(String uuid) {
        codeRepository.deleteByUuid(uuid);
    }

    /**
     * Update code in DB
     *
     * @param code Code for updating
     */
    public void updateCode(Code code) {
        codeRepository.save(code);
    }

    /**
     * Find Code in DB by uuid and check restrictions
     *
     * @param uuid uuid of Code in DB
     * @return Code from DB if exist
     * @throws ResponseStatusException if Code not found or if the code has restrictions and they happened
     */
    public Code findCodeByUuid(String uuid) throws ResponseStatusException {
        Code code = codeRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        //Checking restrictions
        if (isViewsRestriction(code) && isTimeRestriction(code)) {
            return code;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Check if Code has a time restrictions and they happened
     *
     * @param code Code for checking
     * @return true if no restrictions or they not happened, false otherwise
     */
    private boolean isTimeRestriction(Code code) {
        // If the code has no restrictions
        if (!code.isTimeRestriction()) {
            return true;
        }
        // If code has restrictions
        // Check if restriction happened
        if (code.isTimeRestriction() &&
                code.getDate().plusSeconds(code.getTime()).isBefore(LocalDateTime.now())
        ) {
            return false; // if restriction happened
        } else {
            // Set remaining time limit for showing for Users
            code.setTime(code.getTime() - (ChronoUnit.SECONDS.between(code.getDate(), LocalDateTime.now())));
            return true;
        }
    }

    /**
     * Check if Code has a view restrictions and they happened
     *
     * @param code Code for checking
     * @return true if no restrictions or they not happened, false otherwise
     */
    private boolean isViewsRestriction(Code code) {
        // If the code has no restrictions
        if (!code.isViewsRestriction()) {
            return true;
        }
        // If code has restrictions
        // Check if restriction happened
        if (code.isViewsRestriction() && code.getViews() <= 0) {
            return false; // if restriction happened
        } else {
            // Set a new number of views
            code.setViews(code.getViews() - 1);
            updateCode(code); // Updating code in DB
            return true;
        }
    }

    /**
     * Find and return List of last 10 uploading codes (without code with restrictions)
     *
     * @return List of Codes from DB
     */
    public List<Code> findLatest() {
        return codeRepository.getLatestCodes();
    }
}
