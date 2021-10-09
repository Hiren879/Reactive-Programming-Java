package com.reactive.service;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

public class FluxAndMonoServiceTest {

	private FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();

	@Test
	public void test_getCountryFlux() {
		var flux = fluxAndMonoService.getCountryFlux();
		StepVerifier.create(flux).expectNext("India", "USA", "UK").verifyComplete();
	}

	@Test
	public void test_getCountryMono() {
		var mono = fluxAndMonoService.getCountryMono();
		StepVerifier.create(mono).expectNext("India").verifyComplete();
	}

	@Test
	public void test_getCountryMapFlux() {
		var flux = fluxAndMonoService.getCountryMapFlux();
		StepVerifier.create(flux).expectNext("INDIA", "USA", "UK").verifyComplete();
	}

	@Test
	public void test_getCountryFilterFlux() {
		var flux = fluxAndMonoService.getCountryFilterFlux();
		StepVerifier.create(flux).expectNext("India").verifyComplete();
	}

	@Test
	public void test_getCountryFlatMapFlux() {
		var flux = fluxAndMonoService.getCountryFlatMapFlux();
		StepVerifier.create(flux).expectNextCount(10).verifyComplete();
	}

	@Test
	public void test_getCountryFlatMapFluxAsync() {
		var flux = fluxAndMonoService.getCountryFlatMapFluxAsync();
		StepVerifier.create(flux).expectNextCount(10).verifyComplete();
	}

	@Test
	public void test_getCountryFlapMapMono() {
		var mono = fluxAndMonoService.getCountryFlapMapMono();
		StepVerifier.create(mono).expectNextCount(1).verifyComplete();
	}
	
	@Test
	public void test_getCountryMonoFlatMapMany() {
		var mono = fluxAndMonoService.getCountryMonoFlatMapMany();
		StepVerifier.create(mono).expectNextCount(5).verifyComplete();
	}
	
	@Test
	public void test_getCountryFluxTransform() {
		var flux = fluxAndMonoService.getCountryFluxTransform(5);
		StepVerifier.create(flux).expectNextCount(2).verifyComplete();
	}
	
	@Test
	public void test_getCountryFluxConcate() {
		var flux = fluxAndMonoService.getCountryFluxConcate().log();
		StepVerifier.create(flux).expectNextCount(5).verifyComplete();
	}
	
	@Test
	public void test_getCountryFluxMerge() {
		var flux = fluxAndMonoService.getCountryFluxMerge().log();
		StepVerifier.create(flux).expectNextCount(5).verifyComplete();
	}
	
	@Test
	public void test_getFluxCountryZip() {
		var flux = fluxAndMonoService.getFluxCountryZip();
		StepVerifier.create(flux).expectNextCount(2).verifyComplete();
	}
}
