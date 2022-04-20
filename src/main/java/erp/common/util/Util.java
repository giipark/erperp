package erp.common.util;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import erp.common.exception.LogicException;
import erp.common.exception.RuntimeLogicException;

public class Util {
	
	/**
	 * QueryString을 Map으로 변환하여 리턴
	 * @param query
	 * @return
	 */
	public static Map<String, String> getQueryMap(String query) {
		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, value);
		}
		return map;
	}
	
	/**
	 * Map을 QueryString으로 변환
	 * @param map
	 * @return
	 */
	public static String mapToQueryString(Map<String, String> map) {
		StringBuilder string = new StringBuilder();
		
		for(Entry<String, String> entry : map.entrySet()) {
			if (string.length() > 0) {
				string.append("&");
			}
			string.append(entry.getKey());
			string.append("=");
			string.append(entry.getValue());
		}

		return string.toString();
	}
	
	/**
	 * 문자열이 숫자형인지 체크
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);  
		} catch(NumberFormatException e) {  
			return false;
		}
		return true;  
	}
	
	/**
	 * 폴더 삭제
	 * @param targetFolder
	 */
	public static void deleteFolder(File targetFolder) {
		if(targetFolder.isDirectory()){
			File[] targetFolderFiles = targetFolder.listFiles();
			for (File file : targetFolderFiles) {
				if(file.isDirectory()){
					deleteFolder(file);
					
				}else{
					file.delete();
				}
			}
			targetFolder.delete();
		}
	}
	
	/**
	 * 파일 생성
	 * @param inFileName
	 * @param outFileName
	 */
	public static void createFile(String fileName, String contents) {
		try {
            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, false), "UTF-8"));

            fw.write(contents);
            fw.flush();

            fw.close(); 
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	/**
	 * 파일 복사
	 * @param inFileName
	 * @param outFileName
	 */
	public static void fileCopy(String inFilePath, String outFilePath) {
		try {
			FileInputStream fis = new FileInputStream(inFilePath);
			FileOutputStream fos = new FileOutputStream(outFilePath);

			byte[] buffer = new byte[1024];

			int length;

			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}

			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 파일 복사
	 * @param inFilePath
	 * @param outFilePathDir
	 */
	public static String fileCopyTypeDir(String inFilePath, String outFilePathDir) {
		String realName = null;
		try {
			
			String[] dirList = outFilePathDir.split("/");
			
			String filePath = "";
			
			for (String dir : dirList) {
				filePath += dir + "/";
				File fileDir = new File(filePath);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
			}
			
			realName = Util.getUploadFileName(outFilePathDir, inFilePath.substring(inFilePath.lastIndexOf("/")+1));
			
			FileInputStream fis = new FileInputStream(inFilePath);
			FileOutputStream fos = new FileOutputStream(outFilePathDir+"/"+realName);

			byte[] buffer = new byte[1024];

			int length;

			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}

			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			realName = null;
		}
		
		return realName;
	}
	
	/**
	 * 파일 삭제
	 * @param targetFile
	 */
	public static void fileDelete(String targetFile) {
		File deleteFile = new File(targetFile);
		deleteFile.delete();
	}
	
	/**
	 * 파일 목록
	 * @param targetFile
	 */
	public static List<HashMap<String, Object>> getFileNameList(String path) {
		List<HashMap<String, Object>> fileNameList = new ArrayList<HashMap<String, Object>>();
		File dir = new File(path);
		File[] fileList = dir.listFiles();
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				if (file.isFile()) {
					HashMap<String, Object> temp = new HashMap<String, Object>();
					temp.put("file_name", file.getName());
					fileNameList.add(temp);
				}
			}
		} catch(Exception e) {
			return null;
		}
		return fileNameList;
	}
	
	/**
	 * MD5 비밀번호 암호화
	 * @param input
	 * @return
	 */
	public static String md5(String input) {
		String md5 = null;

		if (null == input) return null;

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(), 0, input.length());
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			md5 = input;
		}
		return md5;
	}
	
	/**
	 * SHA256 비밀번호 암호화
	 * @param input
	 * @return
	 */
	public static String getSHA256(String input) {
		String SHA = "";
		
		if (null == input) return null;
		
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(input.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
			
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;
	}
	
	/**
	 * 현재시간
	 * @param input
	 * @return
	 */
	public static String getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
	
		String currentDate = dateFormat.format(calendar.getTime());
		
		return currentDate;
	}
	
	/**
	 * 이미지 크기 변경
	 * @param soruce, width, height
	 * @return
	 */
	 public static Image resizing(Image soruce, int width, int height) throws Exception {
		 
		 int newW = width;
		 int newH = height;

		 return soruce.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	 }
	 
	/**
	 * 랜덤 파일명 생성
	 * @param uploadDir
	 * @param fileName
	 * @return
	 */
	public static String getUploadFileName(String uploadDir, String fileName) {
		
		String realName = null;
		String milName = "";
		String fileExt = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		long milliSecond = System.currentTimeMillis();
		milName = String.valueOf(milliSecond).substring( 10 ,13);
		
    	realName = (sdf.format(new java.util.Date()) + milName  ) + "."+fileExt;
    	
		
    	File ckfile = new File(uploadDir + "/" + realName);
    	
		int i = 0;
		while(ckfile.exists()) {
			i++;
			realName = ( (sdf.format(new java.util.Date()) + milName) ) + i  + "."+fileExt;
			ckfile = new File(uploadDir + "/" + realName);
		}
		return realName;
	}
	
	public static boolean isNull(Object obj) {
		boolean result = false;
		try {
			if (obj == null) {
				throw new LogicException("Object empty");
			} else if (obj instanceof String && obj.equals("")) {
				throw new LogicException("String empty");
			} else if (obj instanceof Map && ((Map<?, ?>) obj).isEmpty()) {
				throw new LogicException("Map empty");
			} else if (obj instanceof Set && ((Set<?>) obj).isEmpty()) {
				throw new LogicException("Set empty");
			} else if (obj instanceof List) {
				List<?> list = (List<?>)obj;
				if(list.size() == 0) {
					throw new LogicException("List empty");
				} else {
					for(Object listObj: list) {
						if(isNull(listObj)) {
							result = true;
							break;
						}
					}
				}
			} 
		} catch (Exception e) {
			result = true;
		}
		
		return result;
	}
	
	public static String nvl(String str, String dafault) {
		if(isNull(str)) return dafault;
		else return str;
	}
	
	public static String nvl(String str){
		return nvl(str, "");
	}
	
	public static void requiredCheck(HashMap<String, Object> param, String[] requiredValues) throws RuntimeLogicException {
		if (requiredValues.length > 0) {
			if (param != null && !param.isEmpty()) {
				for(String key : requiredValues) {
					if (isNull(param.get(key))) {
						throw new RuntimeLogicException("MISSING_REQUIRED_VALUE : ["+key+"]", "001");
					}
				}
			}
		} else {
			throw new RuntimeLogicException("REQUIRED_VALUES : EMPTY");
		}
	}
	/**
	 * 날짜형식 체크
	 * 2000-01-01 , 20000101 
	 * @param date
	 * @return
	 */
	public static boolean DateCheck(String date){
		boolean chk = false;
		
		SimpleDateFormat dateFormatParser;
		try{
			if(date.length() == 8)
			{
				dateFormatParser = new SimpleDateFormat("yyyyMMdd");
				dateFormatParser.parse(date);
				chk = true;
			}
			else if(date.length() == 10)
			{
				dateFormatParser = new SimpleDateFormat("yyyy-MM-dd");
				dateFormatParser.parse(date);
				chk = true;
			}
			else
				chk = false;
		
		}catch(Exception Ex){
		
		}
		return chk;
	}
	
	
	
	public String hyphenset(String getStr) {
		String resultStr = "";
		String year = "";
		String month = "";
		String day = "";
		if(getStr==null)
			return "";
		if(getStr.length()==8) {
			year = getStr.substring(0, 4);
			month = getStr.substring(4, 6);
			day = getStr.substring(6, 8);
			resultStr = year +"-"+ month +"-"+ day;
			return resultStr;
		
		}else if(getStr.length()==10)
			return getStr;
		else {
			return "";
		}
		
	}
	
	/*
	포맷값 넣어 현재 시간 가져오기 
	yyyy : 2018 / MM : 01 / yyyyMM : 201801 / yyyy-MM : 2018-01 / HH : 24시간 / hh : 12시간 / mm : 분 / ss : 초
	 */
	public static String getNowTime(String formatType, int msec) {
		long time = System.currentTimeMillis() + msec;
		SimpleDateFormat dayTime = new SimpleDateFormat(formatType);
		String nowTime = dayTime.format(new Date(time));
		
		return nowTime;		
	}	
	
	public static HashMap<String, Object> fromJson(JSONObject jsonObject) {
		HashMap<String, Object> map = new HashMap<String, Object>();

        Iterator<?> keyIterator = jsonObject.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = (String) keyIterator.next();
            try {
                Object obj = jsonObject.get(key);

                if (obj instanceof JSONObject) {
                    map.put(key, fromJson((JSONObject) obj));
                }
                else if (obj instanceof JSONArray) {
                    map.put(key, fromJson((JSONArray) obj));
                }
                else {
                    map.put(key, obj);
                }
            }
            catch (Exception e) {
                map = null;
            }
        }

        return map;
    }
	
	@SuppressWarnings("unchecked")
	public static List<HashMap<String, Object>> fromJson(JSONArray jsonArray) {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < jsonArray.size(); i++) {
            try {
                Object obj = jsonArray.get(i);

                if (obj instanceof JSONObject) {
                    list.add(fromJson((JSONObject) obj));
                }
                else if (obj instanceof JSONArray) {
                    list.add((HashMap<String, Object>) fromJson((JSONArray) obj));
                }
                else {
                    list.add((HashMap<String, Object>) obj);
                }
            }
            catch (Exception e) {
               list = null;
            }
        }

        return list;
    }
	

	public static String getStartDate14(String paramDate) throws Exception {
		//paramDate의  1일-14일한 값을 return
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String resultStartDate = null;
		if (paramDate != null && !paramDate.equals("")) {
			String thisMonthStartDate = paramDate.substring(0,7)+"-01";
			cal.setTime(sdf.parse(thisMonthStartDate));
			cal.add(Calendar.DAY_OF_YEAR, -14);
			resultStartDate = sdf.format(cal.getTime());
		}
		return resultStartDate;
	}
	
	public static String getEndDate14(String paramDate) throws Exception {
		//paramDate의 마지막 일+14일한 값을 return
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String resultEndDate = null;
		if (paramDate != null && !paramDate.equals("")) {
			cal.setTime(sdf.parse(paramDate));
			int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			Date thisMonthEndDate = sdf.parse(paramDate.substring(0,7)+"-"+endDay);
			cal.setTime(thisMonthEndDate);
			cal.add(Calendar.DAY_OF_YEAR, +14);
			resultEndDate = sdf.format(cal.getTime());
		}
		return resultEndDate;
	}
	
	public static String getFileExt(String filename) {
		int pos = filename.lastIndexOf( "." );
		String ext = filename.substring( pos + 1 );
		
		return ext;
	}
	
	public static String getFileType(String filename) {
		filename=filename.toLowerCase();
		String type = "F";
		String imgPattern = ".+\\.(png|jpg|jpg|tif|bmp|gif)$";
		String videoPattern = ".+\\.(mp4)$";
		String audioPattern = ".+\\.(mp3)$";
		String zipPattern = ".+\\.(zip)$";
		String pdfPattern = ".+\\.(pdf)$";
		
		Pattern p = Pattern.compile(imgPattern);
		Matcher m = p.matcher(filename);
		if(m.matches()) {
			return "I";
		}
		
		p = Pattern.compile(videoPattern);
		m = p.matcher(filename);
		if(m.matches()) {
			return "V";
		}		

		p = Pattern.compile(audioPattern);
		m = p.matcher(filename);
		if(m.matches()) {
			return "A";
		}

		p = Pattern.compile(zipPattern);
		m = p.matcher(filename);
		if(m.matches()) {
			return "Z";
		}
		
		p = Pattern.compile(pdfPattern);
		m = p.matcher(filename);
		if(m.matches()) {
			return "P";
		}
		
		return type;
	}
	
	public static String getBrowser(HttpServletRequest request) {

        String header =request.getHeader("User-Agent");

        if (header.contains("MSIE")) {

               return "MSIE";

        } else if(header.contains("Chrome")) {

               return "Chrome";

        } else if(header.contains("Opera")) {

               return "Opera";

        }

        return "Firefox";

  }
}
