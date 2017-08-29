package com.cognifide.interview.planerv2.client;

import io.advantageous.qbit.http.client.HttpClient;
import io.advantageous.qbit.http.request.HttpRequest;

import static io.advantageous.qbit.http.client.HttpClientBuilder.httpClientBuilder;
import static io.advantageous.qbit.http.request.HttpRequestBuilder.httpRequestBuilder;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class SimpleRestClient {

	private static final int PORT_VALUE = 6060;

	private static final Logger log = Logger.getLogger(SimpleRestClient.class);

	/**
	 * could be time consuming due to default timeout
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		HttpClient httpClient = httpClientBuilder().setHost("localhost").setPort(PORT_VALUE).build();

		httpClient.start();

		try {
			addBuilding(httpClient);
			getBuilding(httpClient);
			removeBuilding(httpClient);

			removeBuilding(httpClient);
			addBuilding(httpClient);
			addBuilding(httpClient);

			getBuilding(httpClient);
			getRoom(httpClient);

			createReservation(httpClient);
			createReservation(httpClient);
			createSecondReservation(httpClient);

			getAvailableReservations(httpClient);
			getAllAvailableReservations(httpClient);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void removeBuilding(HttpClient httpClient) throws InterruptedException {
		Thread.sleep(500);
		new Thread(new Runnable() {
			@Override
			public void run() {
				final HttpRequest httpRequest = httpRequestBuilder().setMethod("DELETE")
						.setUri("http://localhost:6060/services/planer/building/remove/hotel").build();
				String results = httpClient
						.sendRequestAndWait(httpRequest, HttpClient.HTTP_CLIENT_DEFAULT_TIMEOUT, TimeUnit.SECONDS)
						.body();
				log.debug(results);
			}
		}).start();
	}

	public static void getBuilding(HttpClient httpClient) throws InterruptedException {
		Thread.sleep(500);
		new Thread(new Runnable() {

			@Override
			public void run() {

				String results = httpClient.get("http://localhost:6060/services/planer/building/get/hotel").body();
				log.debug(results);
			}
		}).start();
	}

	public static void getRoom(HttpClient httpClient) throws InterruptedException {
		Thread.sleep(500);
		new Thread(new Runnable() {

			@Override
			public void run() {

				String results = httpClient.get("http://localhost:6060/services/planer/building/details/hotel/1")
						.body();
				log.debug(results);
			}
		}).start();
	}

	public static void getBuildings(HttpClient httpClient) throws InterruptedException {
		Thread.sleep(500);
		new Thread(new Runnable() {

			@Override
			public void run() {

				String results = httpClient.get("http://localhost:6060/services/planer/buildings").body();
				log.debug(results);
			}
		}).start();
	}

	public static void getAvailableReservations(HttpClient httpClient) throws InterruptedException {
		Thread.sleep(500);
		new Thread(new Runnable() {

			@Override
			public void run() {

				String results = httpClient.get("http://localhost:6060/services/planer/available/building/room/hotel/2")
						.body();
				log.debug(results);
			}
		}).start();
	}

	public static void getAllAvailableReservations(HttpClient httpClient) throws InterruptedException {
		Thread.sleep(500);
		new Thread(new Runnable() {

			@Override
			public void run() {

				String results = httpClient.get("http://localhost:6060/services/planer/available/building/hotel")
						.body();
				log.debug(results);
			}
		}).start();
	}

	public static void addBuilding(HttpClient httpClient) throws InterruptedException {
		Thread.sleep(500);
		new Thread(new Runnable() {

			@Override
			public void run() {

				String results = httpClient.post("http://localhost:6060/services/planer/building/add/hotel/2").body();
				log.debug(results);
			}
		}).start();
	}

	public static void createReservation(HttpClient httpClient) throws InterruptedException {
		Thread.sleep(500);
		new Thread(new Runnable() {

			@Override
			public void run() {

				String results = httpClient
						.post("http://localhost:6060/services/planer/reserve/hotel/2/2018-08-27T12:00/2018-08-27T13:00")
						.body();
				log.debug(results);
			}
		}).start();
	}

	public static void createSecondReservation(HttpClient httpClient) throws InterruptedException {
		Thread.sleep(500);
		new Thread(new Runnable() {

			@Override
			public void run() {
				String results = httpClient
						.post("http://localhost:6060/services/planer/reserve/hotel/2/2018-09-27T12:00/2018-09-27T13:00")
						.body();
				log.debug(results);
			}
		}).start();
	}

}
