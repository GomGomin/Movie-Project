package ch01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Hello {
	
	public static void main(String[] args) throws IOException {
		Game game = new Game();
		System.out.println("== 프로그램을 시작합니다 ==");
		game.loadreport();
		game.play();
		game.writereport();
		System.out.println("== 승패기록 ==");
		game.loadreport();

	}
	
}

class Game{
	private int num;
	private String holjac;
	private String answer;
	private String win;
	private int winPoint;
	private int losePoint;
	private String lose;
	private String line;
	private static final Scanner scanner = new Scanner(System.in);
	
	public void loadreport() throws IOException {
		File file = new File("C:\\develop\\JavaPlese\\src\\ch01\\Report.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		int ch;
		line = br.readLine();
		System.out.println(line);
		
		int findwin = line.indexOf("승");
		win = line.substring(0,findwin);
		winPoint = Integer.parseInt(win);
		
		int findlose = line.indexOf("패");
		lose = line.substring(findwin+2,findlose);
		losePoint = Integer.parseInt(lose);
		
		System.out.println();
		fr.close();
	}

	public void writereport() throws IOException {
		File file = new File("C:\\develop\\JavaPlese\\src\\ch01\\Report.txt");
		PrintWriter pw = new PrintWriter(file);
        pw.close();
		FileWriter fw = new FileWriter(file,true);
		fw.write(line);
		fw.close();
	}

	public void play() {
		String result = "";
		System.out.println("== 게임을 진행합니다 ==");
		num = (int) (Math.random() * 999) + 100;
		holjac = (num % 2 == 1) ? "홀" : "짝";
		String su = ""+num;
		su = su.substring(0,2) + "X";
		System.out.printf("생성된 수 : %s\n",su);
		System.out.print("홀짝을 입력하세요 (1: 홀 2: 짝): ");
		String inputStr = scanner.next();
		int inputNum = Integer.parseInt(inputStr);
		answer = (inputNum == 1) ? "홀" : "짝";
		if(answer.equals(holjac)) {
			result = "정답입니다";
			winPoint++;
		}else{
			result = "오답입니다";
			losePoint++;
		}
		System.out.printf("%s! %s (%d)\n",holjac, result, num);
		System.out.println();
		line = String.format("%d승 %d패", winPoint, losePoint);
	}
}
