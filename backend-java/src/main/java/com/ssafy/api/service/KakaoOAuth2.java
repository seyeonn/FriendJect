package com.ssafy.api.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.db.dto.KakaoInfoDTO;

@Component
public class KakaoOAuth2 {
	
	public KakaoInfoDTO getUserInfo(String code) {
		
		String accessToken = getAccessToken(code);
		KakaoInfoDTO userInfo = getUserInfoByToken(accessToken);
		return userInfo; 
	}

	private static String getAccessToken(String authorize_code) {
		
		 String accessToken = "";
	        String reqURL = "https://kauth.kakao.com/oauth/token";

	        HashMap<String, Object> token = new HashMap<>();
	        
	        try {
	            URL url = new URL(reqURL);

	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);

	            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=8115a87101ffedb86a86de451bbe426d");  //본인이 발급받은 key
	            sb.append("&redirect_uri=http://localhost:8080/kakao");     // 본인이 설정해 놓은 경로
	            sb.append("&code=" + authorize_code);
	            bw.write(sb.toString());
	            bw.flush();

	            //    결과 코드가 200이라면 성공
	            int responseCode = conn.getResponseCode();
	            //System.out.println("responseCode : " + responseCode);
	            if(responseCode == 200) {
	            	
		            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
		            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            String line = "";
		            String result = "";
		
		            while ((line = br.readLine()) != null) {
		                result += line;
		            }
		
		            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
		            JsonObject element = JsonParser.parseString(result).getAsJsonObject();
		            accessToken = element.getAsJsonObject().get("access_token").getAsString();
		    		
		            br.close();
		            bw.close();
	            }else {
	            	token.put("access_Token", "false");
	 	            token.put("refresh_Token", "false");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        return accessToken;
	}

	private KakaoInfoDTO getUserInfoByToken(String accessToken) { 
		System.out.println(accessToken);
		KakaoInfoDTO userInfo = new KakaoInfoDTO();
        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();
            if(responseCode == 200) {
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		        String line = "";
		        String result = "";
		
		        while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        
		        JsonObject element = JsonParser.parseString(result).getAsJsonObject();
		        System.out.println(element);
		        
				userInfo.setId(element.getAsJsonObject().get("id").getAsLong());
				userInfo.setNickname(element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString()); 
				userInfo.setEmail(element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString());
				userInfo.setProfile(element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("profile").getAsJsonObject().get("thumbnail_image_url").getAsString()); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo; 
	}
}
