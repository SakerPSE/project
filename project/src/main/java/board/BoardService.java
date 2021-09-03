package board;

import java.util.List;

public interface BoardService {

	List<BoardVo> selectAll(BoardVo vo);
	BoardVo detail(BoardVo vo);
	int insert(BoardVo vo);
	BoardVo edit(BoardVo vo);
	int update(BoardVo vo);
	int delete(BoardVo vo);
}
