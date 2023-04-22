package application;

import java.io.File;
import java.util.Locale;

import javax.speech.synthesis.Synthesizer;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.Engine;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;


public class TextToSpeech {
//	  public static void main(String[] args) {
//		  TextToSpeech ts = new TextToSpeech();
//		  ts.speak("hello");
//	  
//	  }
	        
    Synthesizer synthesizer;
     MediaPlayer mediaPlayer;


	public TextToSpeech() {
		
 
		   try {
	        	
	        	System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
	            // Initialize the speech synthesizer
	            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
	        	
	        	synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
	            if (synthesizer == null) {
	                throw new Exception("Unable to create synthesizer");
	            }
	            synthesizer.allocate();
	            synthesizer.resume();

	            // Set the voice of the synthesizer
	            Voice voice = new Voice(null, Voice.GENDER_MALE, Voice.AGE_TEENAGER, null);
	            synthesizer.getSynthesizerProperties().setVoice(voice);

	            // Speak the desired text
//	            synthesizer.speakPlainText("hello good morning", null);
//	            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

	            // Release the resources used by the synthesizer
	            //synthesizer.deallocate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		
	}
	

    public void speak(String text) {
        try {
            // Speak the text
            synthesizer.speakPlainText(text, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            
            //	
            AudioClip emptyClip = new AudioClip("");
            Media media = new Media(new File("").toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void pause() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING) {
            mediaPlayer.pause();
        }
    }
}
	  
	  
	

