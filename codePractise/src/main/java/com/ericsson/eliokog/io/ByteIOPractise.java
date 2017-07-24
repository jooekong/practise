package com.ericsson.eliokog.io;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashSet;

/**
 * Created by eliokog on 2016/10/9.test only
 */
public class ByteIOPractise {
    private static final int port = 3334;

    public static void main(String[] arg0) throws InterruptedException {

//        FileInputStreamTest fst = new FileInputStreamTest();
//        fst.readWriteFile(new File("C:\\Users\\eliokog\\Documents\\My Received Files\\Distributed-Configuration-Suites-soapui-project.xml"), new File("C:\\Users\\eliokog\\Documents\\My Received Files\\tmp.log4"));
//        FilerReaderTest frt = new FilerReaderTest();
//        frt.readWriteFile(new File("C:\\Users\\eliokog\\Documents\\My Received Files\\Distributed-Configuration-Suites-soapui-project.xml"), new File("C:\\Users\\eliokog\\Documents\\My Received Files\\tmp.log3"));
//        ObjectIOStreamClient client = new ObjectIOStreamClient();
//        client.start();

        ObjectFileTest.writeFile(new File("C:\\logs\\debug.log"));
        ObjectFileTest.readFile(new File("C:\\logs\\debug.log"));

    }


    private static class ObjectIOStreamClient {

        void start() {
            final Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress("127.0.0.1", port));
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                os.writeObject(new Echo("I'm Client"));

                //Always flush when done writing
                os.flush();
                // Pay attention for the Input&Output stream order here.
                // the ObjectInputStream constructor will block until it reads the stream header
                // see {@ObjectInputStream.readStreamHeader()}
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                Echo echo = (Echo) is.readObject();
                echo.echo();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static class ObjectFileTest {


        static void readFile(File file) {
            try (FileInputStream is = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(is)) {
                Human human = (Human) ois.readObject();
                System.out.println("age is: " + human.age + " name is：" + human.name);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        static void writeFile(File file) {
            try (FileOutputStream os = new FileOutputStream(file);
                 ObjectOutputStream ois = new ObjectOutputStream(os)) {
                ois.writeObject(new Human("tom", 33));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private static class Human implements Serializable, Comparable<Human> {

        private String name;
        private int age;

        Human(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Human o) {
            if (o == null) {
                return 1;
            }
            if (this.age > o.age) {
                return 1;
            }
            if (this.age < o.age) {
                return -1;
            }
            return 0;
        }
    }

    /**
     * read from files and parse to unique string
     */
    private static class FileInputStreamTest implements FileParser {

        /**
         * @param oldFile old file to read from
         * @param newFile write to an new file
         */
        @Override
        public void readWriteFile(File oldFile, File newFile) {
            try (FileInputStream is = new FileInputStream(oldFile); FileOutputStream os = new FileOutputStream(newFile)) {
                byte[] data = new byte[1024];
                StringBuilder sb = new StringBuilder();
                while (is.read(data) > 0) {
                    sb.append(new String(data));
                }
                HashSet<String> set = split(sb);
                //Writer to new File
                os.write(setToByte(set));
                System.out.println(set.size());
            } catch (FileNotFoundException e) {
                System.out.println("file doesn't exists" + oldFile.getName());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IO exception happened" + oldFile.getName());
                e.printStackTrace();
            }
        }


    }


    private static class FilerReaderTest implements FileParser {

        @Override
        public void readWriteFile(File oldFile, File newFile) {
            try (FileReader reader = new FileReader(oldFile); FileWriter writer = new FileWriter(newFile)) {
                StringBuilder sb = new StringBuilder();
                char[] data = new char[1024];
                if (reader.ready()) {
                    while (reader.read(data) > 0) {
                        sb.append(data);
                    }
                }
                HashSet<String> set = split(sb);
                //Writer to new File
                writer.write(setToChar(set));
                System.out.println(set.size());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static HashSet<String> split(StringBuilder sb) {
        HashSet<String> set = new HashSet<>();
        char[] chars = sb.toString().toCharArray();
        StringBuilder splitSB = new StringBuilder();
        for (char c : chars) {
            if (isPresent(c) && sb.length() > 0) {
                set.add(splitSB.toString());
                splitSB.setLength(0);
            } else {
                splitSB.append(c);
            }
        }
        return set;
    }

    private static boolean isPresent(char c) {
        return (c == '\n' || c == '\t' || c == ' ');
    }

    private static byte[] setToByte(HashSet<String> set) {
        StringBuilder sb = new StringBuilder();
        set.stream().forEach(str -> sb.append(str).append('\n').append('\t'));
        return sb.toString().getBytes();
    }

    private static char[] setToChar(HashSet<String> set) {
        StringBuilder sb = new StringBuilder();
        set.stream().forEach(str -> sb.append(str).append('\n').append('\t'));
        return sb.toString().toCharArray();
    }
}
