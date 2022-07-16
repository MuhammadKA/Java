package sockets;

import java.util.*;
import java.io.*;

public class Jukebox {
	ArrayList<Song> songList = new ArrayList<Song>();

	public static void main(String[] args) {
		new Jukebox().go();
	}

	/*
	 * Create a new inner class that implements Comparator (note that its type
	 * parameter matches the type we’re going to compare—in this case Song objects.)
	 */
	class ArtistCompare implements Comparator<Song> {
		public int compare(Song one, Song two) {
			/*
			 * We’re letting the String variables (for artist) do the actual comparison,
			 * since Strings already know how to alphabetize themselves.
			 */
			return one.getArtist().compareTo(two.getArtist());
		}
	}

	public void go() {
		getSongs();
		System.out.println(songList);
		Collections.sort(songList);
		System.out.println(songList);
		// Make an instance of the Comparator inner class.
		ArtistCompare artistCompare = new ArtistCompare();
		// Invoke sort(), passing it the list and a reference to the new custom
		// Comparator object.
		Collections.sort(songList, artistCompare);
		System.out.println(songList);
	}

	void getSongs() {
		try {
			File file = new File("SongList.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				addSong(line);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void addSong(String lineToParse) {
		String[] tokens = lineToParse.split("/");
		Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
		songList.add(nextSong);
	}

	/*
	 * Usually class type and comparable type match. We’re specifying the type that
	 * the implementing class can be compared against. This means that Song objects
	 * can be compared to other Song objects, for the purpose of sorting.
	 */
	class Song implements Comparable<Song> {
		String title;
		String artist;
		String rating;
		String bpm;

		/*
		 * Note: we’ve made sort-by-title the default sort, by keeping the compareTo()
		 * method in Song use the titles. But another way to design this would be to
		 * implement both the title sorting and artist sorting as inner Comparator
		 * classes, and not have Song implement Comparable at all. That means we’d
		 * always use the two-arg version of Collections.sort().
		 */
		public int compareTo(Song s) {
			/*
			 * We just pass the work on to the title String objects, since we know Strings
			 * have a compareTo() method.
			 */
			return title.compareTo(s.getTitle());
		}

		Song(String t, String a, String r, String b) {
			title = t;
			artist = a;
			rating = r;
			bpm = b;
		}

		public String getTitle() {
			return title;
		}

		public String getArtist() {
			return artist;
		}

		public String getRating() {
			return rating;
		}

		public String getBpm() {
			return bpm;
		}

		public String toString() {
			return title;
		}
	}

}
