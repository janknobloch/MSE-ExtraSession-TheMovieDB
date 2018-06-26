package de.tum.jk.creational.mvc;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.results.ResultList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MyController implements Initializable {

	private static final String API_KEY = "";
	@FXML
	Button forwardButton;
	@FXML
	Button backwardButton;
	@FXML
	Button searchButton;
	@FXML
	TextField searchTextField;
	@FXML
	TextField countTextField;
	@FXML
	ImageView imgView;
	@FXML
	Label movieTitle;
	@FXML
	Label movieOverview;
	@FXML
	Label movieVoteAverage;

	List<MovieInfo> result;
	int movieListCounter = 0;
	TheMovieDbApi api;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		searchTextField.setText("Hello World");
		searchAPI();
	}

	@FXML
	/**
	 * When SearchButton is clicked: Searches the API by using the
	 * searchTextField.getText() as Input
	 * 
	 * @throws MovieDbException
	 * @throws InterruptedException
	 */
	public void searchBtnClicked() throws MovieDbException, InterruptedException {

		System.out.println("search button clicked");

		searchAPI();
	}

	/**
	 * Searches the API by using the searchTextField.getText() as Input
	 * 
	 * @throws MovieDbException
	 * @throws InterruptedException
	 */
	private void searchAPI() {
		try {
			api = new TheMovieDbApi(API_KEY);
			result = api.searchMovie(searchTextField.getText(), 0, "German", true, null, null, null).getResults();
			movieListCounter = 0;
			System.out.println("found " + result.size() + " movies for the query");
			setValues();

		} catch (MovieDbException e) {
			// TODO Auto-generated catch block
			System.out.println("something went wrong..");
		}
	}

	@FXML
	/**
	 * When forwardButton is pressed: Updates the List of Movies Found by iterating
	 * and refreshing all View Elements
	 * 
	 * @throws MovieDbException
	 * @throws InterruptedException
	 */
	public void forwardBtnClicked() throws MovieDbException, InterruptedException {

		System.out.println("forward button clicked");

		if (result.size() - 1 > movieListCounter) {
			movieListCounter++;
			setValues();

		}
	}

	@FXML
	/**
	 * When backButton is pressed: Updates the List of Movies Found by iterating and
	 * refreshing all View Elements
	 * 
	 * @throws MovieDbException
	 * @throws InterruptedException
	 */
	public void backwardBtnClicked() throws MovieDbException, InterruptedException {

		System.out.println("back button clicked");

		if (movieListCounter > 0) {
			movieListCounter--;
			System.out.println(result.get(movieListCounter).getTitle());
			setValues();

		}
	}

	/**
	 * Sets all UI elements based on movieListCounter: ImageView, MovieTitle,
	 * MovieOverview, moviePopularity, counterTextField
	 */
	private void setValues() {
		if (result.size() > 0) {
			imgView.setImage(new Image(
					"https://image.tmdb.org/t/p/w600_and_h900_bestv2" + result.get(movieListCounter).getPosterPath()));
			if (result.get(movieListCounter).getTitle() != null)
				movieTitle.setText(result.get(movieListCounter).getTitle());
			if (result.get(movieListCounter).getOverview() != null)
				movieOverview.setText(result.get(movieListCounter).getOverview());
			movieVoteAverage.setText(result.get(movieListCounter).getVoteAverage() * 10 + " %");
			countTextField.setText(movieListCounter + "");
		}
	}

}
