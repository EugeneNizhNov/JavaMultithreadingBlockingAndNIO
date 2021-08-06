/**
 * Задача 1. Тяжелые вычисления
 * В данной задаче был выбран подход BIO, т.к. при каких-то расчетах есть необходимость дождаться
 * конкретного конечного результата
 */
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // Занимаем порт, определяя серверный сокет
        ServerSocket servSocket = new ServerSocket(23444);
        while (true) {
            // Ждем подключения клиента и получаем потоки для дальнейшей работы
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new
                         InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    // Пишем ответ
                    out.println("Echo: " + getFibonacciValue(Integer.parseInt(line)));
                    // Выход если от клиента получили end
                    if (line.equals("end")) {
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public static int getFibonacciValue(int n) {
        if (n <= 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return getFibonacciValue(n - 1) + getFibonacciValue(n - 2);
        }
    }
}
