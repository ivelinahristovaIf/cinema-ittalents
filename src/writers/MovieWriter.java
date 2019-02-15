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

import bean.Movie;
import helper.NotValidMovieGenreException;

public class MovieWriter {
	private Set<Movie> movies;
	private static MovieWriter instance = null;

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private File file;

	private MovieWriter() throws IOException {
		this.movies = new LinkedHashSet<Movie>();
		this.file = new File("Movies.json");
		if (!file.exists()) {
			new File("Movies.json").createNewFile();
		}

	}

	public static MovieWriter getInstance() {
		if (instance == null) {
			try {
				instance = new MovieWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}


	public void saveMoviesToFile() {
		if (!this.movies.isEmpty()) {
			try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File("Movies.json"), false))) {
				pw.println(gson.toJson(movies));
			} catch (FileNotFoundException e) {
				return;
			}
			System.out.println("Zapisah gi vyv file");
		}
	}
	
	public void getMoviesFromFile() throws FileNotFoundException {
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
			Set<Movie> getMovies = gson.fromJson(builder.toString(), setType);
			this.movies.addAll(getMovies);
		} else {
			System.out.println("Oshte nqma obekti");
		}
	}
	public void addMovie(Movie movie) {
		if(movie!=null) {
			this.movies.add(movie);
		}
	}
	public Set<Movie> getMovies() {
		return movies;
	}
	
//	public static void main(String[] args) throws NotValidMovieGenreException, FileNotFoundException {
////		MovieWriter.getInstance().getMoviesFromFile();
//		MovieWriter.getInstance().addMovie(Movie.getInstance());
//		MovieWriter.getInstance().saveMoviesToFile();
//	}
}
