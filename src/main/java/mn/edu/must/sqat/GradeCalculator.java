package mn.edu.must.sqat;

public class GradeCalculator {

    // 90+ -> A, 80-89 -> B, 70-79 -> C, 60-69 -> D, <60 -> F
    // score нь 0-100 хязгаараас гарвал IllegalArgumentException шиднэ
    public String letterGrade(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Оноо 0-100 хооронд байх ёстой: " + score);
        }
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Ирц(10), лаб+бие даалт(40), сорил1(10), сорил2(10), шалгалт(30)
    // Аль нэг нь сөрөг эсвэл дээд хязгаараасаа хэтэрвэл IllegalArgumentException шиднэ
    public double totalScore(double att, double lab, double quiz1, double quiz2, double exam) {
        checkRange(att, 10, "Ирц");
        checkRange(lab, 40, "Лаб+бие даалт");
        checkRange(quiz1, 10, "Сорил 1");
        checkRange(quiz2, 10, "Сорил 2");
        checkRange(exam, 30, "Шалгалт");
        return att + lab + quiz1 + quiz2 + exam;
    }

    private void checkRange(double value, double max, String name) {
        if (value < 0 || value > max) {
            throw new IllegalArgumentException(name + " оноо 0-" + max + " хооронд байх ёстой: " + value);
        }
    }
}
