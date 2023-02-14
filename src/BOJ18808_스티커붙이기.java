import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ18808_스티커붙이기 {
    static int n, m;
    static boolean[][] notebook;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        notebook = new boolean[n][m];
        int stickerNumber = Integer.parseInt(stringTokenizer.nextToken());

        for (int i = 0; i < stickerNumber; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int stickerHeight = Integer.parseInt(stringTokenizer.nextToken());
            int stickerWidth = Integer.parseInt(stringTokenizer.nextToken());
            boolean[][] sticker = new boolean[stickerHeight][stickerWidth];
            for (int j = 0; j < stickerHeight; j++) {
                stringTokenizer = new StringTokenizer(br.readLine());
                for (int k = 0; k < stickerWidth; k++) {
                    int temp = Integer.parseInt(stringTokenizer.nextToken());
                    if (temp == 1) {
                        sticker[j][k] = true;
                    }
                }
            }

            //printSticker(sticker);
            int rotateCount = 0;
            boolean changed = false;

            while (!changed) {
                if (rotateCount > 3) {
                    break;
                }
                for (int j = 0; j < n; j++) {
                    if (changed) {
                        break;
                    }
                    for (int k = 0; k < m; k++) {
                        if (attachSticker(j, k, sticker)) {
                            changed = true;
                            break;
                        }
                    }
                }
                sticker = rotateSticker(sticker, rotateCount);
                //printSticker(sticker);
                rotateCount += 1;
            }
        }

        //printNotebook();
        System.out.println(countEmpty());
    }

    private static boolean[][] rotateSticker(boolean[][] sticker, int rotateCount) {
        int stickerHeight = sticker.length;
        int stickerWidth = sticker[0].length;
        boolean[][] rotated = new boolean[stickerWidth][stickerHeight];

            for (int i = 0; i < stickerWidth; i++) {
                for (int j = 0; j < stickerHeight; j++) {
                    rotated[i][j] = sticker[stickerHeight - 1 - j][i];
                }
            }

        return rotated;
    }

    private static boolean attachSticker(int x, int y, boolean[][] sticker) {
        int stickerHeight = sticker.length;
        int stickerWidth = sticker[0].length;

        if (x + stickerHeight > n || y + stickerWidth > m) {
            return false;
        }
        boolean[][] copy = copyNotebook(notebook);
        for (int i = 0; i < stickerHeight; i++) {
            for (int j = 0; j < stickerWidth; j++) {
                int tempX = x + i;
                int tempY = y + j;
                if (sticker[i][j]) {
                    if (!copy[tempX][tempY]) {
                        copy[tempX][tempY] = true;
                    } else {
                        return false;
                    }
                }
            }
        }
        notebook = copyNotebook(copy);
        //printNotebook();
        return true;
    }

    private static boolean[][] copyNotebook(boolean[][] notebook) {
        boolean[][] newNotebook = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newNotebook[i][j] = notebook[i][j];
            }
        }
        return newNotebook;
    }

    private static int countEmpty() {
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (notebook[i][j]) {
                    answer += 1;
                }
            }
        }

        return answer;
    }

    private static void printNotebook() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (notebook[i][j]) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
            sb.append("\n");
        }
        sb.append("******************************");
        System.out.println(sb);
    }

    private static void printSticker(boolean[][] sticker) {
        int hegiht = sticker.length;
        int width = sticker[0].length;
        StringBuilder sb = new StringBuilder();
        sb.append("**this is sticker**");
        for (int i = 0; i < hegiht; i++) {
            for (int j = 0; j < width; j++) {
                if (sticker[i][j]) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
            sb.append("\n");
        }
        sb.append("******************************");
        System.out.println(sb);
    }
}
