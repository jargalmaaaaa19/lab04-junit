package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GradeCalculatorTest {

    // ---------- letterGrade: хязгаарын утгууд ----------

    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)")
    void ninetyIsExactlyA() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        String grade = calc.letterGrade(90.0);          // Act
        assertEquals("A", grade);                       // Assert
    }

    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой, A биш (хязгаарын тохиолдол)")
    void eightyNinePointNineNineIsB() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(89.99);
        assertEquals("B", grade);
    }

    @Test
    @DisplayName("60 оноо яг D дүн байх ёстой (хязгаарын тохиолдол)")
    void sixtyIsExactlyD() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(60.0);
        assertEquals("D", grade);
    }

    @Test
    @DisplayName("59.99 оноо F дүн байх ёстой (хязгаарын тохиолдол)")
    void fiftyNinePointNineNineIsF() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(59.99);
        assertEquals("F", grade);
    }

    @Test
    @DisplayName("0 оноо зөвшөөрөгдөх доод хязгаар бөгөөд F дүн өгнө")
    void zeroIsValidAndF() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(0.0);
        assertEquals("F", grade);
    }

    @Test
    @DisplayName("100 оноо зөвшөөрөгдөх дээд хязгаар бөгөөд A дүн өгнө")
    void hundredIsValidAndA() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(100.0);
        assertEquals("A", grade);
    }

    // ---------- letterGrade: ердийн утгууд ----------

    @Test
    @DisplayName("Ердийн оноонууд зөв үсгэн дүн өгнө (95→A, 85→B, 75→C, 65→D, 30→F)")
    void typicalScores() {
        GradeCalculator calc = new GradeCalculator();
        assertAll(
            () -> assertEquals("A", calc.letterGrade(95)),
            () -> assertEquals("B", calc.letterGrade(85)),
            () -> assertEquals("C", calc.letterGrade(75)),
            () -> assertEquals("D", calc.letterGrade(65)),
            () -> assertEquals("F", calc.letterGrade(30))
        );
    }

    // ---------- letterGrade: буруу оролт ----------

    @Test
    @DisplayName("Сөрөг оноо (-1) IllegalArgumentException шидэх ёстой")
    void negativeScoreThrows() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }

    @Test
    @DisplayName("100-аас их оноо (101) IllegalArgumentException шидэх ёстой")
    void scoreAbove100Throws() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    // ---------- totalScore ----------

    @Test
    @DisplayName("Бүх оноо дээд хэмжээндээ байвал нийлбэр 100 байна")
    void totalScoreMaximumIs100() {
        GradeCalculator calc = new GradeCalculator();
        double total = calc.totalScore(10, 40, 10, 10, 30);
        assertEquals(100.0, total, 0.0001);
    }

    @Test
    @DisplayName("Ирцийн оноо сөрөг (att = -5) бол IllegalArgumentException шиднэ")
    void totalScoreNegativeAttendanceThrows() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class,
                () -> calc.totalScore(-5, 40, 10, 10, 30));
    }

    @Test
    @DisplayName("Лабын оноо дээд хязгаараас хэтэрсэн (lab = 41) бол IllegalArgumentException шиднэ")
    void totalScoreLabAboveMaxThrows() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class,
                () -> calc.totalScore(10, 41, 10, 10, 30));
    }

    // ---------- Parameterized тестүүд ----------

    @ParameterizedTest(name = "letterGrade({0}) = {1}")
    @DisplayName("letterGrade-ийн хязгаарын утгууд")
    @CsvSource({"95,A", "90,A", "89.99,B", "80,B", "70,C", "60,D", "59.99,F", "0,F"})
    void letterGradeBoundaries(double score, String expected) {
        assertEquals(expected, new GradeCalculator().letterGrade(score));
    }

    @ParameterizedTest(name = "totalScore({0},{1},{2},{3},{4}) = {5}")
    @DisplayName("totalScore-ын зөв нийлбэрүүд")
    @CsvSource({
        "10,40,10,10,30,100",
        "0,0,0,0,0,0",
        "8,30,7,6,20,71",
        "5,20,5,5,15,50"
    })
    void totalScoreSums(double att, double lab, double q1, double q2, double exam, double expected) {
        assertEquals(expected, new GradeCalculator().totalScore(att, lab, q1, q2, exam), 0.0001);
    }
}
