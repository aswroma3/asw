package asw.sentence.sentenceservice.domain;

import java.util.concurrent.CompletableFuture;

public interface WordClientAsyncPort {

	public CompletableFuture<String> getWordAsync(); 
	
}
