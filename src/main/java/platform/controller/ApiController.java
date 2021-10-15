package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.model.CodeService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
public class ApiController {
    @Autowired
    CodeService codeService;

    /**
     * Find and return JSON with List of last 10 uploading codes (without code with restrictions)
     *
     * @return JSON with List of codes
     */
    @GetMapping("/api/code/latest")
    public List<Code> getCodeList(HttpServletResponse response) {
        response.addHeader("Content-Type", "application/json");
        return codeService.findLatest();
    }

    /**
     * Find and return a JSON from DB by uuid
     *
     * @param uuid uuid of Code in DB
     * @return JSON with Code from DB
     */
    @GetMapping("/api/code/{uuid}")
    public Code getCodeById(@PathVariable String uuid) {
        return codeService.findCodeByUuid(uuid);
    }

    /**
     * Takes a JSON with new Code
     *
     * @param code Code for save
     * @return uuid of new Code from DB
     */
    @PostMapping("/api/code/new")
    public Map<String, String> newCode(@RequestBody Code code) {
        String uuid = String.valueOf(codeService.saveCode(code).getUuid());
        return Map.of("id", uuid);
    }
}
