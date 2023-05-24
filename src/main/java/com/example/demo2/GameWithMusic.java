package com.example.demo2;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GameWithMusic {
    private Clip clip;

    public GameWithMusic() {
        playBackgroundMusic();
    }

    public void playBackgroundMusic() {
        try {
            File audioFile = new File("src/main/resources/com/example/demo2/music.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    public static void main(String[] args) {
        GameWithMusic game = new GameWithMusic();
        game.stopMusic();
    }
}
