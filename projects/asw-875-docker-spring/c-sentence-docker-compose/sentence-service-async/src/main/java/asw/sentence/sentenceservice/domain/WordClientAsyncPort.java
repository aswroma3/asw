package asw.sentence.sentenceservice.domain;

import java.util.concurrent.CompletableFuture;

public interface WordClientAsyncPort {

	CompletableFuture<String> getWordAsync(); 
	
}
