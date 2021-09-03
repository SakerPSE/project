package news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	NewsDao dao;
	
	@Override
	public List<NewsVo> selectAll(NewsVo vo) {
		int totCount = dao.count(vo); // 총 개수
		//총 페이지수
		int totPage = totCount / vo.getPageRow();
		if(totCount % vo.getPageRow() > 0) totPage++;
//		int startIdx = (vo.getReqPage() -1) * vo.getPageRow(); // 시작인덱스
//		vo.setStartIdx(startIdx);
		
		//시작 페이지
		int startPage = (vo.getReqPage() -1) / vo.getPageRange() * vo.getPageRange() + 1;
		int endPage = startPage + (vo.getPageRange()-1);
		if (endPage > totPage) endPage = totPage;
		
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		vo.setTotCount(totCount);
		vo.setTotPage(totPage);
		return dao.selectAll(vo);
	}

	@Override
	public NewsVo detail(NewsVo vo) {
		dao.updateReadCount(vo); // 조회수 1증가
		return dao.detail(vo); //no 해당하는 데이터를 가져오는 메소드
	}

	@Override
	public int insert(NewsVo vo) {
		return dao.insert(vo);
	}
	
	@Override
	public NewsVo edit(NewsVo vo) {
		return dao.detail(vo);
	}
	
	@Override
	public int update(NewsVo vo) {
		if ("1".equals(vo.getIsDel())) {
			dao.delFilename(vo);
		}
		return dao.update(vo);
	}
	
	@Override
	public int delete(NewsVo vo) {
		return dao.delete(vo);
	}
}
