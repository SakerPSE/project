package news;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class NewsController {

	@Autowired
	NewsService service;
	
	@RequestMapping("/news/index.do")
	public String index(Model model, NewsVo vo) {
		model.addAttribute("list", service.selectAll(vo));
		return "news/index";
	}
	
	@RequestMapping("/news/detail.do")
	public String detail(Model model, NewsVo vo) {
		model.addAttribute("vo", service.detail(vo));
		return "news/detail";
	}
	
	@RequestMapping("/news/write.do")
	public String write(Model model, NewsVo vo) {
		return "news/write";
	}
	
	@RequestMapping("/news/insert.do")
	public String insert(Model model, NewsVo vo, @RequestParam MultipartFile file, HttpServletRequest req) {
//		service.insert(vo, file, req);
		if (!file.isEmpty()) { // 첨부파일이 있으면
			try {
			String org = file.getOriginalFilename(); //원본 파일명
			String ext = ""; // 확장자
			
			ext = org.substring(org.lastIndexOf(".")); // 확장자 명 가져오기
			String real = new Date().getTime()+ext; // 서버에 저장할 파일명
//			System.out.println("org: "+org);
//			System.out.println("real: "+real);
			// 파일 저장
			String path = req.getRealPath("/upload/"); // 저장 경로
			System.out.println(path);
			file.transferTo(new File(path+real)); // 경로 + 파일명
			// vo 에 set
			vo.setFilename_org(org);
			vo.setFilename_real(real);
			} catch (Exception e) {
				
			}
		}
		int r = service.insert(vo);
		// r > 0 : 정상 -> alert -> 목록으로 이동
		// r == 0: 비정상 -> alert -> 이전 페이지로 이동
		if (r>0) {
			model.addAttribute("msg", "정상적으로 등록되었습니다");
			model.addAttribute("url", "index.do");
		} else {
			model.addAttribute("msg", "등록 실패");
			model.addAttribute("url", "write.do");
		}
		return "include/alret";
	}
	
	@RequestMapping("/news/edit.do")
	public String edit(Model model, NewsVo vo) {
		model.addAttribute("vo", service.edit(vo));
		return "news/edit";
	}
	
	@RequestMapping("/news/update.do")
	public String update(Model model, NewsVo vo, @RequestParam MultipartFile file, HttpServletRequest req) {
//		service.insert(vo, file, req);
		if (!file.isEmpty()) { // 첨부파일이 있으면
			try {
			String org = file.getOriginalFilename(); //원본 파일명
			String ext = ""; // 확장자
			
			ext = org.substring(org.lastIndexOf(".")); // 확장자 명 가져오기
			String real = new Date().getTime()+ext; // 서버에 저장할 파일명
//			System.out.println("org: "+org);
//			System.out.println("real: "+real);
			// 파일 저장
			String path = req.getRealPath("/upload/"); // 저장 경로
			System.out.println(path);
			file.transferTo(new File(path+real)); // 경로 + 파일명
			// vo 에 set
			vo.setFilename_org(org);
			vo.setFilename_real(real);
			} catch (Exception e) {
				
			}
		}
		int r = service.update(vo);
		// r > 0 : 정상 -> alert -> 목록으로 이동
		// r == 0: 비정상 -> alert -> 이전 페이지로 이동
		if (r>0) {
			model.addAttribute("msg", "정상적으로 수정되었습니다");
			model.addAttribute("url", "index.do");
		} else {
			model.addAttribute("msg", "수정 실패");
			model.addAttribute("url", "edit.do?no="+vo.getNo());
		}
		return "include/alret";
	}
	
	@RequestMapping("/news/delete.do")
	public String delete(Model model, NewsVo vo) {
//		model.addAttribute("vo", service.delete(vo));
		service.delete(vo);
		model.addAttribute("res", "true");
		return "news/ajax";
	}
}
