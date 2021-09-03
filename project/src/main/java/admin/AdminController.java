package admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping("/admin")
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping("/admin/board/index.do")
	public String boardIndex() {
		return "admin/board/index";
	}
	
	@RequestMapping("/admin/board/view.do")
	public String boardView() {
		return "admin/board/view";
	}
	
	@RequestMapping("/admin/board/write.do")
	public String boardWrite() {
		return "admin/board/write";
	}
}
