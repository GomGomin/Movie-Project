package movieProject;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;

public class Movie {
	private long id;
	private String title;
	private String genre;
	private static final File file = new File("movies.txt");
	
	public Movie(long id, String title, String gener) {
		this.id = id;
		this.title = title;
		this.genre = gener;
	}
	
	public static ArrayList<Movie> findAll() throws IOException{
		ArrayList<Movie> movies = new ArrayList<Movie>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		
		while((line = br.readLine()) != null) {
			String[] temp = line.split(",");
			Movie m = new Movie(
					Long.parseLong(temp[0]),
					temp[1],
					temp[2]
			);
			movies.add(m);
		}
		br.close();
		return movies;
	}
	
	public String toString() {
		return String.format("[%d]: %s(%s)", id, title, genre);
	}
	
	public Movie(String title, String genre) {
		this.id = Instant.now().getEpochSecond();
		this.title = title;
		this.genre = genre;
	}
	
	public void save() throws IOException {
		FileWriter fw = new FileWriter(file, true);
		fw.write(this.toFileString() + "\n");
		fw.close();
	}
	
	private String toFileString() {
		return String.format("%d,%s,%s", id, title, genre);
	}
	
	public static void delete(String movieIdStr) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String text = "";
		String line = null;
		
		while ((line = br.readLine()) != null) {
			String[] temp = line.split(",");
			if(movieIdStr.equals(temp[0])) {
				continue;
			}
			text += line + "\n";
			br.close();
			
			FileWriter fw = new FileWriter(file);
			
			fw.write(text);
			fw.close();
		}
	}
	
	public static Movie findById(String movieIdStr) throws IOException {
		Movie movie = null;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		
		while((line = br.readLine()) != null) {
			String[] temp = line.split(",");
			if(movieIdStr.equals(temp[0])) {
				movie = new Movie(Long.parseLong(temp[0]),temp[1],temp[2]);
				break;
			}
		}
		br.close();
		return movie;
	}

	public String getTitle() {
		return title;
	}
}
