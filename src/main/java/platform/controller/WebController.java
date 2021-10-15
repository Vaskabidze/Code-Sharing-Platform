package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import platform.model.Code;
import platform.model.CodeService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class WebController {

    @Autowired
    CodeService codeService;

    /**
     * find and return Page with List of last 10 uploading Codes (without code with restrictions)
     *
     * @return Page with List of last 10 uploading Codes (without code with restrictions)
     */
    @GetMapping("/code/latest")
    public ModelAndView getCodeListWeb(HttpServletResponse response, ModelAndView model) {
        response.addHeader("Content-Type", "text/html");
        model.addObject("codes", codeService.findLatest());
        model.setViewName("listofcodes");
        return model;
    }

    /**
     * Find and return a page with COde from DB by uuid
     *
     * @param uuid uuid of Code in DB
     * @return page with COde from DB by uuid
     */
    @GetMapping(path = "/code/{uuid}", produces = MediaType.TEXT_HTML_VALUE)
    public String getCode(Model model, @PathVariable String uuid) {
        Code code = codeService.findCodeByUuid(uuid);
        model.addAttribute("code", code);
        model.addAttribute("timeLeft", code.getTime());
        return "code";
    }

    /**
     * Create and return page for uploading a new Code
     *
     * @return a page for uploading a new Code
     */
    @GetMapping(value = "/code/new")
    public ModelAndView newCodeWeb(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        ModelAndView model = new ModelAndView();
        model.setViewName("newcode");
        return model;
    }

    /**
     * Create and return page for uploading a new Code
     *
     * @return a page for uploading a new Code
     */
    @GetMapping(value = "/code/secretcode")
    public ModelAndView secretCode(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        ModelAndView model = new ModelAndView();
        model.setViewName("secretcode");
        return model;
    }

    /**
     * Create and return page for uploading a new Code
     *
     * @return a page for uploading a new Code
     */
    @GetMapping(value = "/code/contact")
    public ModelAndView contact(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        ModelAndView model = new ModelAndView();
        model.setViewName("contact");
        return model;
    }
}
