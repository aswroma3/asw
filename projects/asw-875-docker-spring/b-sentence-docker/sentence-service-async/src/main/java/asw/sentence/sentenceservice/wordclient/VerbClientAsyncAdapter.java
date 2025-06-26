package asw.sentence.sentenceservice.wordclient;

import asw.sentence.sentenceservice.domain.WordClientAsyncPort;
import asw.sentence.sentenceservice.domain.WordClientPort;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

@Service 
public class VerbClientAsyncAdapter implements WordClientAsyncPort {

	@Autowired 
	private WordClientPort verbClientAdapter;
	
	@Async
	public CompletableFuture<String> getWordAsync() {
		return CompletableFuture.completedFuture(verbClientAdapter.getWord()); 
	}

	
}



