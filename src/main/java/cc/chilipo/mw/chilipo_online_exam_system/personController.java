package cc.chilipo.mw.chilipo_online_exam_system;

import cc.chilipo.mw.chilipo_online_exam_system.students.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

@Controller
public class personController
{
    @Autowired
    StudentRepository studrep;

}
