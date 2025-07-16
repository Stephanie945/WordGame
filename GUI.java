JLabel prizeImage = new JLabel(new ImageIcon(getClass().getResource("/images/prize1.png")));
prizePanel.add(prizeImage);
prizePanel.revalidate();
prizePanel.repaint();


import javax.sound.sampled.*;

public void playSound(String soundFileName) {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/" + soundFileName));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
