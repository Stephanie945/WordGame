public class Host {
    private String name;
    private String phrase;
    private StringBuilder maskedPhrase;

    public Host(String name, String phrase) {
        this.name = name;
        this.phrase = phrase.toLowerCase();
        maskedPhrase = new StringBuilder();

        for (char c : phrase.toCharArray()) {
            maskedPhrase.append(Character.isLetter(c) ? "_" : c);
        }
    }

    public boolean guessLetter(char letter) {
        boolean found = false;
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == letter) {
                maskedPhrase.setCharAt(i, letter);
                found = true;
            }
        }
        return found;
    }

    public boolean isComplete() {
        return maskedPhrase.toString().equals(phrase);
    }

    public String getMaskedPhrase() {
        return maskedPhrase.toString();
    }
}
