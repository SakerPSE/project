package board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao dao;
	
	@Override
	public List<BoardVo> selectAll(BoardVo vo) {
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
	public BoardVo detail(BoardVo vo) {
		dao.updateReadCount(vo); // 조회수 1증가
		return dao.detail(vo); //no 해당하는 데이터를 가져오는 메소드
	}

	@Override
	public int insert(BoardVo vo) {
		return dao.insert(vo);
	}
	
	@Override
	public BoardVo edit(BoardVo vo) {
		return dao.detail(vo);
	}
	
	@Override
	public int update(BoardVo vo) {
		if ("1".equals(vo.getIsDel())) {
			dao.delFilename(vo);
		}
		return dao.update(vo);
	}
	
	@Override
	public int delete(BoardVo vo) {
		return dao.delete(vo);
	}
}
