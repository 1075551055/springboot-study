package com.water.springboot.study.readinglist;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {
    private ReadlingListRepository readlingListRepository;

    public ReadingListController(ReadlingListRepository readlingListRepository) {
        this.readlingListRepository = readlingListRepository;
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readersBook(@PathVariable("reader") String reader, Model model){
        List<Book> books = readlingListRepository.findByReader(reader);
        if(books != null){
            model.addAttribute("books", books);
        }
        return "readingList";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        readlingListRepository.save(book);
        return "redirect:/{reader}";
    }
}
