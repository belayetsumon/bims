/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.test;

import itgarden.model.test.MyTest;
import itgarden.repository.test.MyTestRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/mytest")
public class MyTestController {

    @Autowired
    MyTestRepository myTestRepository;

    @RequestMapping("/index")
//@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.DESC)
    //@RequestParam(value="0") int page
    public String index(Model model, @RequestParam(defaultValue = "0") int page) {

        Pageable pageable = new PageRequest(page, 15, Direction.DESC, "id");

        Page<MyTest> pages = myTestRepository.findAll(pageable);

        //System.out.println("hello"+pages);
        model.addAttribute("list", pages);
        model.addAttribute("currentPage", page);

        /// Pagination Short ////
        // Loop Start value
        int startPage = 0;

        // Loop end  value
        int endPage = 0;

        if (pages.getTotalPages() <= 10) {
            // less than 10 total pages so show all
            startPage = 0;
            endPage = pages.getTotalPages();
        } else // more than 10 total pages so calculate start and end pages
        {
            if (page <= 6) {
                startPage = 0;
                endPage = 10;
            } else if (page + 4 >= pages.getTotalPages()) {
                startPage = pages.getTotalPages() - 9;
                endPage = pages.getTotalPages();
            } else {
                startPage = page - 5;
                endPage = page + 4;
            }
        }
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/test/mytest";
    }

    @RequestMapping("/index2")
    @ResponseBody
    public Page index2(Model model,
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
        Page<MyTest> pages = myTestRepository.findAll(pageable);
        //System.out.println("hello"+pages);
        model.addAttribute("list", pages);
        return pages;

    }
}
