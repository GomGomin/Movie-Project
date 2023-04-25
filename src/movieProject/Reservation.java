package movieProject;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;

public class Reservation {
	private long id;
	private long movieId;
	private static String movieTitle;
	private String seatName;
	private static final File file = new File("reservations.txt");
	
	public Reservation(long id, long movieId, String movieTitle, String seatName) {
		this.id = id;
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.seatName = seatName;
	}
	
	public static Reservation findById(String reservationId) throws IOException {
		Reservation r = null;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		
		while ((line = br.readLine()) != null) {
			String[] temp = line.split(",");
			if (reservationId.equals(temp[0])) {
				r = new Reservation(Long.parseLong(temp[0]), Long.parseLong(temp[1]), temp[2], temp[3]);
				break;
			}
		}
		br.close();
		return r;
	}
	
	public String toString() {
		return String.format("영화: %s. 좌석: %s", movieTitle, seatName);
	}
	
	public static Reservation cancel(String reservationId) throws IOException {
		Reservation canceled = null;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String text = "";
		String line = null;
		
		while ((line = br.readLine()) != null) {
			String[] temp = line.split(",");
			if(reservationId.equals(temp[0])) {
				canceled = new Reservation(
						Long.parseLong(temp[0]), 
						Long.parseLong(temp[1]), 
						temp[2], 
						temp[3]
				);
				continue;	
			}
			text += line + "\n";
		}
		br.close();
		
		FileWriter fw = new FileWriter(file);
		
		fw.write(text);
		fw.close();
		return canceled;
	}

	public static ArrayList<Reservation> findByMovieId(String movieIdStr) throws IOException {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		
		while((line = br.readLine()) != null) {
			String temp[] = line.split(",");
			if(movieIdStr.equals(temp[1])) {
				long id = Long.parseLong(temp[0]);
				long movieId = Long.parseLong(temp[1]);
				String movieTietle = temp[2];
				String seatName = temp[3];
				Reservation r = new Reservation(id, movieId, movieTitle, seatName);
				reservations.add(r);
			}
		}
		br.close();
		return reservations;
	}
	
	public Reservation(long movieId, String movieTitle, String seatName) {
		this.id = Instant.now().toEpochMilli();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.seatName = seatName;
	}
	
	public String getSeatName() {
		return seatName;
	}
	
	public void save() throws IOException {
		FileWriter fw = new FileWriter(file,true);
		fw.write(this.toFileString()+"\n");
		fw.close();
	}
	
	private String toFileString() {
		return String.format("%d,%d,%s,%s", id, movieId, movieTitle, seatName);
	}
	
	public long getId() {
		return id;
	}
}
