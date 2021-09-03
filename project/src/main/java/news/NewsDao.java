package news;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDao {
  
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<NewsVo> selectAll(NewsVo vo) {
		return sqlSession.selectList("news.selectAll", vo);
	}
	
	public int count(NewsVo vo) {
		return sqlSession.selectOne("news.count", vo);
	}
	
	public NewsVo detail(NewsVo vo) {
		return sqlSession.selectOne("news.detail", vo);
	}
	
	public void updateReadCount(NewsVo vo) {
		sqlSession.update("news.updateReadCount", vo);
	}

	public int insert(NewsVo vo) {
		return sqlSession.insert("news.insert", vo);
	}
	
	public int update(NewsVo vo) {
		return sqlSession.insert("news.update", vo);
	}
	public int delFilename(NewsVo vo) {
		return sqlSession.insert("news.delFilename", vo);
	}
	public int delete(NewsVo vo) {
		return sqlSession.delete("news.delete", vo);
	}
}
