package writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import bean.Movie;
import bean.MovieTheather;
import bean.MovieTheatherType;

public class CatalogueWriter {

	private static CatalogueWriter instance = null;
	private Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> catalogue;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private File file;
	
	
	private CatalogueWriter() throws IOException {
		this.catalogue = new HashMap<>();
		this.file = new File("Catalogue.json");
		if (!this.file.exists()) {
			new File("Catalogue.json").createNewFile();
		}
		
	}
	
	public void saveCatalogueToFile() {
		if (!this.catalogue.isEmpty()) {
			try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, false))) {
				writer.println(gson.toJson(this.catalogue));
			} catch (IOException e) {
				return;
			}
			System.out.println("Zapisani vuv file " + file.getName());
		}
	}
	
	
	public void getCatalogueFromFile() throws FileNotFoundException {
		StringBuilder builder = new StringBuilder();
		try (Scanner sc = new Scanner(this.file)) {
			while (sc.hasNextLine()) {
				builder.append(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Type setType = new TypeToken<Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>>>() {}.getType();
		if (builder.length() > 0) {
			Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> getCatalogue 
			= gson.fromJson(builder.toString(), setType);
			this.catalogue.putAll(getCatalogue);
		} else {
			System.out.println("Oshte nqma obekti");
		}
	}
	
	public void addMovie(MovieTheather theater, LocalDate date, Movie movie) {
		if(theater != null && date != null && movie != null) {
			if(this.catalogue.containsKey(theater)) {
				if(this.catalogue.get(theater).containsKey(date)) {
					this.catalogue.get(theater).get(date).add(movie);
				} else {
					this.catalogue.get(theater).put(date, new TreeSet<Movie>());
					this.catalogue.get(theater).get(date).add(movie);	
				}
			} else {
				System.out.println("nqma takuv theater kluch");
				this.catalogue.put(theater, new TreeMap<LocalDate, TreeSet<Movie>>());
				this.catalogue.get(theater).put(date, new TreeSet<Movie>());
				this.catalogue.get(theater).get(date).add(movie);
			}
		} else {
			System.out.println("neshto e null");
		}
	}
	
	private static CatalogueWriter getInstance() {
		if(instance == null) {
			try {
				CatalogueWriter.instance = new CatalogueWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return CatalogueWriter.instance;
	}
	
	public Map<LocalDate, TreeSet<Movie>> getDateAndMoviesByTheater(MovieTheather mt){
		Map<LocalDate, TreeSet<Movie>> dateAndMovies = this.catalogue.get(mt);
		return dateAndMovies;
	}
	
	public TreeSet<Movie> getMoviesByTheaterAndDate(MovieTheather mt, LocalDate date){
		TreeSet<Movie> movies = this.catalogue.get(mt).get(date);
		return movies;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		CatalogueWriter.getInstance().getCatalogueFromFile();
//		CatalogueWriter.getInstance().addMovie(new MovieTheather(new MovieTheatherType(MovieTheatherType.MOVIE_THEATHER_TYPE[0], MovieTheatherType.VIDEO_FORMAT[2],MovieTheatherType.AUDIO_FORMAT[1])), LocalDate.of(2019, 02, 19), new Movie("Comedy", "Friends", (short) 90, LocalDate.of(2019, 02, 18), "category"));
//		CatalogueWriter.getInstance().addMovie(new MovieTheather(new MovieTheatherType(MovieTheatherType.MOVIE_THEATHER_TYPE[1], MovieTheatherType.VIDEO_FORMAT[1],MovieTheatherType.AUDIO_FORMAT[0])), LocalDate.of(2019, 02, 18), new Movie("Comedy", "Allo Allo", (short) 90, LocalDate.of(2019, 02, 18), "category"));
//
//		CatalogueWriter.getInstance().saveCatalogueToFile();
		
//		for(MovieTheather mt : CatalogueWriter.getInstance().getCatalogue().keySet()) {
//			System.out.println(CatalogueWriter.getInstance().getCatalogue().get(mt));
//		}
		
	}

	/**
	 * @return the catalogue
	 */
	public Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> getCatalogue() {
		return Collections.unmodifiableMap(catalogue);
	}
	
}
