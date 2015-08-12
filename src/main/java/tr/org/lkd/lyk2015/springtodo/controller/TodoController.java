package tr.org.lkd.lyk2015.springtodo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import tr.org.lkd.lyk2015.springtodo.model.Todo;
import tr.org.lkd.lyk2015.springtodo.service.TodoService;


@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model) {
        List<Todo> todos = todoService.getAll();

        model.addAttribute("todoList", todos);
        return "todoList";
    }

    @RequestMapping(value = "/mark", method = RequestMethod.POST)
    public String checkDone(@RequestParam("id") Long id) {

        todoService.markAsDone(id, true);
        return "redirect:/todo";
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(@ModelAttribute Todo todo) {

        return "createForm";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editForm(@ModelAttribute Todo todo){

        return "editForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Todo todo) {
        todoService.create(todo);

        return "redirect:/todo";
    }

}
