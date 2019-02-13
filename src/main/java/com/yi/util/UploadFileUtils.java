package com.yi.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws IOException {//(upload경로, 파일이름, 파일데이터)
		
		UUID uid = UUID.randomUUID();//중복되지 않는 고유한 키값을 만듦
		String savedName = uid.toString()+"_"+originalName;
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath+savedPath+"/"+savedName);// File(uploadPath+savedPath , savedName)로 보내도 가능(폴더, 파일)
		FileCopyUtils.copy(fileData, target);
		
		//--------------------------썸네일용
		String thumPath = makeThumbnail(uploadPath, savedPath, savedName);
		
		return thumPath;
	}
	
	private static String makeThumbnail(String uploadPath, //c:zzz/upload
										String path, 		// /2019/02/13
										String fileName) throws IOException { //원본파일 이름
		
		//원본이미지에 데이터를 가져와서 가상이미지를 만듦
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath+ path , fileName));
		//세로 100크기에 고정하여가로 너비는 자동 조절하도록 하여 thumbnail데이터를 만듦
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		//thumbnail 파일명을 만듦(s_를 붙임)
		String thumbnailName = uploadPath + path + "/" + "s_" + fileName;
		
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		//만들어진 빈 thumbnail 파일에 destImg 테이터를 넣음
		ImageIO.write(destImg, formatName.toUpperCase(),newFile);
		return path + "/"+"s_"+fileName;//thumbnail용 파일 주소를 넘김
	}
	
	private static String calcPath(String uplaodPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = "/"+cal.get(Calendar.YEAR);
		String monthPath = String.format("%s/%02d", yearPath, cal.get(Calendar.MONTH)+1);
		String datePath = String.format("%s/%02d", monthPath, cal.get(Calendar.DATE));//년/월/일
		
		//2019/02/13
		makeDir(uplaodPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	private static void makeDir(String uploadPath, String... paths) {
		for(String path:paths) {
			File dirPath = new File(uploadPath + path);
			if(dirPath.exists()==false) {
				dirPath.mkdir();
			}
		}
		
	}
}





















