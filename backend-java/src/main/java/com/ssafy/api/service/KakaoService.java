package com.ssafy.api.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class KakaoService {
	@Autowired
	private final UserRepository userRepository;
	
	public KakaoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	// 토큰 받아오기
    public HashMap<String, Object> getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        HashMap<String, Object> token = new HashMap<>();
        
        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            System.out.println("=================================================================");
            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=e1a7f29f3266d25acd09415836291442");  //본인이 발급받은 key
           sb.append("&redirect_uri=https://i6b202.p.ssafy.io/kakao");     // 본인이 설정해 놓은 경로 서버용
            // sb.append("&redirect_uri=http://localhost:8080/kakao");     // 본인이 설정해 놓은 경로 test용
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            if(responseCode == 200) {
            	
	            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	
	            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
//	            JsonParser parser = new JsonParser();
//	            JsonElement element = parser.parse(result);
	            JsonObject element = JsonParser.parseString(result).getAsJsonObject();
	            access_Token = element.getAsJsonObject().get("access_token").getAsString();
	            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
	            
	            token.put("access_Token", access_Token);
	            token.put("refresh_Token", refresh_Token);
	
	            br.close();
	            bw.close();
            }else {
            	token.put("access_Token", "false");
 	            token.put("refresh_Token", "false");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return token;
    }
    
    // 사용자 정보 받아오기
    @Transactional
    public HashMap<String, Object> getUserInfo (String access_Token) {

        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            // System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            // System.out.println("response body : " + result);

//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(result);
            JsonObject element = JsonParser.parseString(result).getAsJsonObject();
//            System.out.println(element);
            String kakao_id = element.getAsJsonObject().get("id").getAsString();
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String profile_image = kakao_account.getAsJsonObject().get("profile").getAsJsonObject().get("thumbnail_image_url").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();

            userInfo.put("kakao_id", kakao_id);
            userInfo.put("nickname", nickname);
            userInfo.put("access_token", access_Token);
            userInfo.put("email", email);
            userInfo.put("profile_image", profile_image);

            
            User user = getUserEmail(userInfo);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
    
    public User getUserEmail(HashMap<String, Object> userInfo) {
    	String email = (String) userInfo.get("email");
    	
    	// DB애 email이 존재하면 패스, 존재하지 않으면 저장 Go!
		return userRepository.findByUserEmail(email).orElseGet(()-> JoinNewUserWithEmail(userInfo));  		 	
    }
    
    @Transactional
    public User JoinNewUserWithEmail(HashMap<String, Object> userInfo){
    	System.out.println("=================");
        User user = new User();
        user.setKakaoId((String) userInfo.get("kakao_id"));
        user.setUserEmail((String) userInfo.get("email"));
        user.setNickName((String) userInfo.get("nickname"));
        user.setAccessToken((String) userInfo.get("access_token"));
        user.setProfileUrl((String) userInfo.get("profile_image"));
        userRepository.save(user);
        
        return user;
    }
    
}
