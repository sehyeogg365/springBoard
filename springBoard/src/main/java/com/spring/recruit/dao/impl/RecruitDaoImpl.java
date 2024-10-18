package com.spring.recruit.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.vo.CareerVo;
import com.spring.recruit.vo.CertificateVo;
import com.spring.recruit.vo.EducationVo;
import com.spring.recruit.vo.RecruitVo;

@Repository
public class RecruitDaoImpl implements RecruitDao{

	@Autowired
	private SqlSession sqlSession;
	
	//채용정보 
	
	@Override
	public int recruitInsert(RecruitVo recruitVo) throws Exception{
		
		return sqlSession.insert("recruit.recruitInsert", recruitVo);
	}
	
	@Override
	public RecruitVo recruitSelect(RecruitVo recruitVo) throws Exception{
		
		return sqlSession.selectOne("recruit.recruitSelect", recruitVo);
	}
	
	@Override
	public int recruitExistence(RecruitVo recruitVo) throws Exception{
		return sqlSession.selectOne("recruit.recruitExistence", recruitVo);
	}
	
	@Override
	public int recruitUpdate(RecruitVo recruitVo) throws Exception{
		return sqlSession.update("recruit.recruitUpdate", recruitVo);
	}
	//학력
	@Override
	public int educateInsert(EducationVo educationVo) throws Exception{
		
		return sqlSession.insert("recruit.educateInsert", educationVo);
	}
	
	@Override
	public int educateDelete(EducationVo educationVo) throws Exception{
		
		return sqlSession.delete("recruit.educateDelete", educationVo);
	}
	
	@Override
	public EducationVo educateSelect(EducationVo educationVo) throws Exception{
		
		return sqlSession.selectOne("recruit.educateSelect", educationVo);
	}
	
	@Override
	public int educateExistence (EducationVo educationVo) throws Exception{
		return sqlSession.selectOne("recruit.educateExistence", educationVo);
	}
	
	@Override
	public int educateUpdate (EducationVo educationVo) throws Exception{
		return sqlSession.update("recruit.educateUpdate", educationVo);
	}
	//경력
	@Override
	public int careerInsert(CareerVo careerVo) throws Exception{
		
		return sqlSession.insert("recruit.careerInsert", careerVo);
	}
	
	@Override
	public int careerDelete(CareerVo careerVo) throws Exception{
		
		return sqlSession.delete("recruit.careerDelete", careerVo);
	}

	@Override
	public CareerVo careerSelect(CareerVo careerVo) throws Exception{
		
		return sqlSession.selectOne("recruit.careerSelect", careerVo);
	}
	@Override
	public int careerExistence (CareerVo careerVo) throws Exception{
		return sqlSession.selectOne("recruit.careerExistence", careerVo);
	}
	
	@Override
	public int careerUpdate (CareerVo careerVo) throws Exception{
		return sqlSession.update("recruit.careerUpdate", careerVo);
	}	
	
	//자격증
	@Override
	public int certificateInsert(CertificateVo certificateVo) throws Exception{
		
		return sqlSession.insert("recruit.certificateInsert", certificateVo);
	}
	
	@Override
	public int certificateDelete(CertificateVo certificateVo) throws Exception{
		
		return sqlSession.delete("recruit.certificateDelete", certificateVo);
	}
	
	@Override
	public CertificateVo certificateSelect(CertificateVo certificateVo) throws Exception{
		
		return sqlSession.selectOne("recruit.certificateSelect", certificateVo);
	}
	
	@Override
	public int certificateExistence (CertificateVo certificateVo) throws Exception{
		return sqlSession.selectOne("recruit.certificateExistence", certificateVo);
	}
	
	@Override
	public int certificateUpdate (CertificateVo certificateVo) throws Exception{
		return sqlSession.update("recruit.certificateUpdate", certificateVo);
	}
	
	
}
