package asw.sentence.sentenceservice.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service 
public class SentenceServiceImpl implements SentenceService {

	@Autowired 
	private WordClientPort subjectClientAdapter;

	@Autowired 
	private WordClientPort verbClientAdapter;

	@Autowired 
	private WordClientPort objectClientAdapter;

	public String getSentence() {
		String sentence = 
			subjectClientAdapter.getWord() + " " + 
			verbClientAdapter.getWord() + " " + 
			objectClientAdapter.getWord() + ".";
		return sentence; 
	}
	
}
