
# Reactive Programming
- It is a new programming paradigm.
- Async and non-blocking
- Event/message driven
- functional code style in Java
- Back Pressure on Data Streams

## What is Reactive Programming ?
- **Give me some data but I will not wait**
- I will not block myself, I will continue doing my other job, you give me the response when you are ready, I will take it and process.
- By doing this, threads are released to do other works.

[![](https://mermaid.ink/img/eyJjb2RlIjoic2VxdWVuY2VEaWFncmFtXG4gICAgQXBwbGljYXRpb24tPj4rRGF0YWJhc2U6IEdpdmUgbWUgc29tZSBkYXRhLCBJIHdvbid0IHdhaXRcbiAgICBEYXRhYmFzZS0-PitBcHBsaWNhdGlvbjogSGVyZSBpcyBzb21lIGRhdGFcbiAgICBBcHBsaWNhdGlvbi0-PitEYXRhYmFzZTogc29tZSBtb3JlLCBJIHdvbid0IHdhaXRcbiAgICBBcHBsaWNhdGlvbi0-PitEYXRhYmFzZTogc29tZSBtb3JlLCBJIHdvbid0IHdhaXRcbiAgICBEYXRhYmFzZS0-PitBcHBsaWNhdGlvbjogSGVyZSBpcyBzb21lIGRhdGFcbiAgICBBcHBsaWNhdGlvbi0-PitEYXRhYmFzZTogc29tZSBtb3JlLCBJIHdvbid0IHdhaXRcbiAgICBEYXRhYmFzZS0-PitBcHBsaWNhdGlvbjogSGVyZSBpcyBzb21lIGRhdGFcbiAgICBEYXRhYmFzZS0-PitBcHBsaWNhdGlvbjogSGVyZSBpcyBzb21lIGRhdGFcbiAgICBBcHBsaWNhdGlvbi0-PitEYXRhYmFzZTogc29tZSBtb3JlLCBJIHdvbid0IHdhaXRcbiAgICBEYXRhYmFzZS0-PitBcHBsaWNhdGlvbjogSSBhbSBET05FLiBvbkNvbXBsZXRlKClcbiAgICBcbiAgICAgICAgICAgICIsIm1lcm1haWQiOnsidGhlbWUiOiJkZWZhdWx0In0sInVwZGF0ZUVkaXRvciI6ZmFsc2UsImF1dG9TeW5jIjp0cnVlLCJ1cGRhdGVEaWFncmFtIjpmYWxzZX0)](https://mermaid-js.github.io/mermaid-live-editor/edit#eyJjb2RlIjoic2VxdWVuY2VEaWFncmFtXG4gICAgQXBwbGljYXRpb24tPj4rRGF0YWJhc2U6IEdpdmUgbWUgc29tZSBkYXRhLCBJIHdvbid0IHdhaXRcbiAgICBEYXRhYmFzZS0-PitBcHBsaWNhdGlvbjogSGVyZSBpcyBzb21lIGRhdGFcbiAgICBBcHBsaWNhdGlvbi0-PitEYXRhYmFzZTogc29tZSBtb3JlLCBJIHdvbid0IHdhaXRcbiAgICBBcHBsaWNhdGlvbi0-PitEYXRhYmFzZTogc29tZSBtb3JlLCBJIHdvbid0IHdhaXRcbiAgICBEYXRhYmFzZS0-PitBcHBsaWNhdGlvbjogSGVyZSBpcyBzb21lIGRhdGFcbiAgICBBcHBsaWNhdGlvbi0-PitEYXRhYmFzZTogc29tZSBtb3JlLCBJIHdvbid0IHdhaXRcbiAgICBEYXRhYmFzZS0-PitBcHBsaWNhdGlvbjogSGVyZSBpcyBzb21lIGRhdGFcbiAgICBEYXRhYmFzZS0-PitBcHBsaWNhdGlvbjogSGVyZSBpcyBzb21lIGRhdGFcbiAgICBBcHBsaWNhdGlvbi0-PitEYXRhYmFzZTogc29tZSBtb3JlLCBJIHdvbid0IHdhaXRcbiAgICBEYXRhYmFzZS0-PitBcHBsaWNhdGlvbjogSSBhbSBET05FLiBvbkNvbXBsZXRlKClcbiAgICBcbiAgICAgICAgICAgICIsIm1lcm1haWQiOiJ7XG4gIFwidGhlbWVcIjogXCJkZWZhdWx0XCJcbn0iLCJ1cGRhdGVFZGl0b3IiOmZhbHNlLCJhdXRvU3luYyI6dHJ1ZSwidXBkYXRlRGlhZ3JhbSI6ZmFsc2V9)

## What is Back Pressure ?
- Suppose in above example, Database is sending huge amount of data to our application. So in that case our application may not be able to handle all the data at once.
- So we will tell Database that **Hey DB, kindly send this much amount of data at once and don't send all the data once.**

## What is Reactive Streams Specification ?

1. **Publisher**
	- This interface has **subscribe()** method which takes subscriber argument as input.
	- The entity (database) which will send you the data.
2. **Subscriber**
	- This interface has 4 methods
		- **onSubscribe**
		- **onNext**
		- **onError**
		- **onComplete**
	- This will be generally your application which will implement this interface and call above methods to fetch the data from publisher.
3. **Subscription**
	- It connects publisher & subscriber.
	- It has **request()** method which will take long args that defines how much data you wants.
	- It has **cancel()** method as well to cancel the subscription.
4. **Processor**
	- It extends the both, publisher & subscriber.
	- It can work as both.
	- 

**Below diagram shows the sequence of how Application (subscriber) & Database (publisher) works together.**

[![](https://mermaid.ink/img/eyJjb2RlIjoic2VxdWVuY2VEaWFncmFtXG4gICAgQXBwbGljYXRpb24tPj4rRGF0YWJhc2U6IHN1YnNjcmliZSgpXG4gICAgRGF0YWJhc2UtPj4rQXBwbGljYXRpb246IG9uU3Vic2NyaWJlKClcbiAgICBBcHBsaWNhdGlvbi0-PitEYXRhYmFzZTogcmVxdWVzdCgzKVxuICAgIERhdGFiYXNlLT4-K0FwcGxpY2F0aW9uOiBvbk5leHQoMSlcbiAgICBEYXRhYmFzZS0-PitBcHBsaWNhdGlvbjogb25OZXh0KDIpXG4gICAgRGF0YWJhc2UtPj4rQXBwbGljYXRpb246IG9uTmV4dCgzKVxuICAgIERhdGFiYXNlLT4-K0FwcGxpY2F0aW9uOiBvbkNvbXBsZXRlIC8gb25FcnJvcigpXG4gICAgXG4gICAgICAgICAgICAiLCJtZXJtYWlkIjp7InRoZW1lIjoiZGVmYXVsdCJ9LCJ1cGRhdGVFZGl0b3IiOmZhbHNlLCJhdXRvU3luYyI6dHJ1ZSwidXBkYXRlRGlhZ3JhbSI6ZmFsc2V9)](https://mermaid-js.github.io/mermaid-live-editor/edit#eyJjb2RlIjoic2VxdWVuY2VEaWFncmFtXG4gICAgQXBwbGljYXRpb24tPj4rRGF0YWJhc2U6IHN1YnNjcmliZSgpXG4gICAgRGF0YWJhc2UtPj4rQXBwbGljYXRpb246IG9uU3Vic2NyaWJlKClcbiAgICBBcHBsaWNhdGlvbi0-PitEYXRhYmFzZTogcmVxdWVzdCgzKVxuICAgIERhdGFiYXNlLT4-K0FwcGxpY2F0aW9uOiBvbk5leHQoMSlcbiAgICBEYXRhYmFzZS0-PitBcHBsaWNhdGlvbjogb25OZXh0KDIpXG4gICAgRGF0YWJhc2UtPj4rQXBwbGljYXRpb246IG9uTmV4dCgzKVxuICAgIERhdGFiYXNlLT4-K0FwcGxpY2F0aW9uOiBvbkNvbXBsZXRlIC8gb25FcnJvcigpXG4gICAgXG4gICAgICAgICAgICAiLCJtZXJtYWlkIjoie1xuICBcInRoZW1lXCI6IFwiZGVmYXVsdFwiXG59IiwidXBkYXRlRWRpdG9yIjpmYWxzZSwiYXV0b1N5bmMiOnRydWUsInVwZGF0ZURpYWdyYW0iOmZhbHNlfQ)

## What is Flux and Mono ?
Types to handle the data in reactive programming
1. **Flux** - works with 0 to N elements
2. **Mono** - works with 0 to 1 elements

### Flux example 
```
public class FluxAndMonoService {

	public Flux<String> getCountryFlux() {
		return Flux.fromIterable(Arrays.asList("India", "USA", "UK")).log();
	}

	public Mono<String> getCountryMono() {
		return Mono.just("India").log();
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
```

Here we are 
	- Getting the Flux and Mono of a String
	- subscribing to it
	- and printing the elements of it

If you will **log** the flux/mono, you will see the events that we saw earlier.
Here are the logs for the same :
```
00:12:36.957 [main] INFO reactor.Flux.Iterable.1 - | onSubscribe([Synchronous Fuseable] FluxIterable.IterableSubscription)
00:12:36.963 [main] INFO reactor.Flux.Iterable.1 - | request(unbounded)
00:12:36.964 [main] INFO reactor.Flux.Iterable.1 - | onNext(India)
Flux of :  India
00:12:36.965 [main] INFO reactor.Flux.Iterable.1 - | onNext(USA)
Flux of :  USA
00:12:36.965 [main] INFO reactor.Flux.Iterable.1 - | onNext(UK)
Flux of :  UK
00:12:36.966 [main] INFO reactor.Flux.Iterable.1 - | onComplete()
00:12:37.048 [main] INFO reactor.Mono.Just.2 - | onSubscribe([Synchronous Fuseable] Operators.ScalarSubscription)
00:12:37.049 [main] INFO reactor.Mono.Just.2 - | request(unbounded)
00:12:37.049 [main] INFO reactor.Mono.Just.2 - | onNext(India)
Mono of : India
00:12:37.050 [main] INFO reactor.Mono.Just.2 - | onComplete()
```
Now writing the test-cases for the same.

```
	private FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();

	@Test
	public void test_getCountryFlux() {
		var flux = fluxAndMonoService.getCountryFlux();
		StepVerifier.create(flux)
			.expectNext("India", "USA", "UK")
			.verifyComplete();
	}
	
	@Test
	public void test_getCountryMono() {
		var mono = fluxAndMonoService.getCountryMono();
		StepVerifier.create(mono)
			.expectNext("India")
			.verifyComplete();
	}

```

More example with other stream functions with flux & mono :
```
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
```

