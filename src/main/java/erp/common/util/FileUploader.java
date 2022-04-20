package erp.common.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.web.multipart.MultipartFile;

public class FileUploader {
	
	/**
	 * 파일명 변경하여 업로드
	 * @param uploadDir
	 * @param file
	 * @return
	 */
	public static String uploadFile(String uploadDir, MultipartFile file) {
		return uploadFile(uploadDir, file, true);
	}
	
	/**
	 * 파일명을 변경하지 않고 업로드
	 * @param uploadDir
	 * @param file
	 * @return
	 */
	public static String uploadOrignalName(String uploadDir, MultipartFile file) {
		return uploadFile(uploadDir, file, false);
	}
	
	/**
	 * 파일 업로드 처리
	 * @param uploadDir
	 * @param file
	 * @param changeName
	 * @return
	 */
	private static String uploadFile(String uploadDir, MultipartFile file, boolean changeName) {
		String fileName = null;
		String realName = null;
		
		//파일이 있으면 업로드
		if (file != null && !file.isEmpty()) {
			
			//업로드 폴더 생성
			String[] dirList = uploadDir.split("/");
			
			String filePath = "";
			
			for (String dir : dirList) {
				filePath += dir + "/";
				File fileDir = new File(filePath);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
			}
			
			fileName = file.getOriginalFilename();
			
			String fileExt = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
			
			if (changeName) {
				realName = getUploadFileName(uploadDir, file.getOriginalFilename());
			} else {
				realName = fileName;
			}
			fileExt=fileExt.toLowerCase();
			//upload 가능한 파일 타입 지정
	        if(fileExt.equalsIgnoreCase("jpg") ||
	        		fileExt.equalsIgnoreCase("jpeg") ||
	        		fileExt.equalsIgnoreCase("gif") ||
	        		fileExt.equalsIgnoreCase("png") ||
	        		fileExt.equalsIgnoreCase("bmp") ||
	        		fileExt.equalsIgnoreCase("mp3") ||
	        		fileExt.equalsIgnoreCase("mp4") ||
	        		fileExt.equalsIgnoreCase("xml") ||
	        		fileExt.equalsIgnoreCase("xls") ||
	        		fileExt.equalsIgnoreCase("xlsx") ||
	        		fileExt.equalsIgnoreCase("txt") ||
	        		fileExt.equalsIgnoreCase("hwp") ||
	        		fileExt.equalsIgnoreCase("pdf") ||
	        		fileExt.equalsIgnoreCase("zip") ||
	        		fileExt.equalsIgnoreCase("ppt") ||
	        		fileExt.equalsIgnoreCase("pptx") ||
	        		fileExt.equalsIgnoreCase("doc") ||
	        		fileExt.equalsIgnoreCase("avi") || 
	        		fileExt.equalsIgnoreCase("mkv") ||
	        		fileExt.equalsIgnoreCase("webm") || 
	        		fileExt.equalsIgnoreCase("ogg") || 
	        		fileExt.equalsIgnoreCase("docx") ) {
	        	 
	        	
	            try {
	            	byte[] bytes = file.getBytes();
					File outFile = new File(uploadDir + "/" + realName);
					FileOutputStream fileOutputStream = new FileOutputStream(outFile);
					fileOutputStream.write(bytes);
					fileOutputStream.close();
	            } catch(IOException e) {
	            	e.printStackTrace();
	            	realName = "";
	            }
	        } else {
	        	realName = "";
	        }
		}
		return realName;
	}
	
	/**
	 * 날짜로 파일명 생성
	 * @param uploadDir
	 * @param fileName
	 * @return
	 */
	public static String getUploadFileName(String uploadDir, String fileName) {
		
		String realName = null;
		String milName = "";
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		long milliSecond = System.currentTimeMillis();
		milName = String.valueOf(milliSecond).substring(10, 13);
		
    	realName = (sdf.format(new java.util.Date()) + milName) + "." + fileExt;
    	
    	File ckfile = new File(uploadDir + "/" + realName);
    	
		int index = 0;
		
		//동일한 파일명이 존재하면 파일명에 1을 더함
		while(ckfile.exists()) {
			index++;
			realName = String.valueOf(Long.parseLong(sdf.format(new java.util.Date()) + milName) + index) + "." + fileExt;
			ckfile = new File(uploadDir + "/" + realName);
		}
		return realName;
	}

	public static void deleteFile(File path){
		if (path != null && path.exists()) {
			File[] files = path.listFiles();
			if (files != null) {
				for(File file : files) {
					if(file.isDirectory()) {
						deleteFile(file);
					}
					else
						file.delete();					
				}
			}
		}
	}

	
	
	//썸네일 이미지 생성하기
    // 1.파일 경로 2. 년월일 경로, 3. 파일 이름 
  /*  public static String makeThumbnail(String uploadPath, String fileName, Integer width, Integer height) throws Exception{
    	//uploadPath  : /opt/tomcat9/webapps/uport_res/capacity/04/326
    	//filePath  : /capacity/04/326
    	//realName  : 20190223163856662.jpg
    	
    	// 파일 존재하는 이미지를 메모리상 이미지에 올려 붙인다. 즉 메모리에 로딩시킨다.
        BufferedImage sourceImg= ImageIO.read(new File(uploadPath+"/", fileName));
         
        //메모리상 이미지를 정해진 크기에 맞게 복사한다.
        BufferedImage destImage=Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC,
                Scalr.Mode.FIT_TO_WIDTH,  width, height);
         
        //썸네일 이미지 이름을 정한다. 썸네일 이미지를 앞에 s_ 붙인다.
        String thumbnailName=uploadPath+"/"+"thumb_"+fileName;
         
        // 파일 경로의 객체를 생성한다.
        File newFile=new File(thumbnailName);
         
        // 경로의 마지막 에 . 으로 중심으로 분리해서 .jpg, png, jpeg gif 의 문자를 추출한다.  
        String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
         
        //실질적인 썸네일 이미지를 복사한다.  
        ImageIO.write(destImage, formatName.toUpperCase(), newFile);
     
        // 파일 경로인 역슬러시를 url 의 경로인 슬러시로 변경해서 해서 반환시킨다.
        // 반환 값   ex ) :   /thumb_20190223164425968.jpg
        return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }
     
     */
	
	
}
