package writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import bean.MovieTheatherType;

public class MovieTheaterTypeWriter {

	private Set<MovieTheatherType> types;
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private File file;
	private static MovieTheaterTypeWriter instance = null;

	
	
	private MovieTheaterTypeWriter() throws IOException {
		this.types = new LinkedHashSet<>();
		this.file = new File("MovieTheaterTypes.json");
		if(!this.file.exists()) {
			new File("MovieTheaterTypes.json").createNewFile();
		}
	}
	
	public static MovieTheaterTypeWriter getInstance() {
		if (instance == null) {
			try {
				instance = new MovieTheaterTypeWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
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
			Set<MovieTheatherType> getTypes = gson.fromJson(builder.toString(), setType);
			this.types.addAll(getTypes);
		} else {
			System.out.println("Oshte nqma obekti");
		}
	}
	
	public void addMovieTheaterType(MovieTheatherType type) {
		if(type != null) {
			this.types.add(type);
		}
	}

	/**
	 * @return the types
	 */
	public Set<MovieTheatherType> getTypes() {
		return Collections.unmodifiableSet(types);
	}
	
//	public static void main(String[] args) {
//		try {
//			MovieTheaterTypeWriter.getInstance().getMovieTheaterTypesFromFile();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		MovieTheaterTypeWriter.getInstance().addMovieTheaterType(new MovieTheatherType("IMAX", "2D", "Dolby Digital"));
//		MovieTheaterTypeWriter.getInstance().saveMovieTheaterTypesToFile();
//	}
	
}
