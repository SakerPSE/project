package news;

import java.util.List;

public interface NewsService {

	List<NewsVo> selectAll(NewsVo vo);
	NewsVo detail(NewsVo vo);
	int insert(NewsVo vo);
	NewsVo edit(NewsVo vo);
	int update(NewsVo vo);
	int delete(NewsVo vo);
}
