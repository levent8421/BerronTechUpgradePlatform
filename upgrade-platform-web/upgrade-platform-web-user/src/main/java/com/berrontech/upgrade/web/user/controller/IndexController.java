package com.berrontech.upgrade.web.user.controller;

import com.berrontech.upgrade.resource.ResourcePathService;
import com.berrontech.upgrade.web.commons.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Create By Levent8421
 * Create Time: 2020/12/1 11:23
 * Class Name: IndexController
 * Author: Levent8421
 * Description:
 * Index Controller
 *
 * @author Levent8421
 */
@Controller
@RequestMapping("/")
public class IndexController extends AbstractController {
    private final ResourcePathService resourcePathService;

    public IndexController(ResourcePathService resourcePathService) {
        this.resourcePathService = resourcePathService;
    }

    /**
     * Index
     *
     * @return index
     */
    @GetMapping
    public ModelAndView index() {
        final String staticService = resourcePathService.getServer();
        final ModelAndView mv = new ModelAndView("index");
        mv.addObject("staticServer", staticService);
        return mv;
    }
}
