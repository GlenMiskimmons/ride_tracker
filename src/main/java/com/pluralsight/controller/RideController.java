package com.pluralsight.controller;

import com.pluralsight.model.Ride;
import com.pluralsight.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RideController {

	@Autowired
	private RideService rideService;

	@GetMapping("/rides")
	public @ResponseBody List<Ride> getRides() {
		return rideService.getRides();
	}

	@PostMapping("/ride")
	public @ResponseBody Ride createRide(@RequestBody Ride ride) {
		return rideService.createRide(ride);
	}

	@GetMapping("/ride/{id}")
	public @ResponseBody Ride getRide(@PathVariable(value = "id") Integer id) {
		return rideService.getRide(id);
	}

	@PutMapping("/ride/")
	public @ResponseBody Ride updateRide(@RequestBody Ride ride) {
		return rideService.updateRide(ride);
	}

	@GetMapping("/batch")
	public @ResponseBody Object batch() {
		rideService.batch();

		return null;
	}

	@DeleteMapping("/delete/{id}")
	public @ResponseBody Object deleteRide(@PathVariable(value = "id") Integer id) {
		rideService.deleteRide(id);

		return null;
	}

}
