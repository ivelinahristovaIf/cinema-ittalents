package writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import bean.Movie;
import bean.MovieTheather;

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
	
	public void saveUsersToFile() {
		if (!this.catalogue.isEmpty()) {
			try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, false))) {
				writer.println(gson.toJson(this.catalogue));
			} catch (IOException e) {
				return;
			}
			System.out.println("Zapisani vuv file " + file.getName());
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
		Type setType = new TypeToken<Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>>>() {
		}.getType();
		if (builder.length() > 0) {
			Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> getCatalogue = gson.fromJson(builder.toString(), setType);
			this.catalogue.putAll((getCatalogue));
		} else {
			System.out.println("Oshte nqma obekti");
		}
	}
	
	public void addMovie(MovieTheather theater, LocalDate date, Movie movie) {
		if(theater != null && date != null && movie != null) {
			if(this.catalogue.containsKey(theater)) {
				if(this.catalogue.get(theater).containsKey(date)) {
					System.out.println("nqma takuv date kluch");
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
}
