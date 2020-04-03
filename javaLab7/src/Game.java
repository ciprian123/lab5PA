public class Game {
    private static void printPlayerStats(Player player) {
        System.out.println(player.getName() + " tokens: ");
        player.getTokenList().forEach(token -> System.out.print(token.getValue() + " "));
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        final Board board = new Board(15);
        board.generateTokenList();

        final Player player1 = new Player("Player1", board);
        final Player player2 = new Player("Player2", board);
        final Player player3 = new Player("Player3", board);

        final Thread thread1 = new Thread(player1);
        final Thread thread2 = new Thread(player2);
        final Thread thread3 = new Thread(player3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        printPlayerStats(player1);
        printPlayerStats(player2);
        printPlayerStats(player3);
    }
}
