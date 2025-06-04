Scanner scanner = new Scanner(System.in);
for (int i = 0; i < currentPlayers.length; i++) {
    System.out.print("Enter name for Player " + (i + 1) + ": ");
    String name = scanner.nextLine();
    currentPlayers[i] = new Players(name);
}
