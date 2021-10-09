package com.reactive.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

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
	
	/**
	 * MonoFlatMapMany : This will convert MONO into FlUX
	 * @return
	 */
	public Flux<String> getCountryMonoFlatMapMany() {
		return Mono.just("India")
				.flatMapMany(s -> Flux.just(s.split("")))
				.log();
	}
	
	/**
	 * Creating a function for the flux which takes the flux and returns the flux.
	 * 
	 * @param number
	 * @return
	 */
	public Flux<String> getCountryFluxTransform(int number) {
		// create a function for a flux
		Function<Flux<String>, Flux<String>> transformFluxFunc = f -> f.filter(s -> s.length() > number);

		return Flux.fromIterable(List.of("India", "Dubai", "USA", "Germany", "France"))
				.transform(transformFluxFunc)
				.defaultIfEmpty("default_data") // case when there is not match
				.log();
	}

	/**
	 * This will concat two flux.
	 * This will be helpful when you have two different
	 * datasources and you want to concat them.
	 * @return
	 */
	public Flux<String> getCountryFluxConcate() {
		var indiaFlux = Flux.just("Gujarat", "Punjab", "Goa");
		var usaFlux = Flux.just("New York", "New Jersey");
		return Flux.concat(indiaFlux, usaFlux);
	}
	
	/**
	 * Merge will concat as and when data is available from each flux.
	 * @return
	 */
	public Flux<String> getCountryFluxMerge() {
		var indiaFlux = Flux.just("Gujarat", "Punjab", "Goa").delayElements(Duration.ofMillis(50));
		var usaFlux = Flux.just("New York", "New Jersey").delayElements(Duration.ofMillis(39));
		return Flux.merge(indiaFlux, usaFlux);
	}
	
	/**
	 * Zip two flux. Take one element from two different flux and decide what you
	 * want to do with it.
	 * 
	 * @return
	 */
	public Flux<String> getFluxCountryZip() {
		var indiaFlux = Flux.just("Gujarat", "Punjab", "Goa").delayElements(Duration.ofMillis(50));
		var usaFlux = Flux.just("New York", "New Jersey").delayElements(Duration.ofMillis(39));
		return Flux.zip(indiaFlux, usaFlux, (first, second) -> first + " :: " + second).log();
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
