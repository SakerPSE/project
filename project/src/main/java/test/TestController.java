package test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping("/test.do")
	public String test(Model model) {
		model.addAttribute("test", "테스트");
		return "test";
	}
}
