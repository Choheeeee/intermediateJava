package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class T02FileTest {

	public static void main(String[] args) throws IOException {

		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");
		
		if (f1.exists()) {
			System.out.println(f1.getAbsolutePath()+"은 존재합니다.");
		}else {
			System.out.println(f1.getAbsolutePath()+ "은 없는 파일입니다.");
			
			if (f1.createNewFile()) {	//파일만 생성해줌 => 안에 데이터는 다른 객체 이용
				System.out.println(f1.getAbsolutePath()+"파일을 새로 만들었습니다.");
			}
		}
		
		if (f2.exists()) {
			System.out.println(f2.getAbsolutePath()+ "은 존재합니다.");
		}else {
			System.out.println(f2.getAbsolutePath()+"은 없는 파일입니다.");
		}
		System.out.println("===============================================");
		
		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles();	//listFiles()는 File타입의 배열객체(파일의 객체를 얻을때 이용)
		
		for (File f : files) {
			System.out.println(f.getName() +" => ");
			if (f.isFile()) {
				System.out.println("파일");
			}else if (f.isDirectory()) {
				System.out.println("디렉토리(폴더)");
			}
		}
		System.out.println("===============================================");
		
		String[] strFiles = f3.list();	//list() String타입의 배열객체 (파일이름이나, 디렉토리 얻을때 이용)
		for (String name : strFiles) {
			System.out.println(name);
		}
		System.out.println("=======================Dos=====================");
		System.out.println();
		
		/*
		 * 지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주기 위한 메서드
		 * dir파일과 디렉토리 목록을 보여줄 파일객체
		 */
		displayFileList(f3);
	}

		//Dos에서 dir - d: - cd D_Other - dir 를 쳤을때, 나오는 정보를 그대로 출력해보기 위한 메서드
		private static void displayFileList(File dir) {
			
			System.out.println("["+ dir.getAbsolutePath()+ "] 디렉토리의 내용");
			
			//디렉토리 안의 모든 파일 목록을 가져온다.
			File[] files = dir.listFiles();
			
			//하위 디렉토리 정보를 저장할 List객체 생성 (인덱스 정보 저장용)
			List<Integer> subDirList = new ArrayList<>();
			
			//날짜를 출력하기 위한 형식 설정
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
			
			for (int i = 0; i < files.length; i++) {
				
				String attr = "";	//attribute : 파일의 속성정보(읽기, 쓰기, 히든, 디렉토리 구분)
				String size = "";	//파일크기(용량)
				
				if (files[i].isDirectory()) {
					attr = "<DIR>";
					subDirList.add(i);
				}else {
					attr = files[i].canRead() ? "R" : "x";
					attr += files[i].canWrite() ? "W" : "x";
					attr += files[i].isHidden() ? "H" : "x";
					size = files[i].length() + "";
				}
				
				System.out.printf("%s %8s %3s %s\n", sdf.format(new Date (files[i].lastModified())), attr, size, files[i].getName());
				
			}
			
			int dirCount = subDirList.size();
			int fileCount = files.length - dirCount;
			
			System.out.println(fileCount + "개의 파일, "+ dirCount+"개의 디렉토리(폴더)");
			System.out.println();
			

			for (Integer i: subDirList) {
				displayFileList(files[i]);
			}
	}
	
}
