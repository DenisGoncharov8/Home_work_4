import java.util.Random;
import java.util.Scanner;

public class Home_work_4 {
    public static int SIZE = 5;
           public static int DOTS_TO_WIN = 4;
           public static final char DOT_EMPTY = '•';
           public static final char DOT_X = 'X';
           public static final char DOT_O = 'O';
           public static final char DOT_P = 'P';
           public static char[][] map;
           public static Scanner sc = new Scanner(System.in);
           public static Random rand = new Random();

           public static void main(String[] args) {
               initMap();
               printMap();
               while (true) {
                   humanTurn();
                   printMap();
                   if (checkWin(DOT_X)) {
                       System.out.println("Победил человек");
                       break;
                   }
                   if (isMapFull()) {
                       System.out.println("Ничья");
                       break;
                   }
                   aiTurn();
                   printMap();
                   if (checkWin(DOT_O)) {
                       System.out.println("Победил Искуственный Интеллект");
                       break;
                   }
                   aiTurn();
                   printMap();
                   if (checkWin(DOT_P)) {
                       System.out.println("Победил человек");
                       break;
                   }
                   if (isMapFull()) {
                       System.out.println("Ничья");
                       break;
                   }
               }
               System.out.println("Игра закончена");
           }

           public static boolean checkWin(char symb) {
               if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
               if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
               if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
               if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
               if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
               if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
               if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
               if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
               return false;
           }

    public static void aiTurn() {
               int x, y;
               do {
                   x = rand.nextInt(SIZE);
                   y = rand.nextInt(SIZE);
               } while (!isCellValid(x, y));
               System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
               map[y][x] = DOT_O;
           }

           public static void humanTurn() {
               int x, y;
               do {
                   System.out.println("Введите координаты в формате X Y");
                   x = sc.nextInt() - 1;
                   y = sc.nextInt() - 1;
               } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
               map[y][x] = DOT_X;
           }

           public static boolean isCellValid(int x, int y) {
               if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
               if (map[y][x] == DOT_EMPTY) return true;
               return false;
           }

           public static void initMap() {
               map = new char[SIZE][SIZE];
               for (int i = 0; i < SIZE; i++) {
                   for (int j = 0; j < SIZE; j++) {
                       map[i][j] = DOT_EMPTY;
                   }
               }
           }

           public static void printMap() {
               for (int i = 0; i <= SIZE; i++) {
                   System.out.print(i + " ");
               }
               System.out.println();
               for (int i = 0; i < SIZE; i++) {
                   System.out.print((i + 1) + " ");
                   for (int j = 0; j < SIZE; j++) {
                       System.out.print(map[i][j] + " ");
                   }
                   System.out.println();
               }
               System.out.println();
           }
    /*
     * 2. Переделать проверку победы,
     * чтобы она не была реализована просто набором условий,
     * например, с использованием циклов.
     * 3. * Попробовать переписать логику проверки победы,
     * чтобы она работала для поля 5х5 и количества фишек 4.
     * Очень желательно не делать это просто набором условий
     * для каждой из возможных ситуаций;
     */
    static boolean isWinner(char symb)
    {
        int endOfOffset = map.length - DOTS_TO_WIN;

        for (int rowOffset = 0; rowOffset <= endOfOffset; rowOffset++)
        {
            if (isDiagonalsFilledWith(symb, rowOffset))
            {
                return true;
            }

            for (int columnOffset = 0; columnOffset <= endOfOffset; columnOffset++)
            {
                boolean hasWin =
                        isLinesFilledWith(symb, rowOffset, columnOffset);

                if (hasWin)
                {
                    return true;
                }
            }
        }

        return false;
    }

    static boolean isLinesFilledWith(char symb, int rowOffset, int columnOffset)
    {
        for (int row = rowOffset; row < (DOTS_TO_WIN + rowOffset); row++)
        {
            int horizontalWinCounter = 0;
            int verticalWinCounter = 0;

            for (int column = columnOffset; column < (DOTS_TO_WIN + columnOffset); column++)
            {
                // проверка горизонтали
                if (map[row][column] == symb)
                {
                    horizontalWinCounter++;
                }
                else
                {
                    horizontalWinCounter = 0;
                }

                // проверка вертикали
                if (map[column][row] == symb)
                {
                    verticalWinCounter++;
                }
                else
                {
                    verticalWinCounter = 0;
                }
            }

            if ((horizontalWinCounter == DOTS_TO_WIN) || (verticalWinCounter == DOTS_TO_WIN))
            {
                return true;
            }
        }

        return false;
    }

    static boolean isDiagonalsFilledWith(char symb, int rowOffset)
    {
        int mainDiagonalCounter = 0;
        int sideDiagonalCounter = 0;

        final int subSquareLength = (DOTS_TO_WIN + rowOffset);

        for (int row = rowOffset; row < subSquareLength; row++)
        {
            // проверка главной диагонали
            if (map[row][row] == symb)
            {
                mainDiagonalCounter++;
            }
            else
            {
                mainDiagonalCounter = 0;
            }

            // проверка побочной диагонали
            if (map[row][map.length - 1 - row] == symb)
            {
                sideDiagonalCounter++;
            }
            else
            {
                sideDiagonalCounter = 0;
            }
        }

        return (mainDiagonalCounter == DOTS_TO_WIN) || (sideDiagonalCounter == DOTS_TO_WIN);
    }

    static boolean isMapFull()
    {
        for (int row = 0; row < SIZE; row++)
        {
            for (int column = 0; column < SIZE; column++)
            {
                if (map[row][column] == DOT_EMPTY)
                {
                    return false;
                }
            }
        }

        return true;
    }
       }


