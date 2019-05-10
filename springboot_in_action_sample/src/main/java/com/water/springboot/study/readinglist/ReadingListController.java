package com.water.springboot.study.readinglist;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
//表示该controller的属性是通过配置属性（application.properties）进行注入的，前缀是amazon
@ConfigurationProperties(prefix = "amazon")
public class ReadingListController {
    private String associateId;

    //ConfigurationProperties注解是通过set方式进行属性的注入
    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    private ReadlingListRepository readlingListRepository;
    public ReadingListController(ReadlingListRepository readlingListRepository) {
        this.readlingListRepository = readlingListRepository;
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readersBook(@PathVariable("reader") String reader, Model model){
        List<Book> books = readlingListRepository.findByReader(reader);
        if(books != null){
            model.addAttribute("books", books);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", associateId);
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
