package com.reactive.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoService {

	public Flux<String> getCountryFlux() {
		return Flux.fromIterable(Arrays.asList("India", "USA", "UK")).log();
	}
	
	public Flux<String> getCountryMapFlux() {
		return Flux.fromIterable(Arrays.asList("India", "USA", "UK"))
				.map(String::toUpperCase)
				.log();
	}
	
	public Flux<String> getCountryFilterFlux() {
		return Flux.fromIterable(Arrays.asList("India", "USA", "UK"))
				.filter(s -> s.startsWith("I"))
				.log();
	}
	
	public Flux<String> getCountryFlatMapFlux() {
		return Flux.fromIterable(Arrays.asList("India", "USA", "UK"))
				.flatMap(s -> Flux.just(s.split("")))
				.log();
	}
	
	public Flux<String> getCountryFlatMapFluxAsync() {
		return Flux.fromIterable(List.of("India", "USA", "UK"))
				.flatMap(s -> Flux.just(s.split("")))
				.delayElements(Duration.ofMillis(new Random().nextInt(1000)))
				.log();
	}

	public Mono<String> getCountryMono() {
		return Mono.just("India").log();
	}
	
	public Mono<List<String>> getCountryFlapMapMono() {
		return Mono.just("India")
				.flatMap(s -> Mono.just(List.of(s.split(""))))
				.log();
	}

	public static void main(String[] args) {
		FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
		fluxAndMonoService.getCountryFlux().subscribe(c -> {
			System.out.println("Flux of :  " + c);
		});

		fluxAndMonoService.getCountryMono().subscribe(c -> {
			System.out.println("Mono of : " + c);
		});
	}
}
