package asw.sentence.sentenceservice.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

@Service 
public class SentenceService {

	@Autowired 
	private WordClientAsyncPort subjectClientAsyncAdapter;

	@Autowired 
	private WordClientAsyncPort verbClientAsyncAdapter;

	@Autowired 
	private WordClientAsyncPort objectClientAsyncAdapter;

	public String getSentence() {
		CompletableFuture<String> futureSubject = subjectClientAsyncAdapter.getWordAsync(); 
		CompletableFuture<String> futureVerb = verbClientAsyncAdapter.getWordAsync(); 
		CompletableFuture<String> futureObject = objectClientAsyncAdapter.getWordAsync(); 
		CompletableFuture.allOf(futureSubject, futureVerb, futureObject).join();
		String sentence = null; 
		try {
			sentence = futureSubject.get() + " " + futureVerb.get() + " " + futureObject.get() + ".";
		} catch (Exception e) {
			sentence = "*****"; 
		}
		return sentence; 
	}

}
