package Practica1;

public class Lab2 {

    // Класс Author
    static class Author {
        private String name;
        private String email;
        private char gender;

        public Author(String name, String email, char gender) {
            this.name = name;
            this.email = email;
            this.gender = gender;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public char getGender() { return gender; }

        @Override
        public String toString() {
            return name + " (" + gender + ") at " + email;
        }
    }

    public static void run() {
        System.out.println("\nПРАКТИКА №2");

        Author a1 = new Author("Justin Biber", "jBiber@gmail.com", 'm');
        Author a2 = new Author("Ariana Grande", "aGrande@gmail.com", 'f');

        System.out.println(a1);
        System.out.println(a2);

        a2.setEmail("arianaGr@gmail.com");
        System.out.println("После смены email: " + a2);
    }
}
