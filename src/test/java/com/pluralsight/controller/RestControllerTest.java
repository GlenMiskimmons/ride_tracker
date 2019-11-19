package com.pluralsight.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerTest {

	private static final Log LOGGER = LogFactory.getLog(RestControllerTest.class);
	private static final String LOG_RIDE_PREFIX = "Ride: ";

	@Test(timeout=15000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8080/ride_tracker/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		if (rides != null) {
			for (Ride ride : rides) {
				LOGGER.info("Ride name: " + ride.getName());
			}
		}
	}

	@Test(timeout=15000)
	public void testCreateRide() {
		RestTemplate restTemplate = new RestTemplate();

		Ride ride = new Ride();
		ride.setName("YellowFork Ride");
		ride.setDuration(30);

		ride = restTemplate.postForObject("http://localhost:8080/ride_tracker/ride", ride, Ride.class);

		LOGGER.info(LOG_RIDE_PREFIX + ride);
	}

	@Test(timeout=15000)
	public void testGetRide() {
		RestTemplate restTemplate = new RestTemplate();

		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1", Ride.class);

		if (ride != null) {
			LOGGER.info(LOG_RIDE_PREFIX + ride.getName());
		}
	}

	@Test(timeout=15000)
	public void testUpdateRide() {
		RestTemplate restTemplate = new RestTemplate();

		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1", Ride.class);

		if (ride != null) {
			ride.setDuration(ride.getDuration() + 1);

			restTemplate.put("http://localhost:8080/ride_tracker/ride/", ride);

			LOGGER.info(LOG_RIDE_PREFIX + ride.getName());
		}
	}

	@Test(timeout=15000)
	public void testBatchUpdate() {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getForObject("http://localhost:8080/ride_tracker/batch", Object.class);
	}

	@Test(timeout=15000)
	public void testDelete() {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.delete("http://localhost:8080/ride_tracker/delete/18");
	}

	@Test(timeout=15000)
	public void testException() {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getForObject("http://localhost:8080/ride_tracker/test", Ride.class);
	}

}
