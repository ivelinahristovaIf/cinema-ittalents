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

import bean.ILogger;
import bean.MovieTheatherType;
import bean.User;

public class MovieTheaterTypeWriter {

	private Set<MovieTheatherType> types;
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private File file;
	
	
	public MovieTheaterTypeWriter() throws IOException {
		this.types = new LinkedHashSet<>();
		this.file = new File("MovieTheaterTypes.json");
		if(!this.file.exists()) {
			new File("MovieTheaterTypes.json").createNewFile();
		}
	}

	public void saveMovieTheaterTypesToFile() {
		if (!this.types.isEmpty()) {
			try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, false))) {
				writer.println(gson.toJson(this.types));
			} catch (IOException e) {
				return;
			}
			System.out.println("Zapisah gi vyv file");
		}
	}

	
	public void getMovieTheaterTypesFromFile() throws FileNotFoundException {
		StringBuilder builder = new StringBuilder();
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				builder.append(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Type setType = new TypeToken<LinkedHashSet<MovieTheatherType>>() {
		}.getType();
		if (builder.length() > 0) {
			Set<MovieTheatherType> getUsers = gson.fromJson(builder.toString(), setType);
			this.types.addAll(getUsers);
		} else {
			System.out.println("Oshte nqma obekti");
		}
	}
	
	public void addMovieTheaterType(MovieTheatherType type) {
		if(type != null) {
			this.types.add(type);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		MovieTheaterTypeWriter mw = new MovieTheaterTypeWriter();
		
		mw.getMovieTheaterTypesFromFile();
//		mw.addMovieTheaterType(new MovieTheatherType("IMAX", "2D", "Digital"));
//		mw.addMovieTheaterType(new MovieTheatherType("VIP", "2D", "Digital"));
		mw.saveMovieTheaterTypesToFile();
		
		for(MovieTheatherType m : mw.types) {
			System.out.println(m);
		}
		
		
		
	}
	
}