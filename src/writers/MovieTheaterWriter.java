package writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import bean.Cinema;
import bean.Movie;
import bean.MovieTheather;
import bean.MovieTheatherType;

public class MovieTheaterWriter {

	
	private static MovieTheaterWriter instance = null;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private File file;
	private Set<MovieTheather> theaters;
	
	private MovieTheaterWriter() throws IOException {
		this.theaters = new LinkedHashSet<MovieTheather>();
		this.file = new File("MovieTheaters.json");
		if (!file.exists()) {
			new File("MovieTheaters.json").createNewFile();
		}
	}
	
	public static MovieTheaterWriter getInstance() {
		if (instance == null) {
			try {
				instance = new MovieTheaterWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public void saveMovieTheaterToFile() {
		if (!this.theaters.isEmpty()) {
			try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File("MovieTheaters.json"), false))) {
				pw.println(gson.toJson(theaters));
			} catch (FileNotFoundException e) {
				return;
			}
			System.out.println("Zapisah gi vyv file");
		}
	}
	
	public void getMovieTheatersFromFile() throws FileNotFoundException {
		StringBuilder builder = new StringBuilder();
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				builder.append(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Type setType = new TypeToken<LinkedHashSet<Movie>>() {
		}.getType();
		if (builder.length() > 0) {
			Set<MovieTheather> getTheaters = gson.fromJson(builder.toString(), setType);
			this.theaters.addAll(getTheaters);
		} else {
			System.out.println("Oshte nqma obekti");
		}
	}
	
	public void addMovieTheater(MovieTheather theater) {
		if(theater != null) {
			this.theaters.add(theater);
		}
	}
	
	public static void main(String[] args) {
		MovieTheather mt = new MovieTheather(type, cinema);
	}
	
	
	
}
