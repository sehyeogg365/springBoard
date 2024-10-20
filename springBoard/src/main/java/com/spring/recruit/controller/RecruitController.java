package com.spring.recruit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.HomeController;
import com.spring.board.vo.UserVo;
import com.spring.common.CommonUtil;
import com.spring.recruit.service.RecruitService;
import com.spring.recruit.vo.CareerVo;
import com.spring.recruit.vo.CertificateVo;
import com.spring.recruit.vo.EducationVo;
import com.spring.recruit.vo.RecruitVo;

@Controller
public class RecruitController {
	
	@Autowired
	RecruitService recruitService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//로그인페이지
	@RequestMapping(value = "/recruit/loginPage.do", method = RequestMethod.GET)
	public String loginPage() throws Exception{
		
		return "/recruit/login";
	}
	//로그인
	@RequestMapping(value = "/recruit/loginAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String loginAction (Locale locale, RecruitVo recruitVo, HttpSession session) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();

		session.setAttribute("name", recruitVo.getName());//학력,경력, 자격증도 이처럼 저장해야함
        session.setAttribute("phone", recruitVo.getPhone());

        result.put("success", (session != null)?"Y":"N");
        System.out.println("name" + recruitVo.getName());
        System.out.println("phone" + recruitVo.getPhone());
        String callbackMsg = commonUtil.getJsonCallBackString(" ",result);     
        
		return callbackMsg;
	}
	
	//메인화면
	@RequestMapping(value = "/recruit/mainPage.do", method = RequestMethod.GET)
	public String mainPage(HttpSession session, Model model) throws Exception{
		
		//세션값 불러오기
		String name = (String) session.getAttribute("name");
        String phone = (String) session.getAttribute("phone");
        
        String educations = (String) session.getAttribute("educations");
        String careers = (String) session.getAttribute("careers");
        String certificates = (String) session.getAttribute("certificates");
        
        System.out.println("name" + name);
        System.out.println("phone" + phone);
      
        
         RecruitVo recruitVo = new RecruitVo();
         EducationVo educationVo = new EducationVo();
         CareerVo careerVo = new CareerVo();
         CertificateVo certificateVo = new CertificateVo();
//        

         // 데이터 조회
         try {
             recruitVo = recruitService.recruitSelect(name, phone);  // name과 phone으로 데이터 조회
             System.out.println("recruitVo: " + recruitVo);
             
             if(recruitVo != null){
             educationVo = recruitService.educateSelect(recruitVo.getSeq());
             System.out.println("educationVo: " + educationVo);
             
             careerVo = recruitService.careerSelect(recruitVo.getSeq());
             System.out.println("careerVo: " + careerVo);
             certificateVo = recruitService.certificateSelect(recruitVo.getSeq());
             System.out.println("certificateVo" + certificateVo);
             }
         } catch (Exception e) {
             e.printStackTrace();  // 오류 로그 출력
             throw new Exception("Recruit 정보 조회 중 오류 발생: " + e.getMessage(), e);
         }
   
      
         if (recruitVo == null) {
        	 model.addAttribute("phone", phone);
     		 model.addAttribute("name", name);
        	 model.addAttribute("recruit", new RecruitVo()); // 기본값으로 빈 객체를 전달
        	 
         } else {//있을때 디비값
        	 model.addAttribute("recruit", recruitVo);
         }
        
         if (educationVo == null) {
        	 model.addAttribute("educate", new EducationVo()); // 기본값으로 빈 객체를 전달

         } else {//있을때 디비값
    	     model.addAttribute("educate", educationVo);
         }
     
         if (careerVo == null) {
        	model.addAttribute("career", new CareerVo()); // 기본값으로 빈 객체를 전달

         } else {
        	model.addAttribute("career", careerVo);
         }
         if (certificateVo == null) {
        	 model.addAttribute("certificate", new CertificateVo()); // 기본값으로 빈 객체를 전달
        
         } else {
        	 model.addAttribute("certificate", certificateVo);
         }
 
		return "/recruit/main";
	}
	
	//저장
	@RequestMapping(value = "/recruit/recruitInsertAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String recruitInsertAction(Locale locale, RecruitVo recruitVo, EducationVo educationVo
								, CareerVo careerVo, CertificateVo certificateVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
//		System.out.println("이름" +recruitVo.getName());
//		System.out.println("생일" + recruitVo.getBirth());
//		System.out.println("성별" + recruitVo.getGender());
//		System.out.println("연락처" + recruitVo.getPhone());
//		System.out.println("이메일" + recruitVo.getEmail());
//		System.out.println("주소" + recruitVo.getAddr());
//		System.out.println("주소" + recruitVo.getAddr());
//		System.out.println("희망근무지" + recruitVo.getLocation());
//		System.out.println("근무형태" + recruitVo.getWorkType());
//		System.out.println("시퀀스" + recruitVo.getSeq());
//		
		
		//채용정보 존재 확인
		if(recruitVo.getSeq() != null) {
			educationVo.setSeq(recruitVo.getSeq());
			careerVo.setSeq(recruitVo.getSeq());
			certificateVo.setSeq(recruitVo.getSeq());
		}
		int recruitCnt = recruitService.recruitExistence(recruitVo);
		
		int resultCnt = 0;
		if(recruitCnt == 0) {
			 resultCnt = recruitService.recruitInsert(recruitVo);//recruit 정보 저장
		} else {
			 resultCnt = recruitService.recruitUpdate(recruitVo);//'' 정보 업데이트
		} 
		System.out.println("시퀀스값: " + recruitVo.getSeq());
		System.out.println("로케이션: " + recruitVo.getHopeLocation());
		//일단 각 seq값을 셋팅해줘야 할것. 
		
		
				
		// 2. Education 정보 저장
		//Education정보 존재 확인
		int educateCnt = recruitService.educateExistence(recruitVo.getSeq());
		int educationResultCnt = 0;
		if(educateCnt == 0) {
			educationResultCnt = recruitService.educateInsert(educationVo);//educate 정보 저장
		} else {
			educationResultCnt = recruitService.educateUpdate(educationVo);//'' 정보 업데이트
		} 
			
	    // 3. Career 정보 저장
	    
	    //초기값 0 설정 다음 vo 값 존재할시에만 저장
		int carrerCnt = recruitService.careerExistence(recruitVo.getSeq());
	    int careerResultCnt = 0;
	    if(carrerCnt == 0 ) {
	    	careerResultCnt = recruitService.careerInsert(careerVo);//정보 저장
	    } else {
	    	careerResultCnt = recruitService.careerUpdate(careerVo);//정보 업데이트
	    }
	    System.out.println("Career Existence Count: " + carrerCnt);
	    System.out.println(careerVo.getCompName());
	    System.out.println(careerVo.getStartPeriod());
	    System.out.println(careerVo.getEndPeriod());
	    System.out.println(careerVo.getLocation());
	    System.out.println(careerVo.getSalary());

	    // 4. Certificate 정보 저장 (자격증은 필수가 아닐 수 있음)
	    
	    int certificateCnt = recruitService.certificateExistence(recruitVo.getSeq());
	    int certificateResultCnt = 0;
	    
	    if(certificateCnt == 0) {
	    	certificateResultCnt = recruitService.certificateInsert(certificateVo);//정보 저장
	    } else {
	    	certificateResultCnt = recruitService.certificateUpdate(certificateVo);//정보 업데이트
	    }
	        
	    
	    result.put("success", (resultCnt > 0 && educationResultCnt > 0)?"Y":"N");
		
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;

	}



}
