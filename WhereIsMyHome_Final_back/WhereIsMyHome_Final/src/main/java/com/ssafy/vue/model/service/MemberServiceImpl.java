package com.ssafy.vue.model.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.mapper.MemberMapper;
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		sqlSession.getMapper(MemberMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		sqlSession.getMapper(MemberMapper.class).deleteRefreshToken(map);
	}
	
	@Override
	public boolean join(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).join(memberDto)==1;
	}
	
	@Override
	public boolean modify(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).modify(memberDto)==1;
	}
	
	@Override
    public boolean delete(String userid) throws Exception {
        return sqlSession.getMapper(MemberMapper.class).delete(userid)==1;
    }

	@Override
	public String findPw(MemberDto memberDto) throws Exception {
		if (memberDto.getUserid() == null || memberDto.getEmail() == null)
			return null;
		MemberDto dto = (MemberDto) sqlSession.getMapper(MemberMapper.class).findByIdAndEmail(memberDto);
		System.out.println(dto.getEmail());
		if(dto == null) return null;
		
		String tempPwd = getTempPassword();
		dto.setUserpwd(tempPwd);
		if(sqlSession.getMapper(MemberMapper.class).modify(dto)!=1) {
			return null;
		}
		
		System.out.println("db?????????");
		// ????????? ????????? ??????
		Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");     // gmail??? true ??????
        p.put("mail.smtp.host", "smtp.naver.com");      // smtp ?????? ??????
        p.put("mail.smtp.auth","true");                 // gmail??? true ??????
        p.put("mail.smtp.port", "587");                 // ????????? ??????
           
        Authenticator auth = new MyAuthentication();
        //session ?????? ???  MimeMessage??????
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);
         
        try{
            //??????????????????
            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress() ;
            from = new InternetAddress("lcm199794@naver.com"); //????????? ?????????
            // ????????? ?????????
            msg.setFrom(from);
            // ????????? ?????????
            InternetAddress to = new InternetAddress(dto.getEmail());
            msg.setRecipient(Message.RecipientType.TO, to);
            // ????????? ??????
            msg.setSubject("???????????? ??????", "UTF-8");
            // ????????? ??????
            msg.setText("?????? ??????????????? " +dto.getUserpwd() + " ?????????.", "UTF-8");
            // ????????? ??????
            msg.setHeader("content-Type", "text/html");
            //???????????????
            javax.mail.Transport.send(msg, msg.getAllRecipients());
             
        }catch (AddressException addr_e) {
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) {
            msg_e.printStackTrace();
        }catch (Exception msg_e) {
            msg_e.printStackTrace();
        }
		return tempPwd;
	}
	
	class MyAuthentication extends Authenticator {
	    PasswordAuthentication pa;
            
	    public MyAuthentication(){
	        // ID??? ??????????????? ????????????.
	    	String id= "lcm199794@naver.com";  //????????? ????????? ?????????
		    String pw="ssafy7200!";    //????????? ????????????
	        pa = new PasswordAuthentication(id, pw);
	    }
	 
	    // ??????????????? ???????????? ????????????
	    public PasswordAuthentication getPasswordAuthentication() {
	        return pa;
	    }
 	} 
	
	public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // ?????? ?????? ????????? ?????? ???????????? 10?????? ?????? ????????? ?????????
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
}
